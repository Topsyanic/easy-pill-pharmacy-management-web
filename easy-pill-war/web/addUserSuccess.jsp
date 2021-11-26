<%-- 
    Document   : addUserSuccess
    Created on : Nov 26, 2021, 8:32:38 PM
    Author     : Topsy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>User Added Successfully</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="CSS/signUp.css">
        <link rel="stylesheet" href="CSS/footer.css">
    </head>
    <body>
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
                            <h2 class="form-title"> User Added successfully</h2>
                            <p class="detail-box">New user has been added. The credentials provided can be used to login.</p>
                                <br>
                                <br>
                                <div class="form-group">
                                    <input type="submit" name="submit" id="submit" class="form-submit" value="Ok " onclick="location.href='UserController'"/>
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
                            <li><a href="#">affiliate program</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>get help</h4>
                        <ul>
                            <li><a href="#">FAQ</a></li>
                            <li><a href="#">Pricing</a></li>
                            <li><a href="#">returns</a></li>
                            <li><a href="#">order status</a></li>
                            <li><a href="#">payment options</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>Local Libraries</h4>
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
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/signUp.js"></script>
    </body>
</html>