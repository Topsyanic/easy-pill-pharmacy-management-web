<%-- 
    Document   : customerSignUp
    Created on : Jul 24, 2021, 11:14:26 AM
    Author     : Raaid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up Page</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="FONTS/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="CSS/footer.css">
        <link rel="stylesheet" href="CSS/signUp.css">
    </head>
    <body>
        <h1 class="title-h1" >EASY<span class='alt-color'>PILL</span></h1>
        <input name='command' value='ADD' hidden>
        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <form action="RegisterServlet" method="POST" class="signup-form" >
                            <input hidden="" name="command" value="customer">
                            <h2 class="form-title">Create account</h2>
                            <div class="form-group">
                                <p style="text-align: center; color: red;">${ErrorMessage}</p>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="text" class="form-input" name="firstName" required id="name" placeholder="First Name" value="${firstName}"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-input" name="lastName" required id="name" placeholder="Last Name" value="${lastName}"/>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-input" name="email" required id="email" placeholder="Your Email" value="${email}"/>
                                </div>
                                <div class="form-group">
                                    <input type="tel" class="form-input" name="contact" required id="name" placeholder="Contact Number Ex: 0712345689" value="${contact}" pattern="[0]{1}[7,1]{1}[0-9]{8}"
                                           maxlength = "10"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-input" name="address" required id="name" placeholder="Address" value="${address}"/>
                                </div>
                                <div class="form-group">
                                    <input type="password"  class="form-input" name="password" required id="password" placeholder="Password" onkeyup="checkPass();" value="${password}"/>
                                    <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-input" name="password0" required id="password0" placeholder="Repeat your password" onkeyup="checkPass();" value="${password}"/>
                                    <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                                    <span id="confirm-message2" class="confirm-message" ></span>
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up"  onclick="return validate()"/>
                                </div>
                                <p class="loginhere">
                                    Already have an account ? <a class="login-here-link-text" href="login.jsp">Login here</a>
                                </p>
                            </div>
                        </form>

                    </div>
            </section>
            <img class="reading-svg" src="IMG/happy1.svg" />
            <img class="watching-svg" src="IMG/happy3.svg" />
            <img class="reading2-svg" src="IMG/happy2.svg" />
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
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
            <script type="text/javascript">

                function validate()
                {
                    var password = document.getElementById('password').value;
                    var confirmPassword = document.getElementById("password0").value;

                    if (password != confirmPassword)
                    {
                        alert("Passwords entered do not match!");
                        return false;
                    }

                }


                function checkPass()
                {
                    var password = document.getElementById('password');
                    var confirm = document.getElementById('password0');
                    var message = document.getElementById('confirm-message2');

                    var good_color = "#5A2E98";
                    var bad_color = "#ff0000";

                    if (password.value == confirm.value)
                    {
                        message.style.color = good_color;
                        message.innerHTML = "Passwords match!";
                    } else
                    {
                        message.style.color = bad_color;
                        message.innerHTML = "Passwords do not match!";
                    }

                }

                function validation()
                {
                    var form = document.getElementById("form");
                    var email = document.getElementById("email").value;
                    var text = document.getElementById("text");

                    var pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
                    if (email.match(pattern))
                    {
                        form.classList.add("Valid");
                        form.classList.remove("Invalid");
                        text.innerHTML = "Your Email Address is Valid";
                        text.style.color = "#00ff00";
                    } else
                    {
                        form.classList.remove("valid");
                        form.classList.add("Invalid");
                        text.innerHTML = "Please Enter Valid Email Address";
                        text.style.color = "#ff0000";
                    }
                    if (email == "")
                    {
                        form.classList.remove("Valid");
                        form.classList.remove("Invalid");
                        text.innerHTML = "";
                        text.style.color = "#00ff00";
                    }
                }
            </script>
            <script src="VENDOR/jquery/jquery.min.js"></script>
            <script src="JS/signUp.js"></script>
            <script src="JS/app.js"></script>
    </body>
</html>