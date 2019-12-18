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
import java.util.Random;
import javax.servlet.ServletException;
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
        request.setCharacterEncoding("UTF-8");
        ClientEntity user = (ClientEntity) session.getAttribute("usr");
        OrderEntity OrderCurrent = (OrderEntity) session.getAttribute("currentorder");
        DAO MyDao = (DAO) session.getAttribute("mydao");
        ArrayList<LineEntity> lineListCurrent = (ArrayList<LineEntity>) session.getAttribute("currentlinelist");


//      debut suppression line

        if (request.getParameter("supProduitRef") != null) {
            int numprod = Integer.parseInt(request.getParameter("supProduitRef"));

            for (int i = 0; i < lineListCurrent.size(); i++)
                if (lineListCurrent.get(i).getProduct() == MyDao.getProductByCode(numprod)) {
                    lineListCurrent.remove(i);
                    session.setAttribute("currentlinelist", lineListCurrent);
                    break;
                }
        }
//      fin suppression line

//      debut modifier line
        if (request.getParameter("lineQuantityNew") != null && request.getParameter("numProdNew") != null && request.getParameter("lineQuantityOld") != null) {

            int numProdUpdateLineNew = Integer.parseInt(request.getParameter("numProd"));
            int quantityUpdateLineNew = Integer.parseInt(request.getParameter("lineQuantityNew"));

            int quantityUpdateLineOld = Integer.parseInt(request.getParameter("lineQuantityOld"));

            LineEntity oldLine = new LineEntity(OrderCurrent, MyDao.getProductByCode(numProdUpdateLineNew), quantityUpdateLineOld);
            LineEntity newLine = new LineEntity(OrderCurrent, MyDao.getProductByCode(numProdUpdateLineNew), quantityUpdateLineNew);

            for (LineEntity line : lineListCurrent)
                if (line.getProduct() == newLine.getProduct())
                    line = newLine;
        }
//      fin modifier line 

//      debut suppression line
        if (request.getParameter("supProduitRef") != null) {

            int numprod = Integer.parseInt(request.getParameter("supProduitRef"));

            for (int i = 0; i < lineListCurrent.size(); i++)
                if (lineListCurrent.get(i).getProduct() == MyDao.getProductByCode(numprod)) {
                    lineListCurrent.remove(i);
                    session.setAttribute("currentlinelist", lineListCurrent);
                    break;
                }
        }
//      fin suppression line

//      debut modifier line
        if (request.getParameter("quantity") != null && request.getParameter("refProduit") != null && request.getParameter("newQuantity") != null) {

            int numProdUpdateLineNew = Integer.parseInt(request.getParameter("refProduit"));
            int quantityUpdateLineNew = Integer.parseInt(request.getParameter("newQuantity"));

            int quantityUpdateLineOld = Integer.parseInt(request.getParameter("quanrity"));

            LineEntity oldLine = new LineEntity(OrderCurrent, MyDao.getProductByCode(numProdUpdateLineNew), quantityUpdateLineOld);
            LineEntity newLine = new LineEntity(OrderCurrent, MyDao.getProductByCode(numProdUpdateLineNew), quantityUpdateLineNew);

            for (LineEntity line : lineListCurrent){
                if (line.getProduct() == newLine.getProduct()){
                    line = newLine;
                }
            }
            session.setAttribute("currentlinelist", lineListCurrent);
        }
//      fin modifier line 

//      debut confirmer commande 
        if (request.getParameter("confirmerCommande") != null){
            if (request.getParameter("confirmerCommande").equals("true")) {
                MyDao.addOrder(OrderCurrent);
                for (LineEntity line : lineListCurrent)
                    MyDao.addLineToCommand(line);
                
                ArrayList<LineEntity> nono= new ArrayList<>();
                session.setAttribute("currentlinelist",nono);
                
                
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
            
            
            
        }
//      fin confirmer commande 







        this.getServletContext().getRequestDispatcher("/WEB-INF/Shop.jsp").forward(request, response);
    }

}
