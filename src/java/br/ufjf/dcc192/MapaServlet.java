/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon Larivoir
 */
@WebServlet(name = "MapaServlet", urlPatterns = {"/index.html"})
public class MapaServlet extends HttpServlet {

    Map<String, String> paises;
    
    public MapaServlet() {
        paises = new HashMap<>();
        
        paises.put("Brasil", "Amarelo");
        paises.put("Argentina", "Azul");
        paises.put("Holanda", "Laranja");
        paises.put("Japão", "Branco");
        paises.put("Itália", "Azul");
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ordenacao = request.getParameter("ordenacao");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Países</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");        out.println("</head>");
            out.println("<body class='container'>");
            out.println("<h1>Países</h1>");
            out.println("<table class=\"table table-striped\">");
            out.println("<tr>");
            out.println("<td><a href='?ordenacao=lista'>Lista</a></td>");
            out.println("<td><a href='?ordenacao=agrupar'>Agrupar por Cor</a></td>");
            out.println("</tr>");
            if("lista".equals(ordenacao)) {
                for (Map.Entry<String, String> pais : paises.entrySet()) {
                    out.println("<tr>");
                    out.println("<td>" + pais.getKey() + "</td>");
                    out.println("<td>" + pais.getValue() + "</td>");
                    out.println("</tr>");
                }
            } else if("agrupar".equals(ordenacao)) {
                Map<String, ArrayList<String>> coresPaises = new HashMap<>();
                for (Map.Entry<String, String> pais : paises.entrySet()) {
                    if(!coresPaises.containsKey(pais.getValue())) {
                        ArrayList<String> listaCor = new ArrayList<>();
                        listaCor.add(pais.getKey());
                        coresPaises.put(pais.getValue(), listaCor);
                    } else {
                        coresPaises.get(pais.getValue()).add(pais.getKey());
                    }
                }
                for (Map.Entry<String, ArrayList<String>> cor : coresPaises.entrySet()) {
                    out.println("<tr>");
                    out.println("<td>" + cor.getKey() + "</td>");
                    out.println("<td>" + cor.getValue() + "</td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
}
