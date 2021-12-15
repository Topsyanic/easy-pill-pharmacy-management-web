<%-- 
    Document   : customerHomePage
    Created on : Dec 9, 2021, 11:43:24 AM
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
            <c:url var="orderLink" value="OrderController">
                <c:param name="command" value="CUSTOMERORDERS"/>
            </c:url>
            <c:url var="historyLink" value="OrderController">
                <c:param name="command" value="HISTORY"/>
            </c:url>
            <c:url var="patientsLink" value="UserController">
                <c:param name="command" value="PATIENTS"/>
            </c:url>
            <c:url var="logoutLink" value="LogoutServlet">
            </c:url>
            <div class="sidebar-menu">
                <ul>
                    <li><a href="${homeLink}" class="active"><span class="las la-store-alt"></span><span>Shop</span></a></li>
                    <li><a href="${cartLink}"><span class="las la-shopping-bag"></span><span>Cart</span></a></li>
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
                    Shop
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
                <div class="recent-grid">
                </div>
                <c:forEach var="tempList3" items="${MEDICINELIST}">
                    <c:url var="cartLink" value="/customerAddToCartPage.jsp">
                        <c:param name="name" value="${tempList3.name}"/>
                        <c:param name="medicineId" value="${tempList3.medicineId}"/>
                        <c:param name="weight" value="${tempList3.weight}"/>
                        <c:param name="price" value="${tempList3.price}"/>
                        <c:param name="imagePath" value="${tempList3.imagePath}"/>
                        <c:param name="quantity" value="${tempList3.quantity}"/>
                        <c:param name="requirePres" value="${tempList3.requirePres}"/>
                        <c:param name="description" value="${tempList3.description}"/>
                    </c:url>
                    <div class="prescription-card">
                        <div class="card-image">
                            <img src="${tempList3.imagePath}"/>  
                        </div>
                        <h3 class="card-title-text">${tempList3.name}</h3>
                        <div class="card-details">
                            <p class="card-text">Availability - ${tempList3.quantity}</p>
                            <p class="card-text">Weight - ${tempList3.weight}</p>
                        </div>
                        <div class="card-bottom">
                            <c:choose>
                                <c:when  test="${SessionDetails.userRole == 'doctor'}">
                                    <c:choose>
                                        <c:when  test="${tempList3.quantity > 0}">
                                            <p class="instock-text">In Stock</p>
                                            <h3 class="date-text">Rs.${tempList3.price}</h3>
                                            <a class="cartButton" href='${cartLink}'><h1 class="las la-shopping-bag"></h1></a>
                                            </c:when>
                                            <c:otherwise>
                                            <p class="outstock-text">Out Of Stock</p>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when  test="${tempList3.requirePres == 'No'}">   
                                            <c:choose>
                                                <c:when  test="${tempList3.quantity > 0}">
                                                    <p class="instock-text">In Stock</p>
                                                    <h3 class="date-text">Rs.${tempList3.price}</h3>
                                                    <a class="cartButton" href='${cartLink}'><h1 class="las la-shopping-bag"></h1></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <p class="outstock-text">Out Of Stock</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="outstock-text">Requires Prescription</p>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </c:forEach>
            </main>
        </div>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
        <script type="text/javascript" src="JS/paging.js"></script> 
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tableData').paging({limit: 2});
                $('#tableData2').paging({limit: 8});
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