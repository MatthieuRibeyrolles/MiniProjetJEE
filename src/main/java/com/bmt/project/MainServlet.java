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

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private DAO MyDao;
    private ClientEntity user;
    public HttpSession session;
    private OrderEntity OrderCurrent;
    private ArrayList<LineEntity> lineListCurrent;

    @Override
    public void init() throws ServletException {
        super.init();
        this.MyDao = new DAO(DataSourceFactory.getDataSource());
        lineListCurrent = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(true);
        session.setAttribute("mydao", MyDao);

        if (session.getAttribute("currentorder")==null){
            session.setAttribute("usr", null);  
            session.setAttribute("currentorder", null);
            session.setAttribute("currentlinelist", new ArrayList<>());
        }else{
            OrderCurrent = (OrderEntity) session.getAttribute("currentorder");                    
        }
   
        //liste des catégories et map des produits
        List<CategoryEntity> cat = MyDao.getCategoriesList();
        List<ProductEntity> prod = MyDao.getProductsList();

        List<String> nomcat = new ArrayList<>();

        Map<String, ArrayList<String>> mapProduct = new HashMap<>();

        Map<String, ArrayList<String>> mapInfoProduct = new HashMap<>();

        for (CategoryEntity c : cat) {

            nomcat.add(c.getWording());
            mapProduct.put(c.getWording(), new ArrayList<String>());

        }
        Map<String, ProductEntity> products = new HashMap<>();

        for (ProductEntity p : prod) {

            products.put(p.getName(), p);
            ArrayList<String> tmp;
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

//      debut renvoie des infos dans le panier
        if (request.getParameter("refProduit") != null && request.getParameter("quantity") != null) {
//              debut  faut construire la liste des lignes pour matthieu                 
            ArrayList<LineEntity> lineList = (ArrayList<LineEntity>) session.getAttribute("currentlinelist");

            int refprod = Integer.parseInt(request.getParameter("refProduit"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            LineEntity encour = new LineEntity((OrderEntity) session.getAttribute("currentorder"), MyDao.getProductByCode(refprod), quantity);

            ArrayList<String> infoline = new ArrayList<>();
            infoline.add(encour.getProduct().getName()); //nom
            infoline.add(String.valueOf(encour.getProduct().getReference())); //ref
            infoline.add(String.valueOf(encour.getQty())); //quantité
            infoline.add(String.valueOf(encour.getProduct().getPrice() * encour.getQty())); // prix toto
//             fin  faut construire la liste des lignes pour matthieu                   

//             debut   puis faut l'ajouter a cette liste
            ArrayList<ArrayList<String>> maj = (ArrayList<ArrayList<String>>) session.getAttribute("cart_list");
            maj.add(infoline);

//              fin  puis faut l'ajouter a cette liste
            session.setAttribute("cart_list", maj);

        } else {
        }

//      fin renvoie des infos dans le panier      
//      debut ajout ligne                        
        if (request.getParameter("refProduit") != null && request.getParameter("quantity") != null) {
            int productLine = -1;
            int quantityLine = -1;
            productLine = Integer.parseInt(request.getParameter("refProduit"));
            quantityLine = Integer.parseInt(request.getParameter("quantity"));

            if (productLine >= 0 && quantityLine >= 0) {
                LineEntity newline = new LineEntity(OrderCurrent, MyDao.getProductByCode(productLine), quantityLine);
                lineListCurrent.add(newline);
                session.setAttribute("currentlinelist",lineListCurrent);
                
            }
        }

//      fin ajout ligne 

        request.getRequestDispatcher("/WEB-INF/products_presentation.jsp").forward(request, response);

    }
}
