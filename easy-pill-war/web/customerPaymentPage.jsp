<%-- 
    Document   : customerPaymentPage
    Created on : Dec 15, 2021, 11:19:03 AM
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
        <title>Payment Page</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="CSS/signUp.css">
        <link rel="stylesheet" href="CSS/footer.css">
    </head>
    <c:url var="cartLink" value="CartController">
        <c:param name="command" value="CART"/>
    </c:url>
    <body>
        <br>
        <br>
        <br>
        <h1 class="title-h1-large" >EASY<span class='alt-color'>PILL</span></h1>
        <img class="reading3-svg" src="IMG/happy1.svg" />
        <img class="watching-svg" src="IMG/happy2.svg" />
        <div class="main">
            <section class="signup">           
                <div class="container">
                    <div class="signup-content">
                        <form action="OrderController" method="POST" class="signup-form">
                            <input name="command" value="PLACEORDER" hidden="">
                            <input name="amount" value="${cartTotal}" hidden="">
                            <input name="orderDetails" value="${orderDetails}" hidden="">
                            <h2 class="form-title" > make Payment</h2>
                            <p >NAME ON CARD</p>
                            <div class="form-group">
                                <input  class="form-input" name="nameOnCard" id="firstName" placeholder="Name on card" required=""/>
                            </div>
                            <p >CARD NUMBER</p>
                            <div class="form-group">
                                <input  class="form-input" type="number" name="cardNumber" id="cardNumber" placeholder="Card Number" required=""  oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                                        maxlength = "19"
                                        />
                            </div>
                            <p>CVV NUMBER</p>
                            <div class="form-group">
                                <input  class="form-input" type="number" name="cvv" id="cvv" placeholder="CVV/CVC" required=""
                                        oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                                        maxlength = "4"/>
                            </div>
                            <p>EXPIRATION DATE</p>
                            <div class="form-group">
                                <input  class="form-input"  name="expireDate" id="cvv" placeholder="MM/YY" required=""
                                        oninput="javascript: if (this.value.length == 2) this.value = this.value=this.value+'/';" />
                            </div>
                            <p>Total Amount - Rs. ${cartTotal}</p>
                            <div class="form-group">
                                <input type="submit" name="submit" id="submit" class="form-submit" value="Proceed"/>
                                <p class="loginhere"><a class="login-here-link-text" onclick="location.href = '${cartLink}'">Go Back</a></p>
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