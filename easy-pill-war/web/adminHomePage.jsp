<%-- 
    Document   : adminHomePage
    Created on : Sep 19, 2021, 1:17:45 PM
    Author     : Raaid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/adminUser.css">
        <title>Easy Pill Admin</title>
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    </head>
    <body>
        <input type="checkbox" id="nav-toggle">
        <div class="sidebar">
            <div class="sidebar-brand">
                <h2> <span>EASY PILL</span></h2>
            </div>
            <div class="sidebar-menu">
                <c:url var="dashboardLink" value="AdminController">
                    <c:param name="command" value="HOME"/>
                </c:url>
                <c:url var="customerLink" value="UserController">
                    <c:param name="command" value="USER"/>
                    <c:param name="tab" value="customer"/>
                </c:url>
                <c:url var="doctorLink" value="UserController">
                    <c:param name="command" value="USER"/>
                    <c:param name="tab" value="doctor"/>
                </c:url>
                <c:url var="pharmacistLink" value="UserController">
                    <c:param name="command" value="USER"/>
                    <c:param name="tab" value="pharmacist"/>
                </c:url>
                <c:url var="inStockLink" value="MedicineController">
                    <c:param name="command" value="MEDICINE"/>
                    <c:param name="tab" value="instock"/>
                </c:url>
                <c:url var="outStockLink" value="MedicineController">
                    <c:param name="command" value="MEDICINE"/>
                    <c:param name="tab" value="outstock"/>
                </c:url>
                <c:url var="supplierLink" value="SupplierController">
                    <c:param name="command" value="SUPPLIER"/>
                </c:url>
                <c:url var="precriptionLink" value="PrescriptionController">
                    <c:param name="command" value="PRESCRIPTION"/>
                </c:url>
                <c:url var="orderLink" value="OrderController">
                    <c:param name="command" value="ORDER"/>
                </c:url>
                <c:url var="logoutLink" value="LogoutServlet">
                </c:url>
                <ul>
                    <li><a href="${dashboardLink}" class="active"><span class="las la-home"></span><span>Dashboard</span></a></li>
                    <li><a href="${customerLink}"><span class="las la-user-alt"></span><span>Customers</span></a></li>
                    <li><a href="${pharmacistLink}"><span class="las la-stethoscope"></span><span>Doctors</span></a></li>
                    <li><a href="${pharmacistLink}"><span class="las la-user-nurse"></span><span>Pharmacists</span></a></li>
                    <li><a href="${inStockLink}"><span class="las la-first-aid"></span><span>Medicine In Stock</span></a></li>
                    <li><a href="${outStockLink}"><span class="las la-band-aid"></span><span>Medicine Out of Stock</span></a></li>
                    <li><a href="${supplierLink}"><span class="las la-truck"></span><span>Suppliers</span></a></li>
                    <li><a href="${orderLink}"><span class="las la-shopping-cart"></span><span>Orders</span></a></li>
                    <li><a href="${precriptionLink}"><span class="las la-notes-medical"></span><span>Prescriptions</span></a></li>
                    <li><a href="${logoutLink}"><span class="las la-sign-out-alt"></span><span>Logout</span></a></li>
                </ul>
            </div>
        </div>

        <div class="main-content">
            <header>
                <h2>
                    <label for="nav-toggle">
                        <span class="las la-bars"></span>
                    </label>
                    Dashboard
                </h2>
                <div class="user-wrapper">
                    <img src="IMG/admin.jpg" width="40px" height="40px" alt="">
                    <div>
                        <h4>${username}</h4>
                        <small>${role}</small>
                    </div>
                </div>
            </header>
            <main>
                <div class="cards">
                    <div class="cards-single">
                        <div>
                            <h1>${userCount}</h1>
                            <span>Users</span>
                        </div>
                        <div>
                            <span class="las la-users"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${medicineCount}</h1>
                            <span>Medicine</span>
                        </div>
                        <div>
                            <span class="las la-first-aid"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${orderCount}</h1>
                            <span>Orders</span>
                        </div>
                        <div>
                            <span class="las la-shopping-cart"></span>
                        </div>
                    </div>
                </div>
                <div class="recent-grid">
                    <div class="projects">
                        <div class="card">
                            <div class="card-header">
                                <h3>Recent Orders</h3>
                                <a class="linkButton" href="${orderLink}">See More <span class="las la-arrow-right"></span></a>
                            </div>

                            <div class="card-body">
                                <div class="table-responsive">
                                    <table width="100%" id="tableData">
                                        <thead>
                                            <tr>
                                                <td>Order Id</td>
                                                <td>Status</td>
                                                <td>Date</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="tempList" items="${ORDERLIST}">
                                                <tr>
                                                    <td>${tempList.orderId}</td>
                                                    <td>${tempList.status}</td>
                                                    <td>${tempList.date}</td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div> 
                            </div>
                        </div>
                    </div>
                    <div class="customers">
                        <div class="card">
                            <div class="card-header">
                                <h3>New Customers</h3>
                                <a class="linkButton" href="${customerLink}">See More <span class="las la-arrow-right"></span></a>
                            </div>
                            <div class="card-body">
                                <c:forEach var="tempList" items="${CUSTOMERLIST}">
                                    <div class="customer" >
                                        <div class="info">
                                            <img src="IMG/user.png" width="40px" height="40px" alt="">
                                            <div>
                                                <h4>${tempList.firstName} ${tempList.lastName}</h4>
                                                <small>${tempList.email}</small>
                                            </div>
                                        </div>
                                        <div class="contact"> 
                                            <span class="las la-user-circle"></span> 
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    </body>
</html>