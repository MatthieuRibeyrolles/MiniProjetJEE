/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project;

import com.bmt.project.model.DAO;
import com.bmt.project.model.DataSourceFactory;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thibault
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    private DAO MyDao;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("com.bmt.project.AdminServlet.init()");
        this.MyDao = new DAO(DataSourceFactory.getDataSource());
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        System.out.println("com.bmt.project.AdminServlet.processRequest()");

        String d1Str = request.getParameter("date1");
        String d2Str = request.getParameter("date2");
        
        Date startDate = (d1Str != null && !d1Str.isEmpty()) ? Date.valueOf(d1Str) : Date.valueOf("1995-01-31");
        Date endDate = (d2Str != null && !d2Str.isEmpty()) ? Date.valueOf(d2Str) : Date.valueOf("1996-05-30");

        Map<String, Map<String, Float>> m = new HashMap<>();

        m.put("byCountry", this.MyDao.getRevenuesByCountryBetweenDates(startDate, endDate));
        m.put("byClient", this.MyDao.getRevenuesByClientBetweenDates(startDate, endDate));
        m.put("byCategory", this.MyDao.getRevenuesByCategoryBetweenDates(startDate, endDate));

        String json = new Gson().toJson(m);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("com.bmt.project.AdminServlet.doGet()");
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("com.bmt.project.AdminServlet.doPost()");
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        System.out.println("com.bmt.project.AdminServlet.getServletInfo()");
        return "Short description";
    }// </editor-fold>

}
