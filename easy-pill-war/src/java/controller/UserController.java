/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserFacade;
import model.UserIdGenerator;
import utilities.HashPassword;

/**
 *
 * @author Topsy
 */
public class UserController extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "USER";
        }
        switch (command) {
            case "USER":
                redirectAdminHome(request, response);
            case "DELETEUSER":
                redirectDeleteUser(request, response);
            case "CONFIRMDELETE":
                confirmDeleteUser(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "USER";
        }
        switch (command) {
            case "USER":
                redirectAdminHome(request, response);
            case "ADDCUSTOMER":
                addCustomer(request, response);
            case "ADDPHARMACIST":
                addPharmacist(request, response);
            case "ADDDOCTOR":
                addDoctor(request, response);

        }

    }

    private void redirectAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<User> customerList = userFacade.getAllCustomers();
        List<User> doctorList = userFacade.getAllDoctors();
        List<User> pharmacistList = userFacade.getAllPharmacists();
        request.setAttribute("username", (String) session.getAttribute("UserFirstName") + " " + (String) session.getAttribute("UserLastName"));
        request.setAttribute("customerCount", customerList.size());
        request.setAttribute("doctorCount", doctorList.size());
        request.setAttribute("pharmacistCount", pharmacistList.size());
        request.setAttribute("CUSTOMERLIST", customerList);
        request.setAttribute("DOCTORLIST", doctorList);
        request.setAttribute("PHARMACISTLIST", pharmacistList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminUser.jsp");
        dispatcher.forward(request, response);

    }

    private void redirectDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/removeUser.jsp");
        dispatcher.forward(request, response);
    }

    private void confirmDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        System.out.println("THE USER ID IS" + userId);
        try {
            userFacade.removeUser(userId);
        } catch (Exception e) {
            System.out.println("Failed to delete User ;" + e);
        }
        request.getRequestDispatcher("deleteUserSuccess.jsp").include(request, response);

    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        if (userFacade.checkEmailExists(request.getParameter("email"))) {
            System.out.println("The email is" + request.getParameter("email"));
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("contact", request.getParameter("contact"));
            request.setAttribute("ErrorMessage", "Email already exists, Please try a different email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAddCustomer.jsp");
            dispatcher.forward(request, response);
        } else {
            UserIdGenerator UIDGen = new UserIdGenerator();
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(HashPassword.getHash(request.getParameter("password")));
            user.setAddress(request.getParameter("address"));
            user.setContactNo(request.getParameter("contact"));
            user.setUserRole("customer");
            user.setUserId(UIDGen.generateNumber());
            userFacade.create(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("addUserSuccess.jsp");
            dispatcher.forward(request, response);

        }

    }

    private void addPharmacist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        if (userFacade.checkEmailExists(request.getParameter("email"))) {
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("contact", request.getParameter("contact"));
            request.setAttribute("ErrorMessage", "Email already exists, Please try a different email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAddPharmacist.jsp");
            dispatcher.forward(request, response);
        } else {
            UserIdGenerator UIDGen = new UserIdGenerator();
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(HashPassword.getHash(request.getParameter("password")));
            user.setAddress(request.getParameter("address"));
            user.setContactNo(request.getParameter("contact"));
            user.setUserRole("pharmacist");
            user.setUserId(UIDGen.generateNumber());
            userFacade.create(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("addUserSuccess.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void addDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        if (userFacade.checkEmailExists(request.getParameter("email"))) {
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("contact", request.getParameter("contact"));
            request.setAttribute("expertise", request.getParameter("expertise"));
            request.setAttribute("ErrorMessage", "Email already exists, Please try a different email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAddDoctor.jsp");
            dispatcher.forward(request, response);
        } else {
            UserIdGenerator UIDGen = new UserIdGenerator();
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(HashPassword.getHash(request.getParameter("password")));
            user.setAddress(request.getParameter("address"));
            user.setContactNo(request.getParameter("contact"));
            user.setExpertise(request.getParameter("expertise"));
            user.setUserRole("doctor");
            user.setUserId(UIDGen.generateNumber());
            userFacade.create(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("addUserSuccess.jsp");
            dispatcher.forward(request, response);
        }

    }

}
