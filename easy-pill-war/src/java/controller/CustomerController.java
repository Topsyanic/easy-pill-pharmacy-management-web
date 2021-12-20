/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Medicine;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MedicineFacade;
import utilities.SessionDetails;

/**
 *
 * @author Topsy
 */
public class CustomerController extends HttpServlet {

    @EJB
    private MedicineFacade medicineFacade;

    
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
        String command = request.getParameter("command");
        if (command == null) {
            command = "HOME";
        }
        switch (command) {
            case "HOME": {
                redirectHome(request, response);
                break;
            }
            case "SEARCH": {
                searchResult(request, response);
                break;
            }

        }
        

    }

    private void redirectHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", SessionDetails.getUserFirstName() +" "+SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        List<Medicine> medicineList = medicineFacade.findAll();
        request.setAttribute("MEDICINELIST",medicineList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerHomePage.jsp");
        dispatcher.forward(request, response);
    }

    private void searchResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        System.out.println("THE KEYWORD IS "+keyword);
        List<Medicine> medicineList = medicineFacade.searchMedicine(keyword);
        request.setAttribute("MEDICINELIST",medicineList);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerHomePage.jsp");
        dispatcher.forward(request, response);
    }
}
