/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;


import com.bmt.project.model.CategoryEntity;
import com.bmt.project.model.ClientEntity;
import com.bmt.project.model.DAO;
import com.bmt.project.model.DataSourceFactory;
import com.bmt.project.model.LineEntity;
import com.bmt.project.model.OrderEntity;
import com.bmt.project.model.ProductEntity;
import java.io.IOException;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bruno-
 */
public class MainServlet extends HttpServlet {

    
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private DAO MyDao;
    private ClientEntity user;
    public HttpSession session;
    private OrderEntity OrderCurrent;
    private ArrayList<LineEntity> lineListCurrent;
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        this.MyDao= new DAO(DataSourceFactory.getDataSource());
        HttpSession session;
        
    }

  
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response); //To change body of generated methods, choose Tools | Template
        
        
        
        request.getRequestDispatcher("/WEB-INF/products_presentation.jsp").forward(request, response); 
    }

    
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(true);
        
        session.setAttribute("log", false);
        
        //liste des catégories et map des produits
        List<CategoryEntity> cat = MyDao.getCategoriesList();
        List<ProductEntity> prod = MyDao.getProductsList();
        
        
        

        List<String> nomcat = new ArrayList<String>();

        Map<String, ArrayList<String>> mapProduct = new HashMap<>();

        
        Map<String, ArrayList<String>> mapInfoProduct = new HashMap<>();


        for (CategoryEntity c : cat) {

            nomcat.add(c.getWording());
            mapProduct.put(c.getWording(), new ArrayList<String>());

        }
        Map<String , ProductEntity> products = new HashMap<>();
        
        for (ProductEntity p : prod) {

            products.put(p.getName(), p);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp = mapProduct.get(p.getCategory().getWording());

            tmp.add(p.getName());
            mapProduct.put(p.getCategory().getWording(), tmp);
            
            
            ArrayList<String> prodTemp = new ArrayList<>();
            prodTemp.add(String.valueOf(p.getReference()));
            prodTemp.add(String.valueOf(p.getName()));
            prodTemp.add(String.valueOf(p.getProvider()));
            prodTemp.add(String.valueOf(p.getCategory()));
            prodTemp.add(String.valueOf(p.getQtyPerPackage()));
            prodTemp.add(String.valueOf(p.getPrice()));
            prodTemp.add(String.valueOf(p.getStock()));
            prodTemp.add(String.valueOf(p.getOrdered()));
            prodTemp.add(String.valueOf(p.getRefill()));
            prodTemp.add(String.valueOf(p.isAvailable()));
            
            mapInfoProduct.put(p.getName(), prodTemp);


        }
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("product_map", mapProduct);
        request.setAttribute("categories_list", nomcat);
        //fin de la liste des catégories et de la map des produits
        request.setAttribute("product_information", mapInfoProduct);
        
        
        
        String log =request.getParameter("login");
        String pass=request.getParameter("password");


//      connexion
        if (log != null && pass != null){
            
            user = MyDao.login(log,pass);
        
//          verification si la connection est possible
            if (( user!=null )|( log=="admin" && pass=="admin") ){   
                
                //admin LOG = Maria Anders, PASS = ALFKI 
                //random LOG = Ana Trujillo  , PASS = ANATR
                boolean admin=false;
                boolean client=false;
                
                ArrayList<String> infoclientString = new ArrayList<String>();
                
                if (log=="admin" && pass=="admin"){
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

                session.setAttribute("usrname", log);
                session.setAttribute("pass",pass);

                session.setAttribute("log", true);
                session.setAttribute("client", client);
                session.setAttribute("admin",admin);
                
//              debut  faut construire la liste des lignes pour matthieu                 
                ArrayList<String> salepd= new ArrayList<String>();
                salepd.add("so");
                salepd.add("caca");
                salepd.add("so");
                salepd.add("caca");
//             fin  faut construire la liste des lignes pour matthieu                   
                
//             debut   puis faut l'ajouter a cette liste
                ArrayList<ArrayList<String>> grospd = new ArrayList<ArrayList<String>>();
                grospd.add(salepd);
                
//              fin  puis faut l'ajouter a cette liste

                session.setAttribute("cart_list",grospd);
                session.setAttribute("infoClient",infoclientString);

//              si c'est un client
                if (client){
                    System.out.println("client");
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
                    
                    session.setAttribute("orderString",clientOrderString);
                    session.setAttribute("order",clientOrder);
                    session.setAttribute("line", clientline);
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
                        
                        allOrderString.add(tmpord);
                    }
                    
                    session.setAttribute("order",allOrder);
                    
//                  ajout de tous les produits
                    
                    
                    
                    
                }
                
            }
        }
