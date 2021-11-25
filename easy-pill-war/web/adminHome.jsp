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
        <link rel="stylesheet" href="CSS/adminPage.css">
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
                <c:url var="dashboardLink" value="ModeratorController">
                    <c:param name="command" value="HOME"/>
                </c:url>
                <c:url var="customerLink" value="ModeratorController">
                    <c:param name="command" value="CUSTOMER"/>
                </c:url>
                <c:url var="movieLink" value="MovieController">
                    <c:param name="command" value="MOVIE"/>
                </c:url>
                <c:url var="bookLink" value="BookController">
                    <c:param name="command" value="BOOK"/>
                </c:url>
                <c:url var="offerLink" value="OfferController">
                    <c:param name="command" value="OFFER"/>
                </c:url>
                <c:url var="logoutLink" value="LogoutServlet">
                </c:url>
                <ul>
                    <li><a href="${dashboardLink}" class="active"><span class="las la-home"></span><span>Dashboard</span></a></li>
                    <li><a href="${customerLink}"><span class="las la-users"></span><span>Users</span></a></li>
                    <li><a href="${bookLink}"><span class="las la-first-aid"></span><span>Medicine</span></a></li>
                    <li><a href="${movieLink}"><span class="las la-truck"></span><span>Suppliers</span></a></li>
                    <li><a href="${offerLink}"><span class="las la-shopping-cart"></span><span>Orders</span></a></li>
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

                <div class="search-wrapper">
                    <span class="las la-search"></span>
                    <input type="search" placeholder="Search here"/>
                </div>

                <div class="user-wrapper">
                    <img src="IMG/admin.jpg" width="40px" height="40px" alt="">
                    <div>
                        <h4>${username}</h4>
                        <small>Super admin</small>
                    </div>
                </div>
            </header>

            <main>
                <div class="cards">
                    <div class="cards-single">
                        <div>
                            <h1>${customerCount}</h1>
                            <span>Users</span>
                        </div>
                        <div>
                            <span class="las la-users"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${bookCount}</h1>
                            <span>Medicine</span>
                        </div>
                        <div>
                            <span class="las la-first-aid"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${movieCount}</h1>
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
                                <h3>Recent Offers</h3>
                                <a class="linkButton" href="${offerLink}">See More <span class="las la-arrow-right"></span></a>
                            </div>

                            <div class="card-body">
                                <div class="table-responsive">
                                    <table width="100%" id="tableData">
                                        <thead>
                                            <tr>
                                                <td>Offer Id</td>
                                                <td>Message</td>
                                                <td>Date</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="tempList" items="${OFFERLIST}">
                                                <tr>
                                                    <td>${tempList.offerId}</td>
                                                    <td>${tempList.message}</td>
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
                                <c:forEach var="tempList" items="${UNVERIFIEDLIST}">
                                    <div class="customer" >
                                        <div class="info">
                                            <img src="${tempList.photoUrl}" width="40px" height="40px" alt="">
                                            <div>
                                                <h4>${tempList.firstName} ${tempList.lastName}</h4>
                                                <small>${tempList.plan}</small>
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
        <script type="text/javascript" src="JS/paging.js"></script> 
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tableData').paging({limit: 10});
            });
        </script>
    </body>
</html>