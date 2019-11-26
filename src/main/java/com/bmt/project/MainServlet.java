/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;

import com.bmt.project.model.OrderEntity;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/main_jsp.jsp").forward(request, response);
            
        
        try  {
            
            //DAO MyDao = new DAO(DataSourceFactory.getDataSource());
            
            
//connection a la base de donnée//   
            try {
                String log = request.getParameter("login");
                String pass = request.getParameter("password");
                
                
                
                if (log == null | pass == null) {
                    throw new Exception("le login ou le password sont null");
                }
                
                //ClientEntity clientlogged  = new MyDa0.login(log,pass);

                

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


                
                Date ajd = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dat = dateFormat.format(ajd);
                
//////                
//////                faut rajouter la date de livraison et je sais pas comment la 
//////                faire (combien de jours si on peut choisir la date ou si 
//////                la date est attribué automatiquement)
//////                
                
                Random rng = new Random();
                int temp = rng.nextInt(1_0000_00); 

                
                OrderEntity neworder = new OrderEntity();   
                
                
                
                
                
                
                //MyDao.addCommande(neworder);
                
            }catch (Exception e) {
                out.printf("erreur pendant l'ajout d'une commande");
            }
    

            



            
/////////////////////////////////////




////editer des commandes




/////////////////////////////////////

////supprimer des commandes 




/////////////////////////////////////




        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
        
    }
}
