<%-- 
    Document   : Login
    Created on : Oct 17, 2023, 12:42:13 AM
    Author     : PC-Phong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Cinema</title>
        <link rel="stylesheet" href="Css/styleLogin.css"/>
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="icon" type="image/x-icon" href="https://th.bing.com/th/id/R.e15d750fe41eb99350f0c56e6b66b653?rik=QJI66s2o67Q7jw&pid=ImgRaw&r=0">
    </head>
    <body>
        <div class="login-form">
            <div class="form-header">
                <a href="/Project_PRJ301/Home"><span class="material-symbols-outlined icon">
                        Home   
                    </span></a>
            </div>
            <h2>Lấy lại mật khẩu </h2>
            <h5 style="color: red">${errorup}</h5>
            <form action="ForgetPass" method="post"> 
                <c:if test="${OTP==null}">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text"  name="username" placeholder="User name" required> 
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone number</label>
                        <input type="text"  name="phone" placeholder="Phone number" required> 
                    </div>
                    <button type="submit">Lấy mã OTP</button
                </c:if>
            </form>
            <form action="NewPassword" method="get" onsubmit="return checkOTP()">
                <c:if test="${OTP!=null}">
                    <h5 style="color: red"> Vui lòng nhập mã OTP: ${OTP}</h5>
                    <div class="form-group">
                        <label for="OTP">OTP</label>
                        <input type="text" id="userOTP" name="OTP" placeholder="Mã OTP" required> 
                    </div>
                    <input type="hidden" name="usernameo" value="${usernameo}">
                    <input type="hidden" name="phonenumbero" value="${phonenumbero}">
                    <button id="OTP" type="submit">Submit</button
                </c:if>
         

            </form>


            <p>Bạn chưa có tài khoản?  <a href="/Project_PRJ301/Register"><span class="forget">Đăng ký</span></p></a>

    </div>
    <script>
        function checkOTP() {
            var correctOTP = '${OTP}'; // Get the correct OTP
            var userOTP = document.getElementById('userOTP').value; // Get user entered OTP

            if (userOTP !== correctOTP) {
                alert('OTP không chính xác. Vui lòng nhập lại.');
                return false; // This will prevent the form from submitting
            }
            return true;
        }

    </script>
</body>
</html>
