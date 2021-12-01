<%-- 
    Document   : adminUpdateSupplierContactPage
    Created on : Dec 1, 2021, 2:32:39 PM
    Author     : Topsy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
       <title>Update Supplier Contact</title>
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
                            <input name='option' value='contact' hidden>
                            <input name='supplierId' value='${supplierId}' hidden>
                            <h2 class="form-title">Update Supplier Contact</h2>
                            <p style='text-align: center;  margin-bottom: 15px;'>Current contact number of this supplier is '${contact}'.</p>
                            <p style='text-align: center; margin-bottom: 15px;'>Please enter the new contact below.</p>
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="tel" class="form-input" name="newContact" required id="name" placeholder="Contact Number Ex: 0712345689" value="${contact}" pattern="[0]{1}[7,1]{1}[0-9]{8}"/>
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
