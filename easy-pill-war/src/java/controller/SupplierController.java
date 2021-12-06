/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.InventoryRequests;
import entities.Supplier;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.InventoryRequestsFacade;
import model.SupplierFacade;
import utilities.Mail;
import utilities.SupplierEmailValidator;
import utilities.SupplierIdGenerator;

/**
 *
 * @author Topsy
 */
public class SupplierController extends HttpServlet {

    @EJB
    private InventoryRequestsFacade inventoryRequestsFacade;

    @EJB
    private SupplierEmailValidator supplierEmailValidator;

    @EJB
    private SupplierIdGenerator supplierIdGenerator;

    @EJB
    private SupplierFacade supplierFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "SUPPLIER";
        }
        switch (command) {
            case "SUPPLIER":
                redirectAdminSupplier(request, response);
            case "SUPPLIEROPTION":
                redirectSupplierOptionSelection(request, response);
                break;
            case "REQUEST":
                redirectSendInventoryRequestPage(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String command = request.getParameter("command");
            if (command == null) {
                command = "SUPPLIER";
            }
            switch (command) {
                case "MEDICINE":
                    redirectAdminSupplier(request, response);
                case "ADDSUPPLIER":
                    addSupplier(request, response);
                case "DELETESUPPLIER":
                    deleteSupplier(request, response);
                    break;
                case "UPDATESUPPLIER":
                    confirmUpdateSupplier(request, response);
                    break;
                case "NEWREQUEST":
                    sendInventoryRequest(request, response);
                    break;

            }
        } catch (MessagingException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void redirectAdminSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("username", (String) session.getAttribute("UserFirstName") + " " + (String) session.getAttribute("UserLastName"));
        List<Supplier> supplierList = supplierFacade.findAll();
        request.setAttribute("SUPPLIERLIST", supplierList);
         List<InventoryRequests> requestList = inventoryRequestsFacade.findAll();
        request.setAttribute("REQUESTLIST", requestList);
        request.setAttribute("supplierCount", supplierList.size());
        request.setAttribute("requestCount", "12");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminSupplierPage.jsp");
        dispatcher.forward(request, response);
    }

    private void redirectSupplierOptionSelection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        switch (option) {
            case "address":
                redirectUpdateSupplierAddress(request, response);
                break;
            case "contact":
                redirectUpdateSupplierContact(request, response);
                break;
            case "delete":
                redirectDeleteSupplier(request, response);
                break;
            default:
                break;
        }
    }

    private void addSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (supplierEmailValidator.emailExists(request.getParameter("email"))) {
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("address", request.getParameter("address"));
            request.setAttribute("contact", request.getParameter("contact"));
            request.setAttribute("ErrorMessage", "Email already exists, Please try a different email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAddSupplierPage.jsp");
            dispatcher.forward(request, response);
        } else {
            Supplier supplier = new Supplier();
            supplier.setName(request.getParameter("name"));
            supplier.setAddress(request.getParameter("address"));
            supplier.setContactNo(request.getParameter("contact"));
            supplier.setEmail(request.getParameter("email"));
            supplier.setSupplierId(supplierIdGenerator.generateNumber());

            supplierFacade.create(supplier);
            RequestDispatcher dispatcher = request.getRequestDispatcher("addSupplierSuccess.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierId = request.getParameter("supplierId");
        supplierFacade.removeSupplier(supplierId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteSupplierSuccess.jsp");
        dispatcher.forward(request, response);
    }

    private void confirmUpdateSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        String supplierId;
        switch (option) {
            case "address":
                supplierId = request.getParameter("supplierId");
                String newAddress = request.getParameter("newAddress");
                supplierFacade.updateAddress(supplierId, newAddress);
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("/adminUpdateSupplierSuccess.jsp");
                dispatcher1.forward(request, response);
                break;
            case "contact":
                supplierId = request.getParameter("supplierId");
                String newContact = request.getParameter("newContact");
                supplierFacade.updateContactNo(supplierId, newContact);
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("/adminUpdateSupplierSuccess.jsp");
                dispatcher2.forward(request, response);
                break;

            default:
                break;
        }
    }

    private void redirectUpdateSupplierAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierId = request.getParameter("supplierId");
        Supplier supplier = supplierFacade.getSupplierById(supplierId);
        request.setAttribute("address", supplier.getAddress());
        request.setAttribute("supplierId", supplierId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminUpdateSupplierAddressPage.jsp");
        dispatcher.forward(request, response);
    }

    private void redirectUpdateSupplierContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierId = request.getParameter("supplierId");
        Supplier supplier = supplierFacade.getSupplierById(supplierId);
        request.setAttribute("contact", supplier.getContactNo());
        request.setAttribute("supplierId", supplierId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminUpdateSupplierContactPage.jsp");
        dispatcher.forward(request, response);
    }

    private void redirectDeleteSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierId = request.getParameter("supplierId");
        Supplier supplier = supplierFacade.getSupplierById(supplierId);
        request.setAttribute("name", supplier.getName());
        request.setAttribute("email", supplier.getEmail());
        request.setAttribute("supplierId", supplierId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminRemoveSupplierPage.jsp");
        dispatcher.forward(request, response);
    }

    private void redirectSendInventoryRequestPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("username", (String) session.getAttribute("UserFirstName") + " " + (String) session.getAttribute("UserLastName"));
        List<Supplier> supplierList = supplierFacade.findAll();
        request.setAttribute("SUPPLIERLIST", supplierList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminSendInventoryRequestPage.jsp");
        dispatcher.forward(request, response);
    }

    private void sendInventoryRequest(HttpServletRequest request, HttpServletResponse response) throws MessagingException, ServletException, IOException {
        String supplierId = request.getParameter("supplier");
        String message = request.getParameter("message");
        Supplier supplier = supplierFacade.getSupplierById(supplierId);
        Mail.sendMail(supplier.getEmail(), message + "\n\nThank You,\nEasy-Pill Team");
        InventoryRequests inventoryRequest = new InventoryRequests();
        inventoryRequest.setDate(LocalDate.now().toString());
        inventoryRequest.setSupplierId(supplierId);
        inventoryRequest.setMessage(message);
        inventoryRequest.setRequestId("RHSK123123");
        inventoryRequestsFacade.create(inventoryRequest);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminInventoryRequestSuccess.jsp");
        dispatcher.forward(request, response);
    }

}
