<%-- 
    Document   : customerOrderPlacedSuccess
    Created on : Dec 15, 2021, 3:13:08 PM
    Author     : Topsy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Order Placed Successfully</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="CSS/signUp.css">
        <link rel="stylesheet" href="CSS/footer.css">
    </head>
    <body>
    <c:url var="customerLink" value="CustomerController">
        <c:param name="command" value="HOME"/>
    </c:url>
    <br>
    <br>
    <br>
    <h1 class="title-h1-large" >EASY<span class='alt-color'>PILL</span></h1>
    <img class="reading3-svg" src="IMG/finish.svg" />
    <img class="watching-svg" src="IMG/welcome.svg" />
    <div class="main">
        <section class="signup">           
            <div class="container">
                <div class="signup-content">
                    <h2 class="form-title"> Order Place Successfully.</h2>
                    <p class="detail-box">The order will be visible in the order tab.</p>
                    <br>
                    <br>
                    <div class="form-group">
                        <input type="submit" name="submit" id="submit" class="form-submit" value="Ok" onclick="location.href = '${customerLink}'"/>
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