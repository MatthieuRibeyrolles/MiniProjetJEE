/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;

import com.bmt.project.model.ClientEntity;
import com.bmt.project.model.DAO;
import com.bmt.project.model.LineEntity;
import com.bmt.project.model.OrderEntity;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bruno
 */
public class AccountServlet extends HttpServlet {



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        DAO MyDao = (DAO) session.getAttribute("mydao");
        ClientEntity user = (ClientEntity) session.getAttribute("usr");            
        OrderEntity OrderCurrent = (OrderEntity) session.getAttribute("currentorder");
        
        
                String log =request.getParameter("login");
        String pass=request.getParameter("password");
        
        request.setCharacterEncoding("UTF-8");
        
//      connexion
        if (log != null && pass != null){
            
            user = MyDao.login(log,pass);
            session.setAttribute("usr", user);
//          verification si la connection est possible
            if (( user!=null )|( log.equals("admin") && pass.equals("admin") )){   
                
                //admin LOG = Maria Anders, PASS = ALFKI 
                //random LOG = Ana Trujillo  , PASS = ANATR
                boolean admin=false;
                boolean client=false;
                
                ArrayList<String> infoclientString = new ArrayList<String>();
                
                if (log.equals("admin") && pass.equals("admin")){
                    admin=true;
                }else{
                    client=true;
                    infoclientString.add(user.getCompany());
                    infoclientString.add(user.getContact());
                    infoclientString.add(user.getRole());
                    infoclientString.add(user.getAddress());
                    infoclientString.add(user.getCity());
                    infoclientString.add(user.getRegion());
                    infoclientString.add(user.getZipCode());
                    infoclientString.add(user.getCountry());
                    infoclientString.add(user.getPhone());
                    infoclientString.add(user.getFax());                   
                }
                session.setAttribute("infoClient",infoclientString);
                session.setAttribute("usrname", log);
                session.setAttribute("pass",pass);
                session.setAttribute("usr",user);

                session.setAttribute("log",true);
                session.setAttribute("client", client);
                session.setAttribute("admin",admin);

//              si c'est un client
                if (client){
                    List<OrderEntity> clientOrder = MyDao.getOrderListByClient(pass);
                    Map<OrderEntity,ArrayList<String>> clientOrderString = new HashMap<OrderEntity,ArrayList<String>> ();
                    
                    Map<OrderEntity, ArrayList<ArrayList<String>>> clientline = new HashMap<OrderEntity, ArrayList<ArrayList<String>>>(); 
                    
                    
                    for ( OrderEntity ord : clientOrder){           
                        
                        ArrayList<String> tmpord = new ArrayList<String>();
                        
                        tmpord.add("date d'envoie : "+String.valueOf(ord.getDateSent()));
                        tmpord.add("frais de port : "+String.valueOf(ord.getPort())+"€");
                        tmpord.add("receveur : "+String.valueOf(ord.getReceiver()));
                        tmpord.add("adresse : "+String.valueOf(ord.getAddress()));
                        tmpord.add("ville : "+String.valueOf(ord.getCity()));
                        tmpord.add("région : "+String.valueOf(ord.getRegion()));
                        tmpord.add("code postal :"+String.valueOf(ord.getZipcode()));
                        tmpord.add("pays : "+String.valueOf(ord.getCountry()));
                        tmpord.add("réduction :"+String.valueOf(ord.getDiscount())+"%");
                        
                        List<LineEntity> listlinetmp = MyDao.getLineListByOrder(ord);
                        float prixtoto = ord.getPort();
                        
                        for (LineEntity line : listlinetmp){
                            prixtoto+=line.getProduct().getPrice()*line.getQty();
                        }
                        
                        tmpord.add("prix total : "+prixtoto);
                        
                        clientOrderString.put(ord,tmpord);
//                      

                        ArrayList<LineEntity> llc =(ArrayList<LineEntity>) MyDao.getLineListByOrder(ord);
                        ArrayList<ArrayList<String>> listlineString = new ArrayList<ArrayList<String>>(); 
                        
                        for (LineEntity tmpline : llc){
                            ArrayList<String> tmp = new ArrayList<String>(); 
                            tmp.add(tmpline.getProduct().getName());
                            tmp.add(String.valueOf(tmpline.getQty()));

                            listlineString.add(tmp);
                        }
                        
                        
                        clientline.put(ord,listlineString );
                    }
                    
                    //      début ajout d'une commande
        

                    if(request.getParameter("feeAddOrder")!=null && request.getParameter("discountAddOrder")!=null ){

                        Random rng = new Random();
                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        java.util.Date d1 = new java.util.Date();
                        java.sql.Date d2 = new java.sql.Date(d1.getTime()+43200000);       
                        float feeAddOrder = rng.nextFloat()*450+50;
                        String receiverAddOrder = user.getContact();
                        String addressAddOrder = user.getAddress();
                        String cityAddOrder = user.getCity();
                        String regionAddOrder = user.getRegion();
                        String zipcodeAddOrder = user.getZipCode();
                        String countryAddOrder = user.getCountry();
                        float discountAddOrder = rng.nextFloat()*10;


                        if (user!=null  && receiverAddOrder!=null && addressAddOrder!=null && cityAddOrder!=null && regionAddOrder!=null && zipcodeAddOrder!=null && countryAddOrder!=null  ){
                            OrderCurrent = new OrderEntity(user, d2, feeAddOrder, receiverAddOrder, addressAddOrder, cityAddOrder, regionAddOrder, zipcodeAddOrder, countryAddOrder, discountAddOrder);                
                            session.setAttribute("currentorder", OrderCurrent);
                        }
                    }

                    //      fin d'ajout d'une commande

                    session.setAttribute("orderString",clientOrderString);
                    session.setAttribute("order",clientOrder);
                    session.setAttribute("line", clientline);
                    
                    session.setAttribute("cart_list", new ArrayList<ArrayList<String>>());
                }
//              si c'est l'admin 
                else{
                               
//                  ajout de toute les commandes 
                    List<OrderEntity> allOrder = MyDao.getOrdersList();
                    ArrayList<ArrayList<String>> allOrderString = new ArrayList<ArrayList<String>>();
                    
                    for (OrderEntity ord : allOrder){
                        ArrayList<String> tmpord = new ArrayList<String>();
                        tmpord.add("numéro de la commade : "+String.valueOf(ord.getNum()));
                        tmpord.add("client : "+String.valueOf(ord.getClient()));
                        tmpord.add("date d'envoie : "+String.valueOf(ord.getDateSent()));
                        tmpord.add("frais de port : "+String.valueOf(ord.getPort()));
                        tmpord.add("receiver : "+String.valueOf(ord.getReceiver()));
                        tmpord.add("adresse : "+String.valueOf(ord.getAddress()));
                        tmpord.add("ville : "+String.valueOf(ord.getCity()));
                        tmpord.add("région : "+String.valueOf(ord.getRegion()));
                        tmpord.add("zip code : "+String.valueOf(ord.getZipcode()));
                        tmpord.add("pays : "+String.valueOf(ord.getCountry()));
                        tmpord.add("réduction : "+String.valueOf(ord.getDiscount()));
                        
                        List<LineEntity> listlinetmp = MyDao.getLineListByOrder(ord);
                        float prixtoto = ord.getPort();
                        
                        for (LineEntity line : listlinetmp){
                            prixtoto+=line.getProduct().getPrice()*line.getQty();
                        }
                        
                        tmpord.add("prix total : "+prixtoto);
                        
                        allOrderString.add(tmpord);
                    }
                    
                    session.setAttribute("order",allOrder);
                    
//                  ajout de tous les produits
                }
            }
        }
//      fin de la connexion


//      debut deco

