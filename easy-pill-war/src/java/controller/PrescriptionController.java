/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Prescription;
import entities.User;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.PrescriptionFacade;
import model.UserFacade;
import utilities.Mail;
import utilities.PrescriptionIdGenerator;
import utilities.SessionDetails;

/**
 *
 * @author Topsy
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100,
        location = "D:\\Documents\\NetBeansProjects\\easy-pill\\easy-pill-war\\web\\PrescriptionImages\\"
)
public class PrescriptionController extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @EJB
    private PrescriptionFacade prescriptionFacade;

    @EJB
    private PrescriptionIdGenerator prescriptionIdGenerator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "HOME";
        }
        try {
            switch (command) {
                case "PRESCRIPTION": {
                    redirectHome(request, response);
                    break;
                }
                case "DELETEPRES": {

                    customerDeletePrescription(request, response);
                    break;
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "HOME";
        }
        try {
            switch (command) {
                case "PRESCRIPTION": {
                    redirectHome(request, response);
                    break;
                }
                case "UPLOAD": {
                    uploadPrescription(request, response);
                    break;
                }
                case "CONFIRM": {
                    confirmPrescription(request, response);
                    break;
                }
                case "DELETE": {
                    deletePrescription(request, response);
                    break;
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void redirectHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", SessionDetails.getUserFirstName() + " " + SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        String role = SessionDetails.getUserRole();
        switch (role) {
            case "customer":
                request.setAttribute("PRESCRIPTIONLIST", prescriptionFacade.findAll());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customerPrescriptionPage.jsp");
                dispatcher.forward(request, response);
                break;
            case "admin":
                request.setAttribute("pendingCount", prescriptionFacade.getPendingCount());
                request.setAttribute("confirmedCount", prescriptionFacade.getConfirmedCount());
                request.setAttribute("PRESCRIPTIONLIST", prescriptionFacade.findAll());
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("/adminPrescriptionPage.jsp");
                dispatcher2.forward(request, response);
                break;

        }

    }

    private void uploadPrescription(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String imagePath;
        String address = request.getParameter("address");
        Part imagePart = request.getPart("image");
        //Validating  Photo and File Uploads
        if (!imagePart.getContentType().equalsIgnoreCase("image/jpeg")) {
            if (!imagePart.getContentType().equalsIgnoreCase("image/png")) {
                request.setAttribute("errorMessage", "Please make sure an image is uploaded here!");
                request.setAttribute("address", request.getParameter("address"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customerUploadPrescriptionPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            String prescriptionId = prescriptionIdGenerator.generateNumber();
            if (imagePart.getContentType().equalsIgnoreCase("image/jpeg")) {
                imagePart.write(prescriptionId + ".jpg");
                imagePath = "PrescriptionImages\\" + prescriptionId + ".jpg";
            } else {
                imagePart.write(prescriptionId + ".png");
                imagePath = "PrescriptionImages\\" + prescriptionId + ".png";
            }
            User user = userFacade.find(SessionDetails.getUserId());
            Prescription prescription = new Prescription();
            prescription.setPrescriptionId(prescriptionId);
            prescription.setImagePath(imagePath);
            if (address.equals("")) {
                prescription.setAddress(user.getAddress());
            } else {
                prescription.setAddress(address);
            }
            prescription.setContact(user.getContactNo());
            prescription.setUserId(SessionDetails.getUserId());
            prescription.setStatus("Pending");
            prescription.setDate(LocalDate.now().toString());
            prescription.setBillAmount("Pending");
            prescriptionFacade.create(prescription);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/customerUploadPrescriptionSuccess.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void confirmPrescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        String prescriptionId = request.getParameter("prescriptionId");
        String userId = request.getParameter("userId");
        String amount = request.getParameter("bill");
        prescriptionFacade.confirmPrescription(prescriptionId, amount);
        User user = userFacade.find(userId);
        Mail.sendMail(user.getEmail(), "Your prescription(" + prescriptionId + ") has been confirmed. The final bill amount is Rs." + amount + "\n\nThank You,\nEasy-Pill Team");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminConfirmPrescriptionSuccess.jsp");
        dispatcher.forward(request, response);
    }

    private void deletePrescription(HttpServletRequest request, HttpServletResponse response) throws MessagingException, ServletException, IOException {
        String prescriptionId = request.getParameter("prescriptionId");
        String userId = request.getParameter("userId");
        Prescription prescription = prescriptionFacade.find(prescriptionId);
        String status = prescription.getStatus();
        User user = userFacade.find(userId);
        Path imagePath = FileSystems.getDefault().getPath("D:\\Documents\\NetBeansProjects\\easy-pill\\easy-pill-war\\web\\" + prescription.getImagePath());
        Files.deleteIfExists(imagePath);
        prescriptionFacade.removePrescription(prescriptionId);
        if (status.equalsIgnoreCase("pending")) {
            Mail.sendMail(user.getEmail(), "Your pending prescription(" + prescriptionId + ") was removed. Please contact support for further information.  " + "\n\nThank You,\nEasy-Pill Team");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminDeletePrescriptionSuccess.jsp");
        dispatcher.forward(request, response);
    }

    private void customerDeletePrescription(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException {
        String prescriptionId = request.getParameter("prescriptionId");
        Prescription prescription = prescriptionFacade.find(prescriptionId);
        Path imagePath = FileSystems.getDefault().getPath("D:\\Documents\\NetBeansProjects\\easy-pill\\easy-pill-war\\web\\" + prescription.getImagePath());
        Files.deleteIfExists(imagePath);
        prescriptionFacade.removePrescription(prescriptionId);
        redirectHome(request, response);
    }
}
