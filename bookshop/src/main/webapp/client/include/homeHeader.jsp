<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <link href="/resources/Frontend/css/style.css" rel="stylesheet" type="text/css"
          media="all"/>
    <link
            href='//fonts.googleapis.com/css?family=Londrina+Solid|Coda+Caption:800|Open+Sans'
            rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="/resources/Frontend/css/responsiveslides.css">
    <script src="/resources/Frontend/js/jquery.min.js"></script>
    <script src="/resources/Frontend/js/responsiveslides.min.js"></script>

    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="/resources/js/client/accounting.js"></script>

    <script>
        // You can also use "$(window).load(function() {"
        $(function () {

            // Slideshow 1
            $("#slider1").responsiveSlides({
                maxwidth: 1600,
                speed: 600
            });
        });

    </script>
</head>
<body>

<div class="wrap">
    <!----start-Header---->
    <div class="header">
        <div class="search-bar">
            <form action="/bookshop/search">
                <input type="text" name="name">
                <input type="submit" value="Search"/>
            </form>
        </div>
        <div class="clear"></div>

        <div class="header-top-nav ">
            <c:if test="${loggedInUser.username != null}">

                <ul>
                    <li>Xin chào: ${loggedInUser.username}</li>

                        <%--						<li><a href="<%=request.getContextPath()%>/checkout">Thanh toán</a></li>--%>
                    <li><a href="<%=request.getContextPath()%>/bookshop/account">Tài
                        khoản</a></li>
                    <li><a href="<%=request.getContextPath()%>/bookshop/cart"><span>Giỏ
									hàng&nbsp;&nbsp;: </span></a><span
                            class="glyphicon glyphicon-shopping-cart"></span></li>
                    <li><a href="<%=request.getContextPath()%>/bookshop/logout"> Đăng
                        xuất</a></li>
                </ul>
            </c:if>
            <c:if test="${loggedInUser.username == null}">
                <ul>
                    <li><a href="<%=request.getContextPath()%>/bookshop/register">Đăng
                        kí</a></li>
                    <li><a href="<%=request.getContextPath()%>/bookshop/login">Đăng
                        nhập</a></li>
                    <li><a href="<%=request.getContextPath()%>/bookshop/cart">
                        <span>Giỏ hàng&nbsp;&nbsp;&nbsp;</span></a><span
                            class="glyphicon glyphicon-shopping-cart"></span></li>

                </ul>
            </c:if>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="clear"></div>
<div class="top-header">
    <div class="wrap">
        <!----start-logo---->
        <div class="logo">
            <a href="<%=request.getContextPath()%>/"></a>
        </div>
        <!----end-logo---->
        <!----start-top-nav---->
        <div class="top-nav">
            <ul>
                <li><a href="<%=request.getContextPath()%>/">Trang chủ</a></li>

                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Cửa hàng <span
                        class="caret"></span></a>
                    <ul class="dropdown-menu" style="background: #94cb32" id="category_1">
                    </ul>
                </li>
                <li><a href="<%=request.getContextPath()%>/bookshop/shipping">Miễn
                    phí vận chuyển</a></li>
                <li><a href="<%=request.getContextPath()%>/bookshop/guarantee">Bảo
                    hành tận nơi</a></li>

                <li><a href="<%=request.getContextPath()%>/bookshop/contact">Liên hệ</a></li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>
<!----End-top-nav---->
<!----End-Header---->

<script src="<c:url value='/resources/js/client/headerAjax.js'/>"></script>