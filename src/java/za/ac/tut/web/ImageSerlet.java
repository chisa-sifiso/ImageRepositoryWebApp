/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import za.ac.tut.bl.ImageFacadeLocal;
import za.ac.tut.model.Image;
import static za.ac.tut.model.Image_.image_source;

/**
 *
 * @author sifis
 */
@MultipartConfig
public class ImageSerlet extends HttpServlet {
  @EJB ImageFacadeLocal ifl;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        Part part = request.getPart("file");
        
        byte[] imag_source = new byte[0];   
        if(part.getContentType()!=null){
         InputStream  imagePart = part.getInputStream();
         //convect image to byte array
         ByteArrayOutputStream bacs = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];
         int byte_integer =0;
         
         while((byte_integer=imagePart.read(buffer))!=-1){
             bacs.write(buffer,0,byte_integer);
         }
          imag_source = bacs.toByteArray();
        }
        
        Image img = new Image();
        img.setName(name);
        img.setImage_source(imag_source);
        ifl.create(img);
        
        
    }



}
