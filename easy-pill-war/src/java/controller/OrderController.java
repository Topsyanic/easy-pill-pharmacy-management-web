/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.CustomerOrder;
import entities.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerOrderFacade;
import model.UserFacade;
import utilities.Mail;
import utilities.OrderIdGenerator;
import utilities.SessionDetails;

/**
 *
 * @author Topsy
 */
public class OrderController extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @EJB
    private CustomerOrderFacade customerOrderFacade;
    

    @EJB
    private OrderIdGenerator orderIdGenerator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "ORDER";
        }
        try {
            switch (command) {
                case "ORDER": {
                    redirectHome(request, response);
                    break;
                }
                case "HISTORY": {
                    redirectCustomerHistory(request, response);
                    break;
                }
                case "DELETE": {

                    cancelOrder(request, response);
                    break;
                }
                case "CUSTOMERORDERS": {
                    redirectCustomerOrders(request, response, null);
                    break;
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "ORDER";
        }
        try {
            switch (command) {
                case "ORDER": {
                    redirectHome(request, response);
                    break;
                }
                case "PLACEORDER": {
                    placeOrder(request, response);
                    break;
                }
                case "CONFIRM": {
                    confirmOrder(request, response);
                    break;
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void redirectHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", SessionDetails.getUserFirstName() + " " + SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        request.setAttribute("pendingCount", customerOrderFacade.getPendingCount());
        request.setAttribute("confirmedCount", customerOrderFacade.getConfirmedCount());
        request.setAttribute("ORDERLIST", customerOrderFacade.findAll());
        RequestDispatcher dispatcher2 = request.getRequestDispatcher("/adminOrderPage.jsp");
        dispatcher2.forward(request, response);
    }

    private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws MessagingException, ServletException, IOException {
        String orderDetails = request.getParameter("orderDetails");
        String amount = request.getParameter("amount");
        String userId = SessionDetails.getUserId();
        customerOrderFacade.makeOrder(orderDetails, amount, userId);
        Mail.sendMail(SessionDetails.getUserEmail(), "You have successfully placed an order. Please visit the site to view the details \n\nThank You,\nEasy-Pill Team");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerOrderPlacedSuccess.jsp");
        dispatcher.forward(request, response);

    }

    private void redirectCustomerOrders(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("username", SessionDetails.getUserFirstName() + " " + SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        String userId = SessionDetails.getUserId();
        request.setAttribute("ORDERLIST", customerOrderFacade.getUserOrders(userId));
        request.setAttribute("orderMessage", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerOrderPage.jsp");
        dispatcher.forward(request, response);
    }

    private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws MessagingException, ServletException, IOException {
        String orderId = request.getParameter("orderId");
        customerOrderFacade.cancelOrder(orderId);
        Mail.sendMail(SessionDetails.getUserEmail(), "You have successfully cancelled Order. Your payment will be refunded shortly. \n\nThank You,\nEasy-Pill Team");
        redirectCustomerOrders(request, response, "Order Successfully Cancelled. Refund has been requested.");
    }

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws MessagingException, ServletException, IOException {
        String orderId = request.getParameter("orderId");
        CustomerOrder order = customerOrderFacade.find(orderId);
        User user = userFacade.find(order.getUserId());
        customerOrderFacade.confirmOrder(orderId);
        Mail.sendMail(user.getEmail(), "Your order has been confirmed. Your order is on its way now. \n\nThank You,\nEasy-Pill Team");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminConfirmOrderSuccess.jsp");
        dispatcher.forward(request, response);
    }

    private void redirectCustomerHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", SessionDetails.getUserFirstName() + " " + SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        String userId = SessionDetails.getUserId();
        request.setAttribute("ORDERLIST", customerOrderFacade.getUserOrders(userId));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerHistoryPage.jsp");
        dispatcher.forward(request, response);
    }

}