        if (request.getParameter("deco")!=null){
            System.out.println("ezra"+user);
                user=null;
                session.setAttribute("usrname",null);
                session.setAttribute("pass",null);
                session.setAttribute("usr",null);
                session.setAttribute("infoClient",null);
                session.setAttribute("order",null);
                
                session.setAttribute("currentorder",null);
                
                session.setAttribute("log",false);
                session.setAttribute("client", false);
                session.setAttribute("admin",false);
                
             
        }

//      fin deco







//      début de modification des données personnelle
        String societe = request.getParameter("societe");
        String contact = request.getParameter("contact");
        String fonction = request.getParameter("fonction");
        String adresse = request.getParameter("adresse");
        String ville = request.getParameter("ville");
        String region = request.getParameter("region");
        String code_postal = request.getParameter("cp");
        String pays = request.getParameter("pays");
        String telephone = request.getParameter("tel");
        String fax = request.getParameter("fax");
        if (societe!=null && contact!=null && fonction!=null && adresse!=null && ville!=null && code_postal!=null && pays!=null && telephone!=null && fax!=null ){    
            ClientEntity newclient = new ClientEntity(user.getCode(), societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax);
            System.out.println("new client : "+newclient);
            System.out.println("old client : "+user);
                    
            MyDao.updateClient(user,newclient);
            user=newclient;
            session.setAttribute("usr",user);
            ArrayList<String> infoclientString = new ArrayList<String>();
            
            infoclientString.add(user.getCompany());
            infoclientString.add(user.getContact());
            infoclientString.add(user.getRole());
            infoclientString.add(user.getAddress());
            infoclientString.add(user.getCity());
            infoclientString.add(user.getRegion());
            infoclientString.add(user.getZipCode());
            infoclientString.add(user.getCountry());
            infoclientString.add(user.getPhone());
            infoclientString.add(user.getFax());
            
            session.setAttribute("infoClient",infoclientString);

        }
//      fin de modification des données personnelle


 
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }



}
