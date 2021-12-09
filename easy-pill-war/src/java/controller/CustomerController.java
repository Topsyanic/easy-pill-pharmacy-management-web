/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.SessionDetails;

/**
 *
 * @author Topsy
 */
public class CustomerController extends HttpServlet {

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
        request.setAttribute("username", SessionDetails.getUserFirstName() +" "+SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerHomePage.jsp");
        dispatcher.forward(request, response);
    }
}
