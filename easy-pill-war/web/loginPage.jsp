<%-- 
    Document   : loginPage
    Created on : Jul 24, 2021, 11:13:46 AM
    Author     : Raaid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login Page</title>
    <link rel="stylesheet" href="FONTS/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="CSS/login.css">
    <link rel="stylesheet" href="CSS/footer.css">
</head>
<body>
<br>
<br>
<br>
<h1 class="title-h1-large">EASY<span class='alt-color'> PILL</span></h1>
<img class="reading3-svg" src="IMG/doctor-1.svg"/>
<img class="watching-svg" src="IMG/doctor-2.svg"/>
<div class="main">
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <form action="LoginServlet" method="POST" class="signup-form">
                    <h2 class="form-title">Sign In</h2>
                    <div class="form-group">
                        <input type="email" class="form-input" name="email" id="email" placeholder="Email" value="${email}"/>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-input" name="password" id="password" placeholder="Password" />
                        <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        <p class="error-message">${ErrorMessage}</p>
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" id="submit" class="form-submit" value="Sign In"/>
                    </div>

                </form>
                <p class="loginhere">
                    No account ? Sign Up as <a href="customerSignUpPage.jsp" class="login-here-link-text">Customer</a> or <a href="doctorSignUpPage.jsp" class="login-here-link-text">Doctor</a>
                </p>
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