//      fin de la connexion

//      début de modification des données personnelle
        String code = request.getParameter("code");
        String societe = request.getParameter("company");
        String contact = request.getParameter("contact");
        String fonction = request.getParameter("fonction");
        String adresse = request.getParameter("adresse");
        String ville = request.getParameter("ville");
        String region = request.getParameter("region");
        String code_postal = request.getParameter("code_postal");
        String pays = request.getParameter("pays");
        String telephone = request.getParameter("telephone");
        String fax = request.getParameter("fax");
        if (code!= null && societe!=null && contact!=null && fonction!=null && adresse!=null && ville!=null && region !=null && code_postal!=null && pays!=null && telephone!=null && fax!=null ){    
            ClientEntity newclient = new ClientEntity(code, societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax);
            
            MyDao.updateClient(user,newclient);
            user=newclient;
        }
//      fin de modification des données personnelle

//      début ajout d'une commande
        
        //user
        //getTime()+43200000;// on rajoute 5 jours a la date d'aujourd'hui
        
        if(request.getParameter("feeAddOrder")!=null && request.getParameter("discountAddOrder")!=null ){

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            java.util.Date d1 = new java.util.Date();
            java.sql.Date d2 = new java.sql.Date(d1.getTime()+43200000);       
            float feeAddOrder = Float.valueOf(request.getParameter("feeAddOrder"));
            String receiverAddOrder = request.getParameter("receiverAddOrder");
            String addressAddOrder = request.getParameter("addressAddOrder");
            String cityAddOrder = request.getParameter("cityAddOrder");
            String regionAddOrder = request.getParameter("regionAddOrder");
            String zipcodeAddOrder = request.getParameter("zip_codeAddOrder");
            String countryAddOrder = request.getParameter("countryAddOrder");
            float discountAddOrder = Float.valueOf(request.getParameter("discountAddOrder"));


            if (user!=null  && receiverAddOrder!=null && addressAddOrder!=null && cityAddOrder!=null && regionAddOrder!=null && zipcodeAddOrder!=null && countryAddOrder!=null  ){
                OrderCurrent = new OrderEntity(user, d2, feeAddOrder, receiverAddOrder, addressAddOrder, cityAddOrder, regionAddOrder, zipcodeAddOrder, countryAddOrder, discountAddOrder);                
//                MyDao.addOrder(new OrderEntity(user,d2, feeAddOrder, receiverAddOrder, addressAddOrder, cityAddOrder, regionAddOrder, zipcodeAddOrder, countryAddOrder, discountAddOrder));
            }
    }
    
//      fin d'ajout d'une commande

//      debut ajout ligne
        if ( request.getParameter("productLine")!=null && request.getParameter("quantityLine")!=null){
            int productLine = -1;
            int quantityLine = -1;
            productLine= Integer.parseInt(request.getParameter("productLine"));
            quantityLine= Integer.parseInt(request.getParameter("quantityLine"));

            if (productLine>=0 && quantityLine>=0){
                LineEntity newline = new LineEntity(OrderCurrent,MyDao.getProductByCode(productLine),quantityLine);
                lineListCurrent.add(newline);
            }
        }
        
//      fin ajout ligne 

//      debut modifier line

        if (request.getParameter("lineQuantityNew")!=null && request.getParameter("numProdNew")!=null && request.getParameter("lineQuantityOld")!=null){

            int numProdUpdateLineNew = Integer.parseInt(request.getParameter("numProd"));
            int quantityUpdateLineNew = Integer.parseInt(request.getParameter("lineQuantityNew"));
            
            int quantityUpdateLineOld = Integer.parseInt(request.getParameter("lineQuantityOld"));
            
            LineEntity oldLine = new LineEntity(OrderCurrent,MyDao.getProductByCode(numProdUpdateLineNew),quantityUpdateLineOld);
            LineEntity newLine = new LineEntity (OrderCurrent,MyDao.getProductByCode(numProdUpdateLineNew),quantityUpdateLineNew);
            
            
            for (LineEntity line : lineListCurrent){
                if (line.getProduct()==newLine.getProduct()){
                    line=newLine;
                }
            }   
        }
//      fin modifier line 


//      debut confirmer commande 

        if (request.getParameter("confirmerCOmmande")=="true"){
            MyDao.addOrder(OrderCurrent);
            for (LineEntity line : lineListCurrent ){
                MyDao.addLineToCommand(line);
            }
        }
//      fin confirmer commande 

        request.getRequestDispatcher("/WEB-INF/products_presentation.jsp").forward(request, response);


    }
}
