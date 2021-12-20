/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.CustomerOrder;
import entities.User;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerOrderFacade;
import model.MedicineFacade;
import model.UserFacade;
import utilities.SessionDetails;

/**
 *
 * @author Topsy
 */
public class AdminController extends HttpServlet {

    @EJB
    private CustomerOrderFacade customerOrderFacade;

    @EJB
    private MedicineFacade medicineFacade;

    @EJB
    private UserFacade userFacade;

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
        request.setAttribute("username", SessionDetails.getUserFirstName() + " " + SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        List<CustomerOrder> customerOrderList = customerOrderFacade.getNewOrders();
        List<User> customerList = userFacade.getAllNewCustomers();
        request.setAttribute("userCount", userFacade.findAll().size());
        request.setAttribute("medicineCount", medicineFacade.findAll().size());
        request.setAttribute("orderCount", customerOrderFacade.findAll().size());
        request.setAttribute("ORDERLIST", customerOrderList);
        request.setAttribute("CUSTOMERLIST", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminHomePage.jsp");
        dispatcher.forward(request, response);
    }
}
