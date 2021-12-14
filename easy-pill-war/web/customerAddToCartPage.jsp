<%-- 
    Document   : customerAddToCartPage
    Created on : Dec 13, 2021, 4:20:19 PM
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
        <title>Add To Cart</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="CSS/updateMedicineImage.css">
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
        <img class="reading3-svg" src="IMG/happy1.svg" />
        <img class="watching-svg" src="IMG/happy2.svg" />
        <div class="main">
            <section class="signup">           
                <div class="container">
                    <div class="signup-content">
                        <form action="CartController" method="POST">
                            <input hidden="" name="command" value="ADD">
                            <input hidden="" name="medicineId" value='<%=request.getParameter("medicineId")%>'>
                            <h2 class="form-title"> Adding to cart</h2>
                            <h3 ><%=request.getParameter("name")%> - Rs.<%=request.getParameter("price")%></h3>
                            <img class="product-image" src='<%=request.getParameter("imagePath")%>'/>
                            <p class="prescription-desc">Description </p>
                            <p><%=request.getParameter("description")%></p>
                            <br>
                            <p class="prescription-desc">Weight</p>
                            <p><%=request.getParameter("weight")%></p>
                            <br>
                            <p class="prescription-desc">Requires Prescription</p>
                            <p><%=request.getParameter("requirePres")%></p>
                            <br>
                            <p class="prescription-desc">Stock Available</p>
                            <p><%=request.getParameter("quantity")%></p>
                            <br>
                            <p class="prescription-desc" for="quantity">Select Quantity</p>
                            <br>
                            <div class="quantity buttons_added">
                                <input type="button" value="-" class="minus"><input type="number" step="1" min="1" max="<%=request.getParameter("quantity")%>" name="quantity" value="1" title="Qty" class="input-text qty text" size="4" pattern="" inputmode=""><input type="button" value="+" class="plus">
                            </div>
                            <div class="form-group">
                                <input type="button" name="submit" id="submit" class="form-submit" value="Back" onclick="location.href = '${customerLink}'"/>
                                <input type="submit" name="submit" id="submit" class="form-submit" value="Add"/>
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
        <script src="JS/addToCart.js"></script>
    </body>
</html>