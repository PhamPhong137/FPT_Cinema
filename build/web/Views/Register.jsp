<%-- 
    Document   : Register
    Created on : Oct 17, 2023, 12:42:54 AM
    Author     : PC-Phong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Cinema</title>
        <link rel="stylesheet" href="Css/styleRegister.css"/>
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="icon" type="image/x-icon" href="https://th.bing.com/th/id/R.e15d750fe41eb99350f0c56e6b66b653?rik=QJI66s2o67Q7jw&pid=ImgRaw&r=0">
    </head>
    <body>
        <div class="form">
            <div class="form-header">
                <a href="/Project_PRJ301/Home"><span class="material-symbols-outlined icon">
                        Home   
                    </span></a>
            </div>
            <h2>Đăng ký</h2>
            <form action="Register" method="post">
                <div class="form-row">
                    <div class="form-group">
                        <label>Họ</label>
                        <input type="text" name="first_name" placeholder="Họ" required>
                    </div>

                    <div class="form-group">
                        <label>Tên</label>
                        <input type="text" name="last_name" placeholder="Tên"required>
                    </div>
                </div>

                <div class="form-group">
                    <label>Tên tài khoản</label>
                    <input class="username" type="text" name="username"placeholder="Tên tài khoản" required  >
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phone_number" placeholder="Số điện thoại" required>
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" placeholder="Email" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input id="pass" type="password" name="pass" placeholder="Mật khẩu" required>
                    </div>

                    <div class="form-group">
                        <label>Xác nhận mật khẩu</label>
                        <input id="re_pass" type="password" name="re_pass" placeholder="Xác nhận mật khẩu" required onkeyup="validatePass()" >
                    </div>
                </div>

                <button id="submit_btn" type="submit">Đăng ký</button>

                <p>Bạn đã có tài khoản? <a href="/Project_PRJ301/Login">Đăng nhập</a></p>
            </form>
        </div>

        <script>
            function validatePass() {
                var pass = document.getElementById("pass");
                var re_pass = document.getElementById("re_pass");
                var submitBtn = document.getElementById("submit_btn");
                if (pass.value !== re_pass.value) {
                    submitBtn.disabled = true;
                    re_pass.style.border = "1px solid red";
                } else {
                    submitBtn.disabled = false;
                    re_pass.style.border = "1px solid green";
                }
            }
        </script>

    </body>
</html>
