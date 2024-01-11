<%-- 
    Document   : movies.jsp
    Created on : Oct 21, 2023, 1:53:09 PM
    Author     : PC-Phong
--%>
<%@page import="DAL.*"%>
<%@page import="Models.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Cinema</title>
        <link rel="stylesheet" href="Css/styleMovies.css"/>
        <link rel="icon" type="image/x-icon" href="https://th.bing.com/th/id/R.e15d750fe41eb99350f0c56e6b66b653?rik=QJI66s2o67Q7jw&pid=ImgRaw&r=0">
    </head>
    <body>
        <%@include file="/components/header.jsp"%>
        ${filmdetail}
        <div class="img"></div>
        <input id="taikhoan" type="hidden" value="${taiKhoan.account_id}">

        <c:forEach items="${film_detail}" var="filmdetail">
            <c:set var="filmId" value="${filmdetail.id}"  />
            <input id="filmId" type="hidden" value="${filmdetail.id}" name="filmid">

            <div class="movie-card">
                <div class="movie-image">
                    <img src="/Project_PRJ301/img/${filmdetail.imageUrl}" width="300px"
                         alt="${filmdetail.title}">
                </div>
                <div class="movie-info">
                    <div style="width: 75%;">
                        <div class="movie-title">
                            <h2>${filmdetail.title}</h2>
                            <c:set var="filmTitle" value="${filmdetail.title}"  />
                            <div class="movie-format">2D</div>
                        </div>
                        <div class="movie-details">
                            <p>${filmdetail.category}</p>
                            <p>${filmdetail.origin}</p>
                            <p>${filmdetail.length} phút</p>
                            <p>Đạo diễn: ${filmdetail.director}</p>
                        </div>
                        <div class="movie-cast">
                            <p>Diễn viên: ${filmdetail.actors}
                            </p>
                        </div>
                        <div class="movie-release">
                            <p>Khởi chiếu: ${filmdetail.publishTime}</p>
                        </div>
                        <div class="movie-description">
                            <p>${filmdetail.description}</p>
                        </div>
                        <div class="movie-warning">
                            <p>${filmdetail.warningText}</p>
                        </div>
                        <div class="movie-actions">
                            <button class="details-button">Chi tiết nội dung</button>
                            <button class="trailer-button">Xem trailer</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="date">
            <c:forEach items="${eventDao.dateFilm}" var="df" >
                <p class="detal-date">${df.date}</p>
                <c:set var="date" value="${df.date}"  />
            </c:forEach>
        </div>
        <div class="time">
            <c:forEach items="${eventDao.eventFilm}" var="ef" >
                <p><a class="detal-time" href="/Project_PRJ301/Films?movie=${filmId}&time=${ef.startHour}">${ef.startHour}</a></p>
                </c:forEach>
        </div>

        <div class="seat">      
            <div style="display: flex; justify-content: space-evenly">
                <h2>Giờ chiếu: ${startHour}  </h2>
                <c:if test="${startHour!=null}">
                    <h3 style="border:1px solid red;border-radius: 10px;padding: 10px ">Thời gian chọn ghế: <span id="time"></span> </h3>
                </c:if>
            </div>
            <img src="https://chieuphimquocgia.com.vn/_next/image?url=%2Fimages%2Fscreen.png&w=1920&q=75" alt="alt"/>
            <h2 >Phòng chiếu ${loadSeat[0].room_id.name}</h2>

            <table>
                <c:forEach items="${loadSeat}" var="c" varStatus="status">
                    <c:if test="${status.index % 10 == 0}">
                        <tr> 
                        </c:if>
                        <c:if test="${c.price==150000&&!listSeatsFilm_booked.contains(c.id)&&!listSeatsFilm_booking.contains(c.id)}">
                            <td class="orange" onclick="totalBill(this)">
                                ${c.seat_number}
                                <input type="hidden" value="${c.price}" class="seat-value"> 
                                <input type="hidden" value="${c.id}" class="seatID">                                                       
                            </td>
                        </c:if>
                        <c:if test="${c.price==100000&&!listSeatsFilm_booked.contains(c.id)&&!listSeatsFilm_booking.contains(c.id)}">
                            <td class="gray" onclick="totalBill(this)">
                                ${c.seat_number}
                                <input type="hidden" value="${c.price}" class="seat-value">
                                <input type="hidden" value="${c.id}" class="seatID">                                                       

                            </td>
                        </c:if>

                        <c:if test="${listSeatsFilm_booked.contains(c.id)}">                            
                            <td style="background-color: red">
                                ${c.seat_number}                         
                            </td>
                        </c:if>
                        <c:if test="${listSeatsFilm_booking.contains(c.id)}">                            
                            <td style="background-color: #2384d1">
                                ${c.seat_number}                         
                            </td>
                        </c:if>

                        <c:if test="${status.index % 10 == 9 || status.index == (loadSeat.size() - 1)}">
                        </tr> 
                    </c:if>
                </c:forEach>
            </table>
            <div class="seat-type">
                <div class="seat1">
                    <div class="box" style="background-color: red"></div>
                    <p>Ghế đã đặt</p>
                </div>
                <div class="seat1">
                    <div class="box" style="background-color: #393991"></div>
                    <p>Ghế bạn chọn</p>
                </div>
                <div class="seat1">
                    <div class="box" style="background-color: #2384d1"></div>
                    <p>Ghế giữ chỗ</p>
                </div>
                <div class="seat1">
                    <div class="box" style="background-color: gray"></div>
                    <p>Ghế thường</p>
                </div>
                <div class="seat1" >
                    <div class="box"style="background-color: orange"></div>
                    <p>Ghế VIP</p>
                </div>
            </div>
            <form action="Payment" method="get">
                <div class="payment">
                    <div style="text-align: left;">
                        <p>Ghế đã chọn: <span id="selectedSeatsDisplay" name="seat" ></span></p>

                        <p>Tổng tiền: <span id="totalPrice" value="money"></span></p>

                    </div>
                    <div class="booking">
                        <span><a style="text-decoration: none;color: white" href="Home">Quay lại</a></span>
                        <input class="submitfilm" type="submit" value="Thanh toán" onclick="return checkSeatsBeforeSubmit();">

                    </div>

                </div>
                <input id="seat" type="hidden" value="" name="seat">
                <input id="money" type="hidden" value="" name="money">


                <input type="hidden" value="${ticketfilm.id}" name="ticketfilmid" id="ticketfilmid">
                <input type="hidden" value="${filmTitle}" name="titlefilm">
                <input type="hidden" value="${startHour}" name="time">
                <input type="hidden" value="${date}" name="date">
                <input type="hidden" value="${loadSeat[0].room_id.name}" name="room">
                
                

                <!--truyen du lieu seatId-->
                <input type="hidden" id="seatIdd" value="" name="seatIdd">


            </form>
        </div>
        <%@include file="/components/footer.jsp"%>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="Javascript/home.js">
        </script>
    </body>
</html>
