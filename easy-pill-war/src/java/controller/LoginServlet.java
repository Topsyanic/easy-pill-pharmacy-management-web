/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserFacade;
import utilities.HashPassword;

/**
 *
 * @author Topsy
 */
public class LoginServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userFacade.getAuthenticatedUser(email);

        if (user == null) {
            request.setAttribute("ErrorMessage", "Invalid credentials, Please sign up if u do not have an account.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else if (!HashPassword.getHash(password).equalsIgnoreCase(user.getPassword())) {
            request.setAttribute("ErrorMessage", "Password is incorrect. Please Try Again.");
            request.setAttribute("email",email);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("email", email);
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
            session.setAttribute("userRole", user.getUserRole());
            switch (user.getUserRole()) {
                case "admin": {
                    response.sendRedirect("AdminController");
                    break;
                }
                case "customer": {
                    response.sendRedirect("CustomerController");
                    break;
                }
                case "pharmacist": {
                    response.sendRedirect("PharmacistController");
                    break;
                }
                case "doctor": {
                    response.sendRedirect("DoctorController");
                    break;
                }
            }
        }

    }

}
