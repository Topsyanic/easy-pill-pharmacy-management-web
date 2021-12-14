/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Cart;
import entities.Medicine;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CartFacade;
import model.MedicineFacade;
import utilities.CartIdGenerator;
import utilities.SessionDetails;

/**
 *
 * @author Topsy
 */
public class CartController extends HttpServlet {

    @EJB
    private CartIdGenerator cartIdGenerator;

    @EJB
    private MedicineFacade medicineFacade;

    @EJB
    private CartFacade cartFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String command = request.getParameter("command");
        if (command == null) {
            command = "CART";
        }
        switch (command) {
            case "CART": {
                redirectHome(request, response, null);
                break;
            }
            case "DELETE": {
                removeFromCart(request, response, null);
                break;
            }
            case "INCREASE": {
                increaseCartItem(request, response, null);
                break;
            }
            case "REDUCE": {
                reduceCartItem(request, response, null);
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
            case "CART": {
                redirectHome(request, response, null);
                break;
            }
            case "ADD": {
                addToCart(request, response);
                break;
            }

        }
    }

    private void redirectHome(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("username", SessionDetails.getUserFirstName() + " " + SessionDetails.getUserLastName());
        request.setAttribute("role", SessionDetails.getUserRole());
        String userId = SessionDetails.getUserId();
        List<Cart> cartList = cartFacade.getUserCart(userId);
        double total = 0.0;
        if (!cartList.isEmpty()) {
            for (Cart cart : cartList) {
                total = total + Double.parseDouble(cart.getSubTotal());
            }
        }
        request.setAttribute("cartTotal", total);
        request.setAttribute("cartMessage", message);
        request.setAttribute("CARTLIST", cartList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerCartPage.jsp");
        dispatcher.forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String medicineId = request.getParameter("medicineId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Medicine medicine = medicineFacade.find(medicineId);
        double price = Double.parseDouble(medicine.getPrice());
        Cart cart = new Cart();
        cart.setCartId(cartIdGenerator.generateNumber());
        cart.setMedicineId(medicineId);
        cart.setProductName(medicine.getName());
        cart.setUserId(SessionDetails.getUserId());
        cart.setQuantity(String.valueOf(quantity));
        cart.setSubTotal(Double.toString(quantity * price));
        cartFacade.create(cart);
        redirectHome(request, response, "Added to cart Successfully");
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response, Object object) throws ServletException, IOException {
        String cartId = request.getParameter("cartId");
        cartFacade.removeCartItem(cartId);
        redirectHome(request, response, "Item removed Successfully");
    }

    private void increaseCartItem(HttpServletRequest request, HttpServletResponse response, Object object) throws ServletException, IOException {
        String cartId = request.getParameter("cartId");
        String medicineId = request.getParameter("medicineId");
        Medicine medicine = medicineFacade.find(medicineId);
        double price = Double.parseDouble(medicine.getPrice());
        cartFacade.increaseCartItem(cartId, price);
        redirectHome(request, response, "Item updated Successfully");
    }

    private void reduceCartItem(HttpServletRequest request, HttpServletResponse response, Object object) throws ServletException, IOException {
        String cartId = request.getParameter("cartId");
        String medicineId = request.getParameter("medicineId");
        Medicine medicine = medicineFacade.find(medicineId);
        double price = Double.parseDouble(medicine.getPrice());
        cartFacade.reduceCartItem(cartId, price);
        redirectHome(request, response, "Item updated Successfully");
    }

}
