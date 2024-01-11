<%-- 
    Document   : header
    Created on : Oct 17, 2023, 11:55:58 PM
    Author     : PC-Phong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Css/styleHeader.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    </head>
    <body>
        <div class="navbar1">
            <div class="container1">
                <div class="logo1">
                    <a href="/Project_PRJ301/Home">
                        <img src="img/FPT.svg.png" alt="logoFPT">
                    </a>
                </div>
                <nav class="main-nav1">
                    <a style="color: red;" href="/Project_PRJ301/Home">Trang chủ</a>
                    <a href="#">Lịch chiếu</a>
                    <a href="News">Tin tức</a>
                    <a href="#">Giá vé</a>
                    <a href="#">Liên hoan phim</a>
                    <a href="#">Giới thiệu</a>
                </nav>
                <c:if test="${sessionScope.taiKhoan == null}">
                    <div class="auth-btn1">
                        <a href="/Project_PRJ301/Register"><button class="register1">Đăng ký</button></a>
                        <a href="/Project_PRJ301/Login"><button class="login1">Đăng nhập</button></a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.taiKhoan != null}">
                    <div class="auth-btn1">
                        <div class="dropdown1">
                            <div class="dropdown-title1">
                                <span style="margin-right:10px" class="material-symbols-outlined">
                                    person
                                </span>
                                <div class="username1">${sessionScope.taiKhoan.first_name}
                                    ${sessionScope.taiKhoan.last_name}</div>
                            </div>
                            <div class="dropdown-content1">
                                <a href="Profile">Thông tin cá nhân</a>
                                <a href="Logout">Đăng xuất</a>
                                <a href="History"> Lịch sử mua vé</a>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
