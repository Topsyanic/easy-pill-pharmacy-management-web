<%-- 
    Document   : adminAddMedicine
    Created on : Nov 29, 2021, 3:25:11 PM
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
        <title>Add Medicine</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="CSS/footer.css">
        <link rel="stylesheet" href="CSS/updateMedicineImage.css">
    </head>
    <c:url var="supplierLink" value="SupplierController">
        <c:param name="command" value="SUPPLIER"/>
    </c:url>
    <body>
        <h1 class="title-h1" >EASY<span class='alt-color'>PILL</span></h1>

        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <form action="MedicineController" method="POST" class="signup-form" enctype="multipart/form-data" >
                            <input name='command' value='CONFIRMADDMEDICINE' hidden>
                            <div class="form-group">
                                <div class="update-pic-div">
                                    <img src='${imagePath}' id="previewPhoto">
                                    <input type="file" id="file" name="image" size="50" required="" />
                                    <label for="file" id="uploadBtn">Choose Photo</label>
                                </div> 
                                <p style="text-align: center; color: red;">${errorMessage}</p>
                                <p style="text-align: center;">Please upload the image of the medicine.</p>
                            </div>
                            <div class="form-group"> 
                                <label class="metric-text"  for="supplier"><p style="text-align:center">Select Supplier</p> <select  name="supplier" class="dropdown">
                                        <c:forEach var="sup" items="${supplierList}">
                                            <option  value="${sup.supplierId}">${sup.name}(${sup.supplierId})</option>
                                        </c:forEach>
                                    </select></label>

                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="text" class="form-input" name="name" required id="name" placeholder="Medicine Name" value="${name}"/>
                                </div>
                                <div class="form-group">
                                    <textarea type="text" class="desc-message2" name="description" required id="name" placeholder="Description">${description}</textarea> 
                                </div>
                                <div class="form-group">
                                    <input type="number" class="form-input" name="quantity" required id="name" value="${quantity}" placeholder="Quantity" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                                           maxlength = "3"/>
                                </div>
                                <div class="form-group">
                                    <label class="metric-text"  for="metric"><p>Select weight metric</p></label>
                                    <input type="radio" name="metric" value="litres">
                                    <label class="metric-text" for="litres"> litres</label>
                                    <input type="radio"  name="metric" value="millilitres">
                                    <label class="metric-text" for="millilitres">millilitres</label>  
                                    <input type="radio"  name="metric" value="grams">
                                    <label class="metric-text" for="grams">grams</label>  
                                    <input type="radio"  name="metric" value="kilograms">
                                    <label class="metric-text" for="kilograms">kilograms</label>  

                                </div>
                                <div class="form-group">
                                    <input type="number" class="form-input" name="weight" required id="name" placeholder="Weight" value="${weight}" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                                           maxlength = "4"/>
                                </div>
                                <div class="form-group">
                                    <input type="number" class="form-input" name="price" required id="name" placeholder="Price In Rupees"  value="${price}" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                                           maxlength = "5"/>
                                </div>

                                <div class="form-group">
                                    <input type="submit" name="submit" id="submit" class="form-submit" value="Add "/>
                                    <input type="button" name="submit" id="submit" class="form-submit" value="cancel " onclick="location.href = '${supplierLink}'"/>
                                </div>
                            </div>
                        </form>
                        </section>
                        <img class="reading-svg" src="IMG/happy1.svg" />
                        <img class="watching-svg" src="IMG/happy3.svg" />
                        <img class="reading2-svg" src="IMG/happy2.svg" />
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
                        <script src="VENDOR/jquery/jquery.min.js"></script>
                        <script src="JS/signUp.js"></script>
                        <script src="JS/app.js"></script>
                        </body>
                        </html>