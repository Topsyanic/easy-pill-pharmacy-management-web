<%-- 
    Document   : adminUser
    Created on : Nov 26, 2021, 6:22:08 PM
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
            <c:url var="dashboardLink" value="AdminController">
                <c:param name="command" value="HOME"/>
            </c:url>
            <c:url var="userLink" value="UserController">
                <c:param name="command" value="USER"/>
            </c:url>
            <c:url var="medicineLink" value="MedicineController">
                <c:param name="command" value="MEDICINE"/>
            </c:url>
            <c:url var="supplierLink" value="SupplierController">
                <c:param name="command" value="SUPPLIER"/>
            </c:url>
            <c:url var="orderLink" value="OrderController">
                <c:param name="command" value="ORDER"/>
            </c:url>
            <c:url var="addCustomer" value="/adminAddCustomer.jsp">
            </c:url>
            <c:url var="addPharmacist" value="/adminAddPharmacist.jsp">
            </c:url>
            <c:url var="addDoctor" value="/adminAddDoctor.jsp">
            </c:url>
            <c:url var="logoutLink" value="LogoutServlet">
            </c:url>
            <div class="sidebar-menu">
                <ul>
                    <li><a href="${dashboardLink}" ><span class="las la-home"></span><span>Dashboard</span></a></li>
                    <li><a href="${userLink}" class="active"><span class="las la-users"></span><span>Users</span></a></li>
                    <li><a href="${medicineLink}"><span class="las la-first-aid"></span><span>Medicine</span></a></li>
                    <li><a href="${supplierLink}"><span class="las la-truck"></span><span>Suppliers</span></a></li>
                    <li><a href="${orderLink}"><span class="las la-shopping-cart"></span><span>Orders</span></a></li>
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
                    Users
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
                            <span>Customers</span>
                        </div>
                        <div>
                            <span class=" las la-user-alt"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${doctorCount}</h1>
                            <span>Doctors</span>
                        </div>
                        <div>
                            <span class="las la-stethoscope"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${pharmacistCount}</h1>
                            <span>Pharmacists</span>
                        </div>
                        <div>
                            <span class="las la-user-nurse"></span>
                        </div>
                    </div>
                </div>
                <div class="recent-grid">
                    <div class="projects">
                        <div class="card">
                            <div class="card-header">
                                <h3>Customers</h3>
                                <a href='${addCustomer}' class="customButton">Add <span class="las la-plus"></span></a> 
                                <input class="search-box" id="search" placeholder="Search..." >
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table width="100%" id="tableData">
                                        <thead>
                                            <tr>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Email</td>
                                                <td>Address</td>
                                                <td>Contact</td>
                                                <td>Remove</td>
                                            </tr>
                                        </thead>
                                        <tbody id="myTable">
                                            <c:forEach var="tempList" items="${CUSTOMERLIST}">
                                                <c:url var="deleteLink" value="UserController">
                                                    <c:param name="command" value="DELETEUSER"/>
                                                    <c:param name="firstName" value="${tempList.firstName}"/>
                                                    <c:param name="lastName" value="${tempList.lastName}"/>
                                                    <c:param name="email" value="${tempList.email}"/>
                                                    <c:param name="userId" value="${tempList.userId}"/>
                                                </c:url>
                                                <tr>
                                                    <td>${tempList.firstName}</td>
                                                    <td>${tempList.lastName}</td>
                                                    <td>${tempList.email}</td>
                                                    <td>${tempList.address}</td>
                                                    <td>${tempList.contactNo}</td>
                                                    <td><a href='${deleteLink}' class="deleteButton"> <span class="las la-times"></span></a></td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div> 
                            </div>
                        </div>
                    </div>
                    <div class="projects">
                        <div class="card">
                            <div class="card-header">
                                <h3>Doctors</h3>
                                <a href='${addDoctor}' class="customButton">Add <span class="las la-plus"></span></a> 
                                <input class="search-box" id="search1" placeholder="Search..." >
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table width="100%" id="tableData2">
                                        <thead>
                                            <tr>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Email</td>
                                                <td>Address</td>
                                                <td>Contact</td>
                                                <td>Expertise</td>
                                                <td>Remove</td>
                                            </tr>
                                        </thead>
                                        <tbody id="myTable1">
                                            <c:forEach var="tempList2" items="${DOCTORLIST}">
                                                <c:url var="deleteLink" value="UserController">
                                                    <c:param name="command" value="DELETEUSER"/>
                                                    <c:param name="firstName" value="${tempList2.firstName}"/>
                                                    <c:param name="lastName" value="${tempList2.lastName}"/>
                                                    <c:param name="email" value="${tempList2.email}"/>
                                                    <c:param name="userId" value="${tempList2.userId}"/>
                                                </c:url>
                                                <tr>
                                                    <td>${tempList2.firstName}</td>
                                                    <td>${tempList2.lastName}</td>
                                                    <td>${tempList2.email}</td>
                                                    <td>${tempList2.address}</td>
                                                    <td>${tempList2.contactNo}</td>
                                                    <td>${tempList2.expertise}</td>
                                                    <td><a href='${deleteLink}' class="deleteButton"> <span class="las la-times"></span></a></td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div> 
                            </div>
                        </div>
                    </div>
                    <div class="projects">
                        <div class="card">
                            <div class="card-header">
                                <h3>Pharmacists</h3>
                                <a href='${addPharmacist}' class="customButton">Add <span class="las la-plus"></span></a> 
                                <input class="search-box" id="search2" placeholder="Search..." >
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table width="100%" id="tableData3">
                                        <thead>
                                            <tr>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Email</td>
                                                <td>Address</td>
                                                <td>Contact</td>
                                                <td>Remove</td>
                                            </tr>
                                        </thead>
                                        <tbody id="myTable2">
                                            <c:forEach var="tempList3" items="${PHARMACISTLIST}">
                                                <c:url var="deleteLink" value="UserController">
                                                    <c:param name="command" value="DELETEUSER"/>
                                                    <c:param name="firstName" value="${tempList3.firstName}"/>
                                                    <c:param name="lastName" value="${tempList3.lastName}"/>
                                                    <c:param name="email" value="${tempList3.email}"/>
                                                    <c:param name="userId" value="${tempList3.userId}"/>
                                                </c:url>
                                                <tr>
                                                    <td>${tempList3.firstName}</td>
                                                    <td>${tempList3.lastName}</td>
                                                    <td>${tempList3.email}</td>
                                                    <td>${tempList3.address}</td>
                                                    <td>${tempList3.contactNo}</td>
                                                    <td><a href='${deleteLink}' class="deleteButton"> <span class="las la-times"></span></a></td>
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