/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;

import com.bmt.project.model.DAO;
import com.bmt.project.model.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        this.getServletContext().getRequestDispatcher("WEB-INF/main_jsp.jsp").forward(request, response);
            
        
        try (PrintWriter out = response.getWriter()) {
            
            //DAO MyDao = new DAO(DataSourceFactory.getDataSource());
            
            
//connection a la base de donnée//   
            try {
                String log = request.getParameter("login");
                String pass = request.getParameter("password");
               
                if (log == null | pass == null) {
                    throw new Exception("le login ou le password sont null");
                }
                
                //MyDa0.Login(log,pass);

                

            }catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
///////////////////////////////////




// modifier les informations personnelles//
            try {
                ArrayList<String> infoPerso = new ArrayList<String>();
                
                String contact = request.getParameter("contact");
                infoPerso.add(contact);
                String fonction = request.getParameter("fonction");
                infoPerso.add(fonction);
                String adresse = request.getParameter("adresse");
                infoPerso.add(adresse);
                String ville = request.getParameter("ville");
                infoPerso.add(ville);
                String region = request.getParameter("region");
                infoPerso.add(region);
                String code_postal = request.getParameter("code_postal");
                infoPerso.add(code_postal);
                String pays = request.getParameter("pays");
                infoPerso.add(pays);
                String telephone = request.getParameter("telephone");
                infoPerso.add(telephone);
                String fax = request.getParameter("fax");
                infoPerso.add(fax);
                
                Boolean flag = true;
                for (int i = 0 ; i < infoPerso.size() ; i++){
                    if (infoPerso.get(i)==null)
                        flag=false;                    
                }
                if (flag == false ){
                    throw new Exception("une des informations est null");
                }
                //MyDao.updateProfile(infoPerso);
                
            }catch (Exception e) {
                out.printf("Erreur pendant la modif de données perso");
            }


//ajouter des commandes// 

            try {
                //String = MyDao.currentUser();
                Date ajd = new Date();
                
                //MyDao.addCommande();
                
            }catch (Exception e) {
                out.printf("erreur pendant l'ajout d'une commande");
            }
    

            



            
///////////////////////////////////


//editer des commandes




///////////////////////////////////


//supprimer des commandes 




///////////////////////////////////




        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
        
    }
}
