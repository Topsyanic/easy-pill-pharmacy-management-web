
<%-- 
    Document   : adminMedicine
    Created on : Nov 27, 2021, 4:49:42 PM
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
            <c:url var="addMedicine" value="MedicineController">
                 <c:param name="command" value="ADDMEDICINE"/>
            </c:url>
            <c:url var="logoutLink" value="LogoutServlet">
            </c:url>
            <div class="sidebar-menu">
                <ul>
                    <li><a href="${dashboardLink}" ><span class="las la-home"></span><span>Dashboard</span></a></li>
                    <li><a href="${userLink}" ><span class="las la-users"></span><span>Users</span></a></li>
                    <li><a href="${medicineLink}" class="active"><span class="las la-first-aid"></span><span>Medicine</span></a></li>
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
                    Medicine
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
                            <h1>${inStockCount}</h1>
                            <span>In Stock</span>
                        </div>
                        <div>
                            <span class=" las la-check-circle"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${outOfStockCount}</h1>
                            <span>Out Of Stock</span>
                        </div>
                        <div>
                            <span class="las la-exclamation-circle"></span>
                        </div>
                    </div>
                    <div class="cards-single">
                        <div>
                            <h1>${medicineCount}</h1>
                            <span>Total Medicine</span>
                        </div>
                        <div>
                            <span class="las la-capsules"></span>
                        </div>
                    </div>
                </div>
                <div class="recent-grid">
                    <div class="projects">
                        <div class="card">
                            <div class="card-header">
                                <h3>Medicine In Stock</h3>
                                <a href='${addMedicine}' class="customButton">Add <span class="las la-plus"></span></a> 
                                <input class="search-box" id="search" placeholder="Search..." >
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table width="100%" id="tableData">
                                        <thead>
                                            <tr>
                                                <td>Name</td>
                                                <td>Description</td>
                                                <td>Weight</td>
                                                <td>Price</td>
                                                <td>Quantity</td>
                                                <td>Supplier ID</td>
                                                <td>Option</td>
                                            </tr>
                                        </thead>
                                        <tbody id="myTable">
                                            <c:forEach var="tempList" items="${INSTOCKLIST}">
                                                <c:url var="editLink" value="/adminMedicineOptionsPage.jsp">
                                                    <c:param name="medicineId" value="${tempList.medicineId}"/>
                                                </c:url>
                                                <tr>
                                                    <td>${tempList.name}</td>
                                                    <td>${tempList.description}</td>
                                                    <td>${tempList.weight}</td>
                                                    <td>${tempList.price}</td>
                                                    <td>${tempList.quantity}</td>
                                                    <td>${tempList.supplierId}</td>
                                                    <td><a href='${editLink}' class="customButton"> <span class="las la-edit"></span></a></td>
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
                                <h3>Medicine Out Of Stock</h3>
                                <a href='${addMedicine}' class="customButton">Add <span class="las la-plus"></span></a> 
                                <input class="search-box" id="search1" placeholder="Search..." >
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table width="100%" id="tableData2">
                                        <thead>
                                            <tr>
                                                <td>Name</td>
                                                <td>Description</td>
                                                <td>Weight</td>
                                                <td>Price</td>
                                                <td>Quantity</td>
                                                <td>Supplier ID</td>
                                                <td>Option</td>
                                        </thead>
                                        <tbody id="myTable1">
                                            <c:forEach var="tempList2" items="${OUTSTOCKLIST}">
                                                <c:url var="editLink" value="/adminMedicineOptionsPage.jsp">
                                                    <c:param name="medicineId" value="${tempList2.medicineId}"/>
                                                </c:url>
                                                <tr>
                                                    <td>${tempList2.name}</td>
                                                    <td>${tempList2.description}</td>
                                                    <td>${tempList2.weight}</td>
                                                    <td>${tempList2.price}</td>
                                                    <td>${tempList2.quantity}</td>
                                                    <td>${tempList2.supplierId}</td>
                                                    <td><a href='${editLink}' class="customButton"> <span class="las la-edit"></span></a></td>
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