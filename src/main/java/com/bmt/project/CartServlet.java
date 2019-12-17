/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;

import com.bmt.project.model.DAO;
import com.bmt.project.model.LineEntity;
import com.bmt.project.model.OrderEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class CartServlet extends HttpServlet {


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
        
        OrderEntity OrderCurrent = (OrderEntity) session.getAttribute("currentorder");
        DAO MyDao = (DAO) session.getAttribute("mydao");
        ArrayList<LineEntity> lineListCurrent =  (ArrayList<LineEntity>) session.getAttribute("currentlinelist");
        
        

//      debut suppression line

        if (request.getParameter("supProduitRef")!=null){
            System.out.println("petit connard repond");
            int numprod = Integer.parseInt(request.getParameter("supProduitRef"));

            for (int i = 0 ; i < lineListCurrent.size() ; i++){
                if(lineListCurrent.get(i).getProduct()==MyDao.getProductByCode(numprod)){
                    lineListCurrent.remove(i);
                    session.setAttribute("currentlinelist", lineListCurrent);
                    break;
                }
            }
        }
//      fin suppression line

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
       this.getServletContext().getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
    }

}
