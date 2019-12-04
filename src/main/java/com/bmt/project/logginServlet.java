/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;

import com.bmt.project.model.ClientEntity;
import com.bmt.project.model.OrderEntity;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bruno
 */
@WebServlet(name = "logginServlet", urlPatterns = {"/logginServlet"})
public class logginServlet extends HttpServlet {



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
        
        
        
        
//connection a la base de donnée depuis un client//   
            try {
                String log = request.getParameter("login");
                String pass = request.getParameter("password");

                if (log == null | pass == null) {
                    throw new Exception("le login ou le password sont null");
                }

//                ClientEntity clientlogged  = new MyDa0.login(log,pass);
//                List<OrderEntity> listOrder = myDao.getOrdersList();
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
///////////////////////////////////
        this.getServletContext().getRequestDispatcher("/WEB-INF/products_presentation.jsp").forward(request, response);
    }



}
