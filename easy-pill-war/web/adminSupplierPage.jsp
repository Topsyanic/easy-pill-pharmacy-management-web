<%-- 
    Document   : adminSupplierPage
    Created on : Dec 1, 2021, 1:39:00 PM
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
            <c:url var="requestLink" value="SupplierController">
                <c:param name="command" value="REQUEST"/>
            </c:url>
            <c:url var="addSupplier" value="/adminAddSupplierPage.jsp">
            </c:url>
            <c:url var="logoutLink" value="LogoutServlet">
            </c:url>
            <div class="sidebar-menu">
                <ul>
                    <li><a href="${dashboardLink}"><span class="las la-home"></span><span>Dashboard</span></a></li>
                    <li><a href="${customerLink}"><span class="las la-user-alt"></span><span>Customers</span></a></li>
                    <li><a href="${doctorLink}"><span class="las la-stethoscope"></span><span>Doctors</span></a></li>
                    <li><a href="${pharmacistLink}" ><span class="las la-user-nurse"></span><span>Pharmacists</span></a></li>
                    <li><a href="${inStockLink}"><span class="las la-first-aid"></span><span>Medicine In Stock</span></a></li>
                    <li><a href="${outStockLink}"><span class="las la-band-aid"></span><span>Medicine Out of Stock</span></a></li>
                    <li><a href="${supplierLink}"  class="active"><span class="las la-truck"></span><span>Suppliers</span></a></li>
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
                    Suppliers
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
                            <h1>${supplierCount}</h1>
                            <span>Suppliers</span>
                        </div>
                        <div>
                            <span class=" las la-truck"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${requestCount}</h1>
                            <span>Requests Sent</span>
                        </div>
                        <div>
                            <span class="las la-envelope"></span>
                        </div>
                    </div>
                </div>
                <div class="recent-grid">
                </div>
                <div class="projects">
                    <div class="card">
                        <div class="card-header">
                            <h3>Suppliers</h3>
                            <c:choose>
                                <c:when  test="${role == 'admin'}">
                                    <a href='${addSupplier}' class="customButton">Add <span class="las la-plus"></span></a> 
                                    </c:when>
                                </c:choose>
                            <input class="search-box" id="search" placeholder="Search..." >
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table width="100%" id="tableData">
                                    <thead>
                                        <tr>
                                            <td>Supplier ID</td>
                                            <td>Name</td>
                                            <td>Address</td>
                                            <td>Contact No</td>
                                            <td>email</td>
                                            <c:choose>
                                                <c:when  test="${role == 'admin'}">
                                                    <td>Option</td>
                                                </c:when>
                                            </c:choose>
                                        </tr>
                                    </thead>
                                    <tbody id="myTable">
                                        <c:forEach var="tempList" items="${SUPPLIERLIST}">
                                            <c:url var="editLink" value="/adminSupplierOptionsPage.jsp">
                                                <c:param name="supplierId" value="${tempList.supplierId}"/>
                                            </c:url>
                                            <tr>
                                                <td>${tempList.supplierId}</td>
                                                <td>${tempList.name}</td>
                                                <td>${tempList.address}</td>
                                                <td>${tempList.contactNo}</td>
                                                <td>${tempList.email}</td>
                                                <c:choose>
                                                    <c:when  test="${role == 'admin'}">
                                                        <td><a href='${editLink}' class="customButton"> <span class="las la-edit"></span></a></td>
                                                            </c:when>
                                                        </c:choose>
                                            </tr> 
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div> 
                        </div>
                    </div>


                </div>
                <div class="recent-grid">
                </div>
                <div class="projects">
                    <div class="card">
                        <div class="card-header">
                            <h3>Inventory Requests</h3>
                            <a href='${requestLink}' class="customButton">Add <span class="las la-plus"></span></a> 
                            <input class="search-box" id="search1" placeholder="Search..." >
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table width="100%" id="tableData2">
                                    <thead>
                                        <tr>
                                            <td>Request Id</td>
                                            <td>Message</td>
                                            <td>Date</td>
                                            <td>Supplier ID</td>
                                    </thead>
                                    <tbody id="myTable1">
                                        <c:forEach var="tempList2" items="${REQUESTLIST}">
                                            <tr>
                                                <td>${tempList2.requestId}</td>
                                                <td>${tempList2.message}</td>
                                                <td>${tempList2.date}</td>
                                                <td>${tempList2.supplierId}</td>
                                            </tr> 
                                        </c:forEach>
                                    </tbody>
                                </table>
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