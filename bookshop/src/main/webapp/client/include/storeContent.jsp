<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link rel="stylesheet" href="/resources/Frontend/css/searchResult.css">
    <script src="/resources/Frontend/js/jquery.min.js"></script>
    <script src="/resources/Frontend/js/responsiveslides.min.js"></script>
    <script src="/resources/js/client/accounting.js"></script>
    <script src="<c:url value='/resources/js/client/addToCart.js'/>" ></script>
</head>

<script type="text/javascript">
    $(document).ready(function () {
        $(".grid_1_of_4 .changeToVND").each(function () {
            var value = accounting.formatMoney($(this).text()) + ' VND';
            $(this).html(value);
        });


    });
</script>


<body>
<div class="clear"> </div>
<div class="wrap">
    <div class="content">
        <div class="content-grids">

            <c:if test = "${list.size() == 0}">
                <div class="section group">
                    <h4>Không tìm thấy kết quả nào</h4>
                </div>
            </c:if>
            <c:forEach var="product" items="${list}" varStatus="loop">
            <c:if test = "${loop.index != list.size()-1}">
                <c:if test = "${loop.index%4 == 0}">
                    <div class="section group">
                    <div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
                        <img style="width: 225px; height: 230px" src="/resources/images/${product.id}.png">
                        <h3 style="font-weight: bold; height: 43.2px">${product.nameProduct}</h3></a>
                        <h3 class="changeToVND">${product.price}</h3>
                        <button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
                        <h3></h3>
                    </div>
                </c:if>

                <c:if test = "${loop.index%4 != 0}">
                    <c:if test = "${loop.index%4 == 3}">
                        <div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
                            <img style="width: 225px; height: 230px"src="/resources/images/${product.id}.png">
                            <h3 style="font-weight: bold;height: 44px">${product.nameProduct}</h3></a>
                            <h3 class="changeToVND">${product.price}</h3>
                            <button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
                            <h3></h3>
                        </div>
                        </div>
                    </c:if>

                    <c:if test = "${loop.index%4 != 3}">
                        <div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
                            <img style="width: 225px; height: 230px" src="/resources/images/${product.id}.png">
                            <h3 style="font-weight: bold;height: 44px">${product.nameProduct}</h3></a>
                            <h3 class="changeToVND">${product.price}</h3>
                            <button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
                            <h3></h3>
                        </div>
                    </c:if>
                </c:if>
            </c:if>
            <c:if test = "${loop.index == list.size()-1}">
            <c:if test = "${loop.index%4 == 0}">
                <div class="section group">
                    <div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
                        <img style="width: 225px; height: 230px" src="/resources/images/${product.id}.png">
                        <h3 style="font-weight: bold;height: 44px">${product.nameProduct}</h3></a>
                        <h3 class="changeToVND">${product.price}</h3>
                        <button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
                        <h3></h3>
                    </div>
                </div>
            </c:if>
            <c:if test = "${loop.index%4 != 0}">
            <div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
                <img style="width: 225px; height: 230px" src="/resources/images/${product.id}.png">
                <h3 style="font-weight: bold;height: 44px">${product.nameProduct}</h3></a>
                <h3 class="changeToVND">${product.price}</h3>
                <button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"> </span>Giỏ hàng</button>
                <h3></h3>
            </div>
        </div>
        </c:if>
        </c:if>
        </c:forEach>
        <c:if test = "${list.size() != 0}">
            <div class="paging">
                <c:if test = "${currentPage != 1}">
                    <a href="/bookshop/store?page=${currentPage-1}&price=${price}&category=${category.nameCategory}&nxb=${nxb}">Back</a>
                </c:if>
                <c:if test = "${currentPage == 1}">
                    <a class="current">1</a>
                </c:if>
                <c:if test = "${currentPage != 1}">
                    <a href="/bookshop/store?page=1&price=${price}&category=${category.nameCategory}&nxb=${nxb}">1</a>
                </c:if>

                <c:forEach var="pag" items="${pageList}" varStatus="loop">
                    <c:if test = "${currentPage == pag}">
                        <a class="current">${pag}</a>
                    </c:if>
                    <c:if test = "${currentPage != pag}">
                        <a href="/bookshop/store?page=${pag}&price=${price}&category=${category.nameCategory}&nxb=${nxb}">${pag}</a>
                    </c:if>
                </c:forEach>

                <c:if test = "${currentPage != totalPage}">
                    <a href="/bookshop/store?page=${currentPage+1}&price=${price}&category=${category.nameCategory}&nxb=${nxb}">Next</a>
                </c:if>
            </div>
        </c:if>


    </div>


</div>
<div class="content-sidebar">
    <h4>Lọc sản phẩm</h4>
    <form>
        <input type="hidden" name="name" value="${name}">
        <p>Mức giá</p>
        <div class="select-range">
            <select name="price" class="form-control">
                <option value="">Tất cả giá</option>
                <option value="duoi-5-chuc">Dưới 50.000Đ</option>
                <option value="5-chuc-den-1-tram">50.000 đến 100.000Đ</option>
                <option value="1-tram-den-2-tram">100.000 - 200.000Đ</option>
                <option value="2-tram-den-3-tram">200.000 - 300.000Đ</option>
                <option value="tren-3-tram">Trên 300.000Đ</option>
            </select>
        </div>
        <p>Nhà sản xuất</p>
        <div class="select-range">
            <select name="nxb" class="form-control">
                <option value="">Tất cả nhà sản xuất</option>
                <c:forEach var="nxb" items="${nxbs}" varStatus="loop">
                    <option value="${nxb}">${nxb}</option>
                </c:forEach>
            </select>
        </div>
        <input type="hidden" name="category" value="${category.nameCategory}">
        <input class="search-submit" type="submit" value="Lọc sản phẩm">
    </form>

</div>
</div>
<div class="clear"> </div>
</body>
</html>