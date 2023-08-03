<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value='/resources/js/client/homeAJax.js'/>"></script>

<!--start-image-slider---->
<div class="wrap">
    <div class="image-slider">
        <!-- Slideshow 1 -->
        <ul class="rslides" id="slider1">
            <li><img src="/resources/Frontend/images/anh1.png" alt=""></li>
            <li><img src="/resources/Frontend/images/anh2.png" alt=""></li>
            <li><img src="/resources/Frontend/images/anh3.png" alt=""></li>
            <li><img src="/resources/Frontend/images/anh4.jpg" alt=""></li>
            <li><img src="/resources/Frontend/images/anh5.jpg" alt=""></li>
        </ul>
        <!-- Slideshow 2 -->
    </div>
    <!--End-image-slider---->
</div>
<div class="clear"></div>
<div class="wrap">
    <div class="content">
        <div class="top-3-grids">
            <div class="section group">
                <div class="grid_1_of_3 images_1_of_3">
                    <a href="<%=request.getContextPath()%>/product?id=35"><img src="/resources/Frontend/images/noibat1.jpg"></a>
                    <h3>Sách nổi bật </h3>
                </div>
                <div class="grid_1_of_3 images_1_of_3 ">
                    <a href="<%=request.getContextPath()%>/product?id=37"><img src="/resources/Frontend/images/noibat2.jpg" style="background-color: white"></a>
                    <h3>Sách nổi bật</h3>
                </div>
                <div class="grid_1_of_3 images_1_of_3 ">
                    <a href="<%=request.getContextPath()%>/product?id=36"><img src="/resources/Frontend/images/noibat3.jpg" style="background-color: white"></a>
                    <h3>Sách nổi bật</h3>
                </div>
            </div>
        </div>

        <div class="content-grids">
            <h4>DANH SÁCH BOOK MỚI NHẤT</h4>

        </div>
    </div>
    <div class="content-sidebar">
        <h4>Danh mục</h4>
        <ul id="category">

        </ul>
    </div>
</div>
<div class="clear"></div>
</div>

