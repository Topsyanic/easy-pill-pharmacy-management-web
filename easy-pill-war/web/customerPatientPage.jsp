<%-- 
    Document   : customerPatientPage
    Created on : Dec 19, 2021, 5:33:38 PM
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
            <c:url var="logoutLink" value="LogoutServlet">
            </c:url>
            <div class="sidebar-menu">
                <ul>
                    <li><a href="${homeLink}" ><span class="las la-store-alt"></span><span>Shop</span></a></li>
                    <li><a href="${cartLink}"><span class="las la-shopping-bag"></span><span>Cart</span></a></li>
                    <li><a href="${precriptionLink}" ><span class="las la-notes-medical"></span><span>Prescriptions</span></a></li>
                    <li><a href="${orderLink}" ><span class="las la-list"></span><span>Orders</span></a></li>
                    <li><a href="${historyLink}"><span class="las la-history"></span><span>History</span></a></li>
                                <c:choose>
                                    <c:when  test="${role == 'doctor'}">
                            <li><a href="${patientsLink}"  class="active"><span class="las la-user"></span><span>Patients</span></a></li>
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
                    Patients
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
                            <h1>${customerCount}</h1>
                            <span>Patients</span>
                        </div>
                        <div>
                            <span class=" las la-user-alt"></span>
                        </div>
                    </div>
                </div>
                <div class="recent-grid"></div>
                <div class="projects">
                    <div class="card">
                        <div class="card-header">
                            <h3>Patients</h3>
                            <input class="search-box" id="search" placeholder="Search..." >
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table width="100%" id="tableData">
                                    <thead>
                                        <tr>
                                            <td>User ID</td>
                                            <td>First Name</td>
                                            <td>Last Name</td>
                                            <td>Email</td>
                                            <td>Address</td>
                                            <td>Contact</td>
                                        </tr>
                                    </thead>
                                    <tbody id="myTable">
                                        <c:forEach var="tempList" items="${CUSTOMERLIST}">
                                            <c:url var="deleteLink" value="/adminRemoveUserPage.jsp">
                                                <c:param name="command" value="DELETEUSER"/>
                                                <c:param name="tab" value="customer"/>
                                                <c:param name="firstName" value="${tempList.firstName}"/>
                                                <c:param name="lastName" value="${tempList.lastName}"/>
                                                <c:param name="email" value="${tempList.email}"/>
                                                <c:param name="userId" value="${tempList.userId}"/>
                                            </c:url>
                                            <tr>
                                                <td>${tempList.userId}</td>
                                                <td>${tempList.firstName}</td>
                                                <td>${tempList.lastName}</td>
                                                <td>${tempList.email}</td>
                                                <td>${tempList.address}</td>
                                                <td>${tempList.contactNo}</td>
                                            </tr> 
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div> 
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
        $('#tableData').paging({limit: 8});
        $('#tableData2').paging({limit: 8});
        $('#tableData3').paging({limit: 8});
        $('#search').keyup(function () {
            search_table($(this).val(), 'myTable');
        });
        $('#search1').keyup(function () {
            search_table($(this).val(), 'myTable1');
        });
        $('#search2').keyup(function () {
            search_table($(this).val(), 'myTable2');
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