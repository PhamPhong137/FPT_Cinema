<%-- 
    Document   : Login
    Created on : Oct 17, 2023, 12:42:13 AM
    Author     : PC-Phong
--%>

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
            <h2>Mật Khẩu mới</h2>          
            <form action="NewPassword" method="post" onsubmit="return validatePasswords()">  
                <div class="form-group">
                    <label for="newPassword">Mật khẩu mới</label>
                    <input type="password" id="newPassword" name="newPassword" placeholder="Mật khẩu mới" required>
                    <span class="" onclick="togglePasswordVisibility('newPassword')">
                        visibility_off
                    </span>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Nhập lại mật khẩu</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Nhập lại mật khẩu" required>
                    <span class="" onclick="togglePasswordVisibility('confirmPassword')">
                        visibility_off
                    </span>
                </div>
                <input type="hidden" name="username" value="${usernameo}">
                <input type="hidden" name="phonenumber" value="${phonenumbero}">
                <button type="submit">Thiết lập Mật Khẩu Mới</button>

                <p>Bạn chưa có tài khoản?  <a href="/Project_PRJ301/Register"><span class="forget">Đăng ký</span></p></a>

            </form>
        </div>
        <script>

            function validatePasswords() {
                var newPassword = document.getElementById("newPassword").value;
                var confirmPassword = document.getElementById("confirmPassword").value;
                if (newPassword !== confirmPassword) {
                    alert("Mật khẩu mới và mật khẩu xác nhận không khớp!");
                    return false; // Prevent form submission
                }
                return true; // Allow form submission
            }

        </script>
    </body>
</html>
