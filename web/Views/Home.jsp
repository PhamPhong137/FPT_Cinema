<%-- 
    Document   : Home
    Created on : Oct 17, 2023, 12:43:09 AM
    Author     : PC-Phong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Cinema</title>
        <link rel="stylesheet" href="Css/styleHome.css"/>
        <link rel="icon" type="image/x-icon" href="https://th.bing.com/th/id/R.e15d750fe41eb99350f0c56e6b66b653?rik=QJI66s2o67Q7jw&pid=ImgRaw&r=0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="/components/header.jsp"%>
        <div class="film">
            <div id="carouselExampleIndicators" class="carousel slide " data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner " style="height: 100vh">
                    <% int count=1; %>
                    <c:forEach items="${film.accFilm}" var="film"> 
                        <c:if test="${film.image_backgroundUrl!=null}">
                            <% if(count==1){ %>
                            <div class="carousel-item active">
                                <img class="d-block w-100 " src="http://localhost:9999/Project_PRJ301/img/${film.image_backgroundUrl}" alt="">
                            </div>                       
                            <%}else{%>
                            <div class="carousel-item ">
                                <img class="d-block w-100 " src="http://localhost:9999/Project_PRJ301/img/${film.image_backgroundUrl}" alt="">
                            </div>
                            <%}%>
                            <% count++; %>
                        </c:if>
                    </c:forEach>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <div style="height: 70px"></div>
            <!--List-Films-->
            <div class="list-film">
                <!--phim dang chieu-->
                <div class="detail-film">
                    <div class="phim-chieu">
                        <div class="dot"></div> 
                        <h3 style="color:white">Phim đang chiếu</h3>
                    </div>
                    <table class="film-showing">              
                        <c:set var="count" value="0"/>
                        <c:forEach items="${film.accFilm}" var="film">
                            <c:if test="${film.status == 0}">
                                <c:choose>
                                    <c:when test="${count % 4 == 0}">
                                        <tr>
                                        </c:when>
                                    </c:choose>
                                    <td>
                                        <a href="/Project_PRJ301/Films?movie=${film.id}" style="text-decoration: none">
                                            <div class="img-wrapper">
                                                <img src="/Project_PRJ301/img/${film.imageUrl}" alt="${film.title}"/>
                                            </div>
                                            <div class="film-desc">
                                                <p>${film.category}</p>
                                                <p>${film.publishTime}</p>
                                            </div>
                                            <p class="title-film">${film.title}</p>
                                        </a>
                                    </td>
                                    <c:choose>
                                        <c:when test="${count % 4 == 3}">
                                        </tr>
                                    </c:when>
                                </c:choose>
                                <c:set var="count" value="${count + 1}"/>
                            </c:if>
                        </c:forEach>  
                        <c:if test="${count % 4 != 0}">
                            <c:forEach begin="1" end="${4 - (count % 4)}">
                                <td></td>
                            </c:forEach>
                            </tr>
                        </c:if>
                    </table>
                    <div class="phim-chieu">
                        <div class="dot"></div> 
                        <h3 style="color:white">Phim sắp chiếu</h3>
                    </div>
                    <table class="film-showing">              
                        <c:set var="count" value="0"/>
                        <c:forEach items="${film.accFilm}" var="film">
                            <c:if test="${film.status == 1}">
                                <c:choose>
                                    <c:when test="${count % 4 == 0}">
                                        <tr>
                                        </c:when>
                                    </c:choose>
                                    <td>
                                        <a href="/Project_PRJ301/Films?movie=${film.id}" style="text-decoration: none">
                                            <div class="img-wrapper">
                                                <img src="/Project_PRJ301/img/${film.imageUrl}" alt="${film.title}"/>
                                            </div>
                                            <div class="film-desc">
                                                <p>${film.category}</p>
                                                <p>${film.publishTime}</p>
                                            </div>
                                            <p class="title-film">${film.title}</p>
                                        </a>
                                    </td>
                                    <c:choose>
                                        <c:when test="${count % 4 == 3}">
                                        </tr>
                                    </c:when>
                                </c:choose>
                                <c:set var="count" value="${count + 1}"/>
                            </c:if>
                        </c:forEach>  
                        <c:if test="${count % 4 != 0}">
                            <c:forEach begin="1" end="${4 - (count % 4)}">
                                <td></td>
                            </c:forEach>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="ex-info">

                </div>
            </div>
        </div>

        <%@include file="/components/footer.jsp"%>
    </body>
</html>
