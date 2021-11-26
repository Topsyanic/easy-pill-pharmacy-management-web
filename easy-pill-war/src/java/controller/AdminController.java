/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Topsy
 */
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "HOME";
        }
        switch (command) {
            case "HOME": {
                redirectHome(request, response);
                break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void redirectHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("username", (String) session.getAttribute("UserFirstName") + " " + (String) session.getAttribute("UserLastName"));
        request.setAttribute("userCount","0" );
        request.setAttribute("medicineCount","0" );
        request.setAttribute("orderCount","0" );
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminHome.jsp");
        dispatcher.forward(request, response);
    }
}
