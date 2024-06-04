/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controler;

import com.emergentes.bean.BeanEstudiante;
import com.emergentes.entidades.Estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nivek
 */
@WebServlet(name = "MainControler", urlPatterns = {"/MainControler"})
public class MainControler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "delete":
                    eliminar(request, response);
                    break;
                case "listar":
                    listar(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = 0;
        String idS = request.getParameter("id");
        if (idS != null && !idS.isEmpty()) {
            id = Integer.parseInt(idS);
        }
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));

        Estudiante e = new Estudiante();
        e.setId(id);
        e.setNombre(nombre);
        e.setApellidos(apellidos);
        e.setEmail(email);
        e.setFechaNacimiento(fechaNacimiento);

        BeanEstudiante jpa = new BeanEstudiante();
        if (id == 0) {
            try {
                jpa.insertar(e);
            } catch (Exception ex) {
                System.out.println("ERROR" + ex.getMessage());
            }
        } else {
            try {
                jpa.editar(e);
            } catch (Exception ex) {
                System.out.println("ERROR" + ex.getMessage());
            }
        }
        response.sendRedirect("MainControler?action=listar");
    }

    public void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Estudiante e = new Estudiante();
        request.setAttribute("estudiante", e);
        request.getRequestDispatcher("estu.jsp").forward(request, response);

    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idS = request.getParameter("id");
        if (idS != null && !idS.isEmpty()) {
            int id = Integer.parseInt(idS);
            BeanEstudiante jpa = new BeanEstudiante();
            Estudiante e = jpa.buscar(id);
            request.setAttribute("e", e);
            request.getRequestDispatcher("estu.jsp").forward(request, response);
        } else {
            response.sendRedirect("MainControler?action=listar");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idS = request.getParameter("id");
        if (idS != null && !idS.isEmpty()) {
            int id = Integer.parseInt(idS);
            BeanEstudiante jpa = new BeanEstudiante();
            jpa.eliminar(id);
        }
        response.sendRedirect("MainControler?action=listar");
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanEstudiante jpa = new BeanEstudiante();

        List<Estudiante> lista = jpa.listarTodos();
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("Estudiantes.jsp").forward(request, response);
    }
}
