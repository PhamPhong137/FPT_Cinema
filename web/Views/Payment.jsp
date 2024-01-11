<%-- 
    Document   : Payment
    Created on : Oct 23, 2023, 9:31:41 PM
    Author     : PC-Phong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Cinema</title>
        <link rel="stylesheet" href="Css/stylePayment.css"/>
        <link rel="icon" type="image/x-icon" href="https://th.bing.com/th/id/R.e15d750fe41eb99350f0c56e6b66b653?rik=QJI66s2o67Q7jw&pid=ImgRaw&r=0">
    </head>
    <body>
        <%@include file="/components/header.jsp"%>
        <input id="taikhoan" type="hidden" value="${taiKhoan.account_id}">
        <input id="seatid" type="text" value="${seatId}">
        <div class="container2">
            <div class="left2">
                <div class="infor-film2">
                    <div style="display: flex;justify-content: space-between;align-content: center">
                        <h3 class="title2">Thông tin phim</h3>
                        <p style="border:1px solid red;border-radius: 10px;padding: 10px">Thời gian thanh toán: <span id="time"></span> </p>
                    </div>
                    <div class="infor-sub-part2">
                        <p class="tag2">Phim</p>
                        <p class="content2">${title}</p>
                    </div>

                    <div class="grid-container2">
                        <div class="infor-sub-part2">
                            <p class="tag2">Ngày giờ chiếu</p>
                            <p class="content2"><span class="time2">${time}  </span> <span class="date" style="margin-left: 10px">${date}</span></p>
                        </div>
                        <div class="infor-sub-part2">
                            <p class="tag2">Định dạng</p>
                            <p class="content2">2d</p>
                        </div>
                        <div class="infor-sub-part2">
                            <p class="tag2">Ghế</p>
                            <p class="content2">${seat}</p>
                        </div>
                        <div class="infor-sub-part2">
                            <p class="tag2">Phòng chiếu</p>
                            <p class="content2">${room}</p>
                        </div>
                    </div>
                </div>
                <div class="bill2">
                    <h3 class="title2">Thông tin thanh toán</h3>
                    <div class="table-container2">
                        <table class="table2">
                            <thead>
                                <tr>
                                    <th scope="c">Danh mục</th>
                                    <th scope="c">Số lượng</th>
                                    <th scope="c">Tổng tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="r">
                                        <span class="" >Ghế ${seat}</span>
                                    </td>
                                    <td class="p">
                                        <span class="">${soluong}</span>
                                    </td>
                                    <td class="p">
                                        <span class="">${money}</span><span class="vnd">đ</span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="right2">
                <div class="pay-container2">
                    <h3 class="title2">Phương thức thanh toán</h3>
                    <div class="options2">
                        <div class="option2 option-1">VNPAY</div>
                        <div class="option2">Payoo</div>
                        <div class="option2">Viettel Money</div>
                    </div>
                    <div class="fee2">
                        <h3 class="title2">Chi phí</h3>
                        <div>
                            <span>Thanh toán</span><span class="value2">${money}<span class="vnd">đ</span></span>
                        </div>
                        <div>
                            <span>Free (0%)</span><span class="value2">0<span class="vnd">đ</span></span>
                        </div>
                        <div>
                            <span>Thanh toán</span><span class="value2">${money}<span class="vnd">đ</span></span>
                        </div>
                    </div>
                    <form action="Payment" method="post">
                        <input type="hidden" value="${seat}" name="seat" id="seat">

                        <!--gui du lieu-->
                        <input type="hidden" value="${seatId}" name="seatId">
                        <input type="hidden" value="${ticketfilmid}" name="ticketfilmid" id="ticketfilmid">


                        <div class="button-submit2">
                            <input type="submit" class="submit2" value="FPT Pay"></input>
                        </div>
                        <div class="button-submit23 ">
                            <a style="color: white" href="Home">Quay lại</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>   
        <%@include file="/components/footer.jsp"%>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="Javascript/payment.js">
        </script>
    </body>
</html>
