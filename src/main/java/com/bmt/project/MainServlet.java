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
import com.bmt.project.model.OrderEntity;
import com.bmt.project.model.ProductEntity;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    
    
    private DAO MyDao;
    private ClientEntity user;
    public HttpSession session;
    
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
                
                boolean admin=false;
                boolean client=false;
                
                if (log=="admin" && pass=="admin"){
                    admin=true;
                }else{
                    client=true;
                }

                session.setAttribute("usrname", log);
                session.setAttribute("pass",pass);

                session.setAttribute("log", true);
                session.setAttribute("client", client);
                session.setAttribute("admin",admin);
                
               

//              si c'est un client
                if (client){
                    List<OrderEntity> clientOrder = MyDao.getOrderListByClient(pass);
                    ArrayList<ArrayList<String>> clientOrderString = new ArrayList<ArrayList<String>>();
                    
                    for ( OrderEntity ord : clientOrder){
                        ArrayList<String> tmpord = new ArrayList<String>();
                        tmpord.add("date d'envoie : "+String.valueOf(ord.getDateSent()));
                        tmpord.add("frais de port : "+String.valueOf(ord.getPort()));
                        tmpord.add("receiver : "+String.valueOf(ord.getReceiver()));
                        tmpord.add("adresse : "+String.valueOf(ord.getAddress()));
                        tmpord.add("ville : "+String.valueOf(ord.getCity()));
                        tmpord.add("région : "+String.valueOf(ord.getRegion()));
                        tmpord.add("code zip :"+String.valueOf(ord.getZipcode()));
                        tmpord.add("pays : "+String.valueOf(ord.getCountry()));
                        tmpord.add("réduction :"+String.valueOf(ord.getDiscount()));
                        
                        clientOrderString.add(tmpord);
                        
                    }
                    
                    session.setAttribute("order",clientOrderString);

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
                    
                    session.setAttribute("order",allOrderString);
                    
//                  ajout de tous les produits
                    
                    
                    
                    
                }
                
            }
        }
//      fin de la connexion



//        String nomprod = request.getParameter("nomprod");
//        if (nomprod != null){
//            
//            request.setAttribute("rienafoutre",);
//        }
        
//        Product productBean = new Product(products);
//        //
//        request.setAttribute("productBean", productBean);
        

        request.getRequestDispatcher("/WEB-INF/products_presentation.jsp").forward(request, response);


    }
}
