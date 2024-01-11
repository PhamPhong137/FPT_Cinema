<%-- 
    Document   : footer
    Created on : Oct 17, 2023, 11:56:04 PM
    Author     : PC-Phong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Footer</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
        <style>
            body {
                margin: 0px;
                box-sizing: border-box;
            }

            .footer {
                background-color: #273039;
                color: white;
                padding: 40px 0;
            }

            .footer .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 0 15px;
                display: grid;
                grid-template-columns: 1fr 1fr 1fr 1fr;
                gap: 40px;
            }

            .footer h4 {
                margin-bottom: 25px;
                border-bottom: 2px solid #e74c3c;
                display: inline-block;
            }

            .footer p,
            .footer a {
                margin-bottom: 10px;
                display: block;
            }

            .footer ul {
                padding: 0;
                list-style-type: none;
            }

            .footer a {
                color: #bdc3c7;
                text-decoration: none;
                transition: color 0.3s;
            }

            .footer a:hover {
                color: #e74c3c;
            }

            .footer i {
                font-size: 20px;
                margin-right: 10px;
            }

            .footer .social-links ul {
                display: flex;
                gap: 15px;
            }

            .footer .map {
                width: 100%;
                height: 200px;
                background-color: #34495e;
                margin-top: 20px;
                position: relative;
            }

            .footer .map iframe {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                border: none;
            }

        </style>
    </head>
    <body>
        <footer class="footer">
            <div class="container">

                <div class="useful-links">
                    <h4>FPT Cinema</h4>
                    <a href="#">Mua vé online</a>
                    <a href="#">Tin tức & Khuyến mãi</a>
                    <a href="#">Thành viên thân thiết</a>
                    <a href="#">Liên hệ<</a>
                </div>
                <div class="contact">
                    <h4>Contact Us</h4>
                    <p><i class="fas fa-envelope"></i> phongphhe176151@fpt.edu.vn</p>
                    <p><i class="fas fa-phone"></i> 0987563385</p>
                    <p><i class="fas fa-map-marker-alt"></i> FPT Cinema</p>
                </div>
                <div class="social-links">
                    <h4>Follow Us</h4>
                    <ul>
                        <li><a href="#" title="Twitter"><i class="fab fa-twitter"></i></a></li>
                        <li><a href="#" title="Facebook"><i class="fab fa-facebook"></i></a></li>
                        <li><a href="#" title="Instagram"><i class="fab fa-instagram"></i></a></li>
                        <li><a href="#" title="Pinterest"><i class="fab fa-pinterest"></i></a></li>
                    </ul>
                </div>
                <div class="map">
                    <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3151.8354776825854!2d144.95373631531693!3d-37.81720927975195!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x6ad642af0f11fd81%3A0x5045675218ceee0!2sMelbourne%20VIC%2C%20Australia!5e0!3m2!1sen!2sus!4v1626525759053!5m2!1sen!2sus"
                        allowfullscreen="" loading="lazy"></iframe>
                </div>
            </div>
        </footer>
    </body>
</html>
