<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" href="/resources/Frontend/css/detailsp.css">
</head>
	<script type="text/javascript">	  
	$(document).ready(function() { 
	  	var priceConvert = accounting.formatMoney(${product.price})+' VND';
		document.getElementById("priceConvert").innerHTML= priceConvert;
		  
	  });
	</script>
<body>
	<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="/bookshop/img/${product.id}.png" /></div>
						</div>		
					</div>
					<div class="details col-md-6">
						<p style="display:none" id="productId">${product.id}</p>
						<h2 class="product-title">${product.nameProduct}</h2>
						<h4 class="product-description"><span class="important">Mô tả sản phẩm:</span></h4>
						<p class="product-nxb"><span class="important">Nhà sản xuất: </span> ${product.nxb.nameNxb}</p>
						<p class="product-info"><span class="important">THÔNG TIN CHUNG: </span> ${product.infoProduct}</p>
						<h4 class="price" id ="blabla">Giá bán: <span id="priceConvert"></span></h4>
						<div class="action">
							<button class="add-to-cart btn btn-warning" type="button">
							<span class="glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="<c:url value='/resources/js/client/detailProductAjax.js'/>" ></script>