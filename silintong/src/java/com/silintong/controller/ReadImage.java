/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author juan.karsten
 */
@WebServlet(name = "ReadImage", urlPatterns = {"/ReadImage"})
public class ReadImage extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            {
//        PrintWriter out=null;
        try {
//            out = response.getWriter();
            String filename=request.getParameter("filename");
                //your image servlet code here
                response.setContentType("image/jpeg");

                // Set content size
                File file = new File(filename);
                response.setContentLength((int)file.length());

                // Open the file and output streams
                FileInputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream();

                // Copy the contents of the file to the output stream
                byte[] buf = new byte[1024];
                int count = 0;
                while ((count = in.read(buf)) >= 0) {
                    out.write(buf, 0, count);
                }
                in.close();
                out.close();
        } catch (IOException ex) {
//            response.setContentType("text/HTML");
//            out.print(ex.toString());
        } finally {   
            
//            out.close();
        }
    }
    
    public byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File fnew=new File(ImageName);
        BufferedImage originalImage=ImageIO.read(fnew);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "png", baos );
        byte[] imageInByte=baos.toByteArray();
        return imageInByte;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
