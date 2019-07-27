package controllery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {
//
    private JEditorPane response;

    public Servlet(JEditorPane response) {
        this.response = response;
    }


       private  String message;

       public void init( )throws ServletException{
           message = "Simple servlet - message";

       }
       public void  doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
           Servlet.this.response.setContentType("text/html");


           PrintWriter printWriter = response.getWriter();
           printWriter.println("<h1>" + message + "<h1>");
       }

       public void destroy(){

       }
   }





























