/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;

import com.bmt.project.model.CategoryEntity;
import com.bmt.project.model.DAO;
import com.bmt.project.model.DataSourceFactory;
import com.bmt.project.model.OrderEntity;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedago-
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO MyDao = new DAO(DataSourceFactory.getDataSource());
        
        List<CategoryEntity> cat = MyDao.getCategoriesList();
        
        List<String> nomcat = new ArrayList<String>();
        for(CategoryEntity c : cat ){
            nomcat.add(c.getWording());
        }
        
        request.setAttribute("categories_list",nomcat);

        this.getServletContext().getRequestDispatcher("/WEB-INF/main_jsp.jsp").forward(request, response);
            
        
        try  {
            
            //DAO MyDao = new DAO(DataSourceFactory.getDataSource());
            
            
//connection a la base de donnée depuis un client//   
            try {
                String log = request.getParameter("login");
                String pass = request.getParameter("password");
                
                
                
                if (log == null | pass == null) {
                    throw new Exception("le login ou le password sont null");
                }
                
                //ClientEntity clientlogged  = new MyDa0.login(log,pass);
                //List<OrderEntity> listOrder = myDao.getOrdersList();
                

            }catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
///////////////////////////////////




// modifier les informations personnelles//
            try {
                
                //String code = clientlogged.getCode();
                //String societe = clientlogged.getCompany();
                String contact = request.getParameter("contact");
               
                String fonction = request.getParameter("fonction");
               
                String adresse = request.getParameter("adresse");
                
                String ville = request.getParameter("ville");
             
                String region = request.getParameter("region");
               
                String code_postal = request.getParameter("code_postal");
               
                String pays = request.getParameter("pays");
                
                String telephone = request.getParameter("telephone");
                
                String fax = request.getParameter("fax");
                
                //new ClientEntity newclient = new ClientEntity(code,societe,contact,fonction,adresse,ville,region,code_postal,pays,telephone,fax);
                
                //MyDao.updateClient(old,newclient);
                
            }catch (Exception e) {
                out.printf("Erreur pendant la modif de données perso");
            }


//ajouter des commandes// 

            try {

                String datesend = request.getParameter("receptiondate");

                String fee = request.getParameter("fee");
                
                String destination = request.getParameter("destination");
                
                String add_livraison  = request.getParameter("add_livraison");
                
                String city_livraison = request.getParameter("city_livraison");
                
                String region_livraison  =request.getParameter("region_livraison");
                
                String zipCode = request.getParameter("zipCode");
                
                String country_livraison = request.getParameter("country_livraison");
                
                String remise = request.getParameter("remise");
                
                //OrderEntity neworder = new OrderEntity(clientlogged,datesend,fee,destionation,add_livrasion,city_livrasion,region_livrasion,zipCode,country_livrasion,remise);   
                
                //myDao.addOrder(neworder);
                
                
                
                
                //MyDao.addCommande(neworder);
                
            }catch (Exception e) {
                out.printf("erreur pendant l'ajout d'une commande");
            }
    

            



            
/////////////////////////////////////


////supprimer des commandes 
            try{
                String num = request.getParameter("num");
                
                //myDao.deleteOrder(num);
            
            }catch (Exception e){
                out.printf("erreur lors de la suppression d'une commande");
            }

/////////////////////////////////////



////editer des commandes
            try {
                
                String datesend = request.getParameter("receptiondate");

                String fee = request.getParameter("fee");
                
                String destination = request.getParameter("destination");
                
                String add_livraison  = request.getParameter("add_livraison");
                
                String city_livraison = request.getParameter("city_livraison");
                
                String region_livraison  =request.getParameter("region_livraison");
                
                String zipCode = request.getParameter("zipCode");
                
                String country_livraison = request.getParameter("country_livraison");
                
                String remise = request.getParameter("remise");
                
                
                
                //OrderEntity neworder = new OrderEntity(clientlogged,datesend,fee,destionation,add_livrasion,city_livrasion,region_livrasion,zipCode,country_livrasion,remise);   
                
                //myDao.editOrder(oldorder,neworder);
                
            }catch( Exception e){
                out.printf("erreur lors de la modification d'une commande");
            }



/////////////////////////////////////






        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
        
    }
}
