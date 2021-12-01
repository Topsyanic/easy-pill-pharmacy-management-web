<%-- 
    Document   : adminMedicineOptionsPage
    Created on : Nov 29, 2021, 4:46:47 PM
    Author     : Topsy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Medicine Options Page</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="CSS/movieOptions.css">
        <link rel="stylesheet" href="CSS/footer.css">
    </head>
    <c:url var="medicineLink" value="MedicineController">
        <c:param name="command" value="MEDICINE"/>
    </c:url>
    <c:url var="imageLink" value="MedicineController">
        <c:param name="command" value="MEDICINEOPTION"/>
        <c:param name="option" value="image"/>
        <c:param name="medicineId" value='<%=request.getParameter("medicineId")%>'/>
    </c:url>

    <c:url var="nameLink" value="MedicineController">
        <c:param name="command" value="MEDICINEOPTION"/>
        <c:param name="option" value="name"/>
        <c:param name="medicineId" value='<%=request.getParameter("medicineId")%>'/>
    </c:url>
    <c:url var="descriptionLink" value="MedicineController">
        <c:param name="command" value="MEDICINEOPTION"/>
        <c:param name="option" value="description"/>
        <c:param name="medicineId" value='<%=request.getParameter("medicineId")%>'/>
    </c:url>
    <c:url var="priceLink" value="MedicineController">
        <c:param name="command" value="MEDICINEOPTION"/>
        <c:param name="option" value="price"/>
        <c:param name="medicineId" value='<%=request.getParameter("medicineId")%>'/>
    </c:url>
    <c:url var="quantityLink" value="MedicineController">
        <c:param name="command" value="MEDICINEOPTION"/>
        <c:param name="option" value="quantity"/>
        <c:param name="medicineId" value='<%=request.getParameter("medicineId")%>'/>
    </c:url>
    <c:url var="deleteLink" value="MedicineController">
        <c:param name="command" value="MEDICINEOPTION"/>
        <c:param name="option" value="delete"/>
        <c:param name="medicineId" value='<%=request.getParameter("medicineId")%>'/>
    </c:url>
    <body>
        <br>
        <br>
        <br>
        <h1 class="title-h1-large" >EASY<span class='alt-color'>PILL</span></h1>
        <img class="reading3-svg" src="IMG/happy3.svg" />
        <img class="watching-svg" src="IMG/happy1.svg" />
        <div class="main">
            <section class="signup">           
                <div class="container">
                    <div class="signup-content">
                        <h2 class="form-title"> Medicine Edit Options</h2>
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Change Image" onclick="location.href = '${imageLink}'"/>
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Change Name" onclick="location.href = '${nameLink}'"/>
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Change Description" onclick="location.href = '${descriptionLink}'"/>
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Change Price" onclick="location.href = '${priceLink}'"/>
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Change Quantity" onclick="location.href = '${quantityLink}'"/>
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Remove Medicine" onclick="location.href = '${deleteLink}'"/>
                            <input type="submit" id="submit" class="form-submit" value="Go Back" onclick="location.href = '${medicineLink}'"/>
                        </div>                   
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
        <script src="JS/signUp.js"></script>
    </body>
</html>