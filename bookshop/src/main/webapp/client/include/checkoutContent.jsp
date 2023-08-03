<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Thanh toán</title>
</head>

<script type="text/javascript">	  
	$(document).ready(function() { 
		 $(".mytable .price .check").each(function() {
			  var value = accounting.formatMoney($(this).text())+ ' VND';
			  $(this).html(value);
			});
	  
		 $(".mytable .total").each(function() {
			  var value = accounting.formatMoney($(this).text()) + ' VND ';
			  $(this).html(value);
			});
	  });
	</script>

<body>

<div class="container">
	<form method="POST" action="<%=request.getContextPath()%>/bookshop/thankyou">
	<div class = "row">
	<br><br>
		<c:if test="${currentUser != null}">
		<div class="col-md-3" >
			<br>
			<p class="border-p" style="line-height:1.5;"><b>Thông tin khách hàng</b></p>
			
			<p style="line-height:2;">Họ tên Quý khách </p>
			<input size="27" value="${currentUser.username}" disabled>
			
			<p style="line-height:2;">Địa chỉ Email </p>
			<input size="27" value="${currentUser.email}" disabled>
			
			<p style="line-height:2;"> Số điện thoại </p>
			<input size="27" value="${currentUser.phone}" disabled>
			
			<p style="line-height:2;">Địa chỉ(số nhà, đường, tỉnh thành) </p>
			<textarea rows="5" cols="29" disabled>${currentUser.address}</textarea>
						
			<br><br>
		</div>
		</c:if>
		<div class="col-md-3">
			<br>
			<p class="border-p" style="line-height:1.5;"><b>Thông tin nhận hàng</b></p>
		
			<p style="line-height:2;" >Họ tên người nhận hàng *</p>
			<input size="27" name="nameCustomer" required>
			
			<p style="line-height:2;">Số điện thoại *</p>
			<input size="27" name="phone" required>
			
			
			<p style="line-height:2;">Địa chỉ(số nhà, đường, tỉnh thành) *</p>
			<textarea rows="5" cols="29" name="addressReceive" required></textarea>

			<br><br>
			<input type="hidden" id="sumPrice" name="sumPrice" value="">
		</div>
		
		<div class="col-md-6">
			<br>
			<p class="border-p" style="line-height:1.5;"><b>Giỏ hàng</b></p>
			<br>
			
				<table class="table-cart-checkout mytable">
					 <tr>
						    <th>Ảnh</th>
						    <th>Tên sản phẩm</th>
						    <th>Đơn giá</th>
						    <th>Tổng</th>
					</tr>
					<c:forEach items="${cart}" var="product" >
				
					 <tr style="text-align: center;">
						    <td>
						    	<img src="/resources/images/${product.id}.png" alt="not found img" class="img-reponsive fix-size-img">
								<input type="hidden" id="product_id" name="product_id" value="${product.id}">
							</td>
						    <td style="color:green">
						    ${product.nameProduct}
						    </td>
						    
						    <td class="price" >
						    	<div class="check " style ="display: inline-block; ">${product.price}</div>
						    	<div style="display: inline-block; "> x ${quantity[product.id]}</div>
								<input type="hidden" id="price" name="price" value="${product.price}">
								<input type="hidden" id="quantity_order" name="quantity_order" value="${quantity[product.id]}">
						    </td>
						    
						    <td>
						    	<div class="total">${product.price*quantity[product.id]}</div>
						    </td>
					</tr>
					</c:forEach>
					
				</table>
			
			
			<br>
			<p>Tổng giá trị đơn hàng:     <b id="ordertotal"> </b></p>
<%--			<input type="hidden" id="sumPrice" name="sumPrice" value="ordertotal">--%>
			<br>
			 &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;
			<a href="<%=request.getContextPath()%>/bookshop/cart" class="btn btn-primary">Quay lại giỏ hàng</a>
			 &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			 
			<button class="btn btn-danger pull-center" type="submit" id="submit">Gửi đơn hàng</button>
		
			<br><br>
		
		</div>
	
	</div>
	</form>
</div>
<script src="<c:url value='/resources/js/client/checkoutAjaxx.js'/>" ></script>
</body>
</html>

