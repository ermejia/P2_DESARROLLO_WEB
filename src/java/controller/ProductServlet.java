/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author mejia
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   Product product;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Producto</title>");            
            out.println("</head>");
            out.println("<body>");
            product = new Product
                   (Integer.valueOf(request.getParameter("txt_idProducto")), 
                    Integer.valueOf(request.getParameter("ChooseBrand")), 
                    Integer.valueOf(request.getParameter("txt_existencia")), 
                    request.getParameter("txt_nombre"),
                    request.getParameter("txt_descripcion"),
                    request.getParameter("txt_precio_costo"),
                    request.getParameter("txt_precio_venta"));
           
             if ("Add".equals(request.getParameter("btn_agregar"))){
                if (product.Add()>0){
                response.sendRedirect("index.jsp");
                }else{
                out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
                out.println("<a href='index.jsp'>Regresar...</a>");
                }
             }
            
            // Boton modificar 
             if ("Modify".equals(request.getParameter("btn_modificar"))){
                if (product.Modify()>0){
                response.sendRedirect("index.jsp");            
                }else{
                out.println("<h1> xxxxxxx No se Modifico xxxxxxxxxxxx </h1>");
                out.println("<a href='index.jsp'>Regresar...</a>");
                }
             }
            
            // Boton eliminar 
            if ("Delete".equals(request.getParameter("btn_eliminar"))){
                if (product.Delete()>0){
                response.sendRedirect("index.jsp");             
                }else{
                out.println("<h1> xxxxxxx No se Elimino xxxxxxxxxxxx </h1>");
                out.println("<a href='index.jsp'>Regresar...</a>");
                }
             }
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
