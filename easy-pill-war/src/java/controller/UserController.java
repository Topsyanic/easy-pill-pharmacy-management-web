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
import javax.validation.Validator;
import model.UserFacade;
import utilities.UserIdGenerator;
import utilities.PasswordHasher;
import utilities.SessionDetails;
import utilities.UserEmailValidator;

/**
 *
 * @author Topsy
 */
public class UserController extends HttpServlet {

    Validator validator;

    @EJB
    private UserEmailValidator userEmailValidator;
    @EJB
    private UserIdGenerator userIdGenerator;
    @EJB
    private PasswordHasher passwordHasher;
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
                redirectAdminUser(request, response);

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
                redirectAdminUser(request, response);
            case "ADDCUSTOMER":
                addCustomer(request, response);
            case "ADDPHARMACIST":
                addPharmacist(request, response);
            case "ADDDOCTOR":
                addDoctor(request, response);
            case "CONFIRMDELETE":
                confirmDeleteUser(request, response);

        }

    }

    private void redirectAdminUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> customerList = userFacade.getAllCustomers();
        List<User> doctorList = userFacade.getAllDoctors();
        List<User> pharmacistList = userFacade.getAllPharmacists();
        request.setAttribute("username", SessionDetails.getUserFirstName() + " " + SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        request.setAttribute("customerCount", customerList.size());
        request.setAttribute("doctorCount", doctorList.size());
        request.setAttribute("pharmacistCount", pharmacistList.size());
        request.setAttribute("CUSTOMERLIST", customerList);
        request.setAttribute("DOCTORLIST", doctorList);
        request.setAttribute("PHARMACISTLIST", pharmacistList);
        String tab = request.getParameter("tab");
        switch (tab) {
            case "customer":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/adminCustomersPage.jsp");
                dispatcher.forward(request, response);
            case "doctor":
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("/adminDoctorsPage.jsp");
                dispatcher2.forward(request, response);
            case "pharmacist":
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("/adminPharmacistsPage.jsp");
                dispatcher3.forward(request, response);

        }

    }

    private void confirmDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        userFacade.removeUser(userId);
        request.setAttribute("tab", request.getParameter("tab"));
        request.getRequestDispatcher("deleteUserSuccess.jsp").include(request, response);

    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        if (userEmailValidator.emailExists(request.getParameter("email"))) {
            System.out.println("The email is" + request.getParameter("email"));
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("contact", request.getParameter("contact"));
            request.setAttribute("ErrorMessage", "Email already exists, Please try a different email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAddCustomerPage.jsp");
            dispatcher.forward(request, response);
        } else {

            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(passwordHasher.getHash(request.getParameter("password")));
            user.setAddress(request.getParameter("address"));
            user.setContactNo(request.getParameter("contact"));
            user.setUserRole("customer");
            user.setUserId(userIdGenerator.generateNumber());
            userFacade.create(user);
            request.setAttribute("tab", request.getParameter("tab"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("addUserSuccess.jsp");
            dispatcher.forward(request, response);

        }

    }

    private void addPharmacist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        if (userEmailValidator.emailExists(request.getParameter("email"))) {
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("contact", request.getParameter("contact"));
            request.setAttribute("ErrorMessage", "Email already exists, Please try a different email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAddPharmacistPage.jsp");
            dispatcher.forward(request, response);
        } else {
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(passwordHasher.getHash(request.getParameter("password")));
            user.setAddress(request.getParameter("address"));
            user.setContactNo(request.getParameter("contact"));
            user.setUserRole("pharmacist");
            user.setUserId(userIdGenerator.generateNumber());
            userFacade.create(user);
            request.setAttribute("tab", request.getParameter("tab"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("addUserSuccess.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void addDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        if (userEmailValidator.emailExists(request.getParameter("email"))) {
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("contact", request.getParameter("contact"));
            request.setAttribute("expertise", request.getParameter("expertise"));
            request.setAttribute("ErrorMessage", "Email already exists, Please try a different email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAddDoctorPage.jsp");
            dispatcher.forward(request, response);
        } else {
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(passwordHasher.getHash(request.getParameter("password")));
            user.setAddress(request.getParameter("address"));
            user.setContactNo(request.getParameter("contact"));
            user.setExpertise(request.getParameter("expertise"));
            user.setUserRole("doctor");
            user.setUserId(userIdGenerator.generateNumber());
            userFacade.create(user);
            request.setAttribute("tab", request.getParameter("tab"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("addUserSuccess.jsp");
            dispatcher.forward(request, response);
        }

    }

}
