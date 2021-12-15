<%-- 
    Document   : customerCartPage
    Created on : Dec 14, 2021, 11:37:40 AM
    Author     : Topsy
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
            <c:url var="homeLink" value="CustomerController">
                <c:param name="command" value="HOME"/>
            </c:url>
            <c:url var="cartLink" value="CartController">
                <c:param name="command" value="CART"/>
            </c:url>
            <c:url var="precriptionLink" value="PrescriptionController">
                <c:param name="command" value="PRESCRIPTION"/>
            </c:url>
            <c:url var="uploadLink" value="/customerUploadPrescriptionPage.jsp">
            </c:url>
            <c:url var="orderLink" value="OrderController">
                <c:param name="command" value="CUSTOMERORDERS"/>
            </c:url>
            <c:url var="historyLink" value="OrderController">
                <c:param name="command" value="HISTORY"/>
            </c:url>
            <c:url var="patientsLink" value="UserController">
                <c:param name="command" value="PATIENTS"/>
            </c:url>
            <c:url var="paymentLink" value="CartController">
                <c:param name="command" value="PAYMENT"/>
            </c:url>
            <c:url var="logoutLink" value="LogoutServlet">
            </c:url>
            <div class="sidebar-menu">
                <ul>
                    <li><a href="${homeLink}" ><span class="las la-store-alt"></span><span>Shop</span></a></li>
                    <li><a href="${cartLink}"  class="active"><span class="las la-shopping-bag"></span><span>Cart</span></a></li>
                    <li><a href="${precriptionLink}"><span class="las la-notes-medical"></span><span>Prescriptions</span></a></li>
                    <li><a href="${orderLink}" ><span class="las la-list"></span><span>Orders</span></a></li>
                    <li><a href="${historyLink}"><span class="las la-history"></span><span>History</span></a></li>
                                <c:choose>
                                    <c:when  test="${SessionDetails.getUserRole() == 'doctor'}">
                            <li><a href="${patientsLink}"><span class="las la-user"></span><span>Patients</span></a></li>
                                    </c:when>
                                </c:choose>
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
                    Cart
                </h2>

                <div class="search-wrapper">
                    <span class="las la-search"></span>
                    <input type="search" placeholder="Search here"/>
                </div>

                <div class="user-wrapper">
                    <img src="IMG/admin.jpg" width="40px" height="40px" alt="">
                    <div>
                        <h4>${username}</h4>
                        <small>${role}</small>
                    </div>
                </div>
            </header>
            <main>
                <p style="color:#1b4b80">${cartMessage}</p>
                <div class="recent-grid">
                    <c:choose>
                        <c:when  test="${CARTLIST.size() == 0}">
                            <h2 class="no-pres">No Items Available</h2>
                        </c:when>
                        <c:otherwise>
                            <div class="projects">
                                <div class="card">
                                    <div class="card-header">
                                        <h3>Cart</h3>
                                        <a href='${paymentLink}' class="customButton">Finish</a> 
                                        <input class="search-box" id="search2" placeholder="Search..." >
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table width="100%" id="tableData3">
                                                <thead>
                                                    <tr>
                                                        <td>Name</td>
                                                        <td>-</td>
                                                        <td>Qty</td>
                                                        <td>+</td>
                                                        <td>Sub Total</td>
                                                        <td>Remove</td>
                                                    </tr>
                                                </thead>
                                                <tbody id="myTable2">
                                                    <c:forEach var="tempList3" items="${CARTLIST}">
                                                        <c:url var="deleteLink" value="CartController">
                                                            <c:param name="command" value="DELETE"/>
                                                            <c:param name="cartId" value="${tempList3.cartId}"/>
                                                        </c:url>
                                                        <c:url var="increaseLink" value="CartController">
                                                            <c:param name="command" value="INCREASE"/>
                                                            <c:param name="cartId" value="${tempList3.cartId}"/>
                                                            <c:param name="medicineId" value="${tempList3.medicineId}"/>
                                                        </c:url>
                                                        <c:url var="reduceLink" value="CartController">
                                                            <c:param name="command" value="REDUCE"/>
                                                            <c:param name="cartId" value="${tempList3.cartId}"/>
                                                            <c:param name="medicineId" value="${tempList3.medicineId}"/>
                                                        </c:url>

                                                        <tr>
                                                            <td>${tempList3.productName}</td>
                                                            <c:choose>
                                                                <c:when  test="${tempList3.quantity > 1}">
                                                                    <td><a href='${reduceLink}' class="customButton"> <span class="las la-minus"></span></a></td>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${tempList3.quantity}</td>
                                                            <td><a href='${increaseLink}' class="customButton"> <span class="las la-plus"></span></a></td>
                                                            <td>Rs.${tempList3.subTotal}</td>
                                                            <td><a href='${deleteLink}' class="deleteButton"> <span class="las la-times"></span></a></td>
                                                        </tr> 
                                                    </c:forEach>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td><span style='font-weight: 700'>Total -</span> Rs.${cartTotal}</td>
                                                        <td></td>
                                                    </tr> 
                                                </tbody>
                                            </table>
                                        </div> 
                                    </div>
                                </div>
                            </div> 
                        </c:otherwise>
                    </c:choose>

                </div>

            </main>
        </div>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
        <script type="text/javascript" src="JS/paging.js"></script> 
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tableData').paging({limit: 8});
                $('#tableData2').paging({limit: 20});
                $('#tableData3').paging({limit: 8});
                $('#search').keyup(function () {
                    search_table($(this).val(), 'myTable');
                });
                $('#search1').keyup(function () {
                    search_table($(this).val(), 'myTable1');
                });


                function search_table(value, table) {
                    $('#' + table + ' tr').each(function () {
                        var found = 'false';
                        $(this).each(function () {
                            if ($(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0) {
                                found = 'true';
                            }
                        });
                        if (found === 'true') {
                            $(this).show();
                        } else {
                            $(this).hide();
                        }
                    });
                }
            });
        </script>
    </body>
</html>