<%-- 
    Document   : adminUpdateSupplierAddressPage
    Created on : Dec 1, 2021, 2:19:28 PM
    Author     : Topsy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
       <title>Update Supplier Address</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="CSS/updateMedicineImage.css">
        <link rel="stylesheet" href="CSS/footer.css">
    </head>
    <body>
        <c:url var="supplierLink" value="SupplierController">
            <c:param name="command" value="SUPPLIER"/>
        </c:url>

        <br>
        <br>
        <br>
        <h1 class="title-h1-large" >EASY<span class='alt-color'>PILL</span></h1>
        <img class="reading3-svg" src="IMG/update1.svg" />
        <img class="watching-svg" src="IMG/update2.svg" />
        <div class="main">
            <section class="signup">           
                <div class="container">
                    <div class="signup-content">
                        <form action="SupplierController" method="POST"  class="signup-form">
                            <input name='command' value='UPDATESUPPLIER' hidden>
                            <input name='option' value='address' hidden>
                            <input name='supplierId' value='${supplierId}' hidden>
                            <h2 class="form-title">Update Supplier Address</h2>
                            <p style='text-align: center;  margin-bottom: 15px;'>Current address of this supplier is '${address}'.</p>
                            <p style='text-align: center; margin-bottom: 15px;'>Please enter the new address below.</p>
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="text" class="form-input" name="newAddress" required id="name" placeholder="New Address" />
                                </div>
                                <input type="submit" name="submit" id="submit" class="form-submit" value="Update "/>
                                <input type="button" name="submit" id="submit" class="form-submit" value="cancel " onclick="history.back()"/>
                            </div>           
                        </form>
                    </div>               
                </div>
            </section>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
        </div>
        <!--         FOOTER      -->
        <footer class="footer">
            <div class="footer-container">
                <div class="footer-row">
                    <div class="footer-col">
                        <h4>company</h4>
                        <ul>
                            <li><a href="#">about us</a></li>
                            <li><a href="#">our services</a></li>
                            <li><a href="#">privacy policy</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>get help</h4>
                        <ul>
                            <li><a href="#">FAQ</a></li>
                            <li><a href="#">payment options</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>Locations</h4>
                        <ul>
                            <li><a href="#">Colombo 14</a></li>
                            <li><a href="#">Colombo 12</a></li>
                            <li><a href="#">Colombo 07 </a></li>
                            <li><a href="#">Colombo 04 </a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>follow us</h4>
                        <div class="social-links">
                            <a href="#"><i class="fab fa-facebook-f"></i></a>
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <script src="VENDOR/jquery/jquery.min.js"></script>
        <script src="JS/update.js"></script>
    </body>
</html> <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Update Medicine Name</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="CSS/updateMedicineImage.css">
        <link rel="stylesheet" href="CSS/footer.css">
    </head>
    <body>
        <c:url var="medicineLink" value="MedicineController">
            <c:param name="command" value="MEDICINE"/>
        </c:url>

        <br>
        <br>
        <br>
        <h1 class="title-h1-large" >EASY<span class='alt-color'>PILL</span></h1>
        <img class="reading3-svg" src="IMG/update1.svg" />
        <img class="watching-svg" src="IMG/update2.svg" />
        <div class="main">
            <section class="signup">           
                <div class="container">
                    <div class="signup-content">
                        <form action="MedicineController" method="POST"  class="signup-form">
                            <input name='command' value='UPDATEMEDICINE' hidden>
                            <input name='option' value='name' hidden>
                            <input name='name' value='${name}' hidden>
                            <input name='medicineId' value='${medicineId}' hidden>
                            <h2 class="form-title">Update Medicine Name</h2>
                            <p style='text-align: center;  margin-bottom: 15px;'>Current name of this medicine is '${name}'.</p>
                            <p style='text-align: center; margin-bottom: 15px;'>Please enter the new name below.</p>
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="text" class="form-input" name="newname" required id="name" placeholder="New Name" />
                                </div>
                                <input type="submit" name="submit" id="submit" class="form-submit" value="Update "/>
                                <input type="button" name="submit" id="submit" class="form-submit" value="cancel " onclick="history.back()"/>
                            </div>           
                        </form>
                    </div>               
                </div>
            </section>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
        </div>
        <!--         FOOTER      -->
        <footer class="footer">
            <div class="footer-container">
                <div class="footer-row">
                    <div class="footer-col">
                        <h4>company</h4>
                        <ul>
                            <li><a href="#">about us</a></li>
                            <li><a href="#">our services</a></li>
                            <li><a href="#">privacy policy</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>get help</h4>
                        <ul>
                            <li><a href="#">FAQ</a></li>
                            <li><a href="#">payment options</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>Locations</h4>
                        <ul>
                            <li><a href="#">Colombo 14</a></li>
                            <li><a href="#">Colombo 12</a></li>
                            <li><a href="#">Colombo 07 </a></li>
                            <li><a href="#">Colombo 04 </a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>follow us</h4>
                        <div class="social-links">
                            <a href="#"><i class="fab fa-facebook-f"></i></a>
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <script src="VENDOR/jquery/jquery.min.js"></script>
        <script src="JS/update.js"></script>
    </body>
</html>