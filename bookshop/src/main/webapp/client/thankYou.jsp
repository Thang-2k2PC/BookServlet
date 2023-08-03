<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <script src="/resources/Frontend/js/jquery.min.js"></script>
    <script src="/resources/Frontend/js/responsiveslides.min.js"></script>
    <script src="/resources/js/client/accounting.js"></script>
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

<!----start-Header---->
<jsp:include page="include/homeHeader.jsp"></jsp:include>
<!----End-Header---->

<div class= "container">
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-10">
            <br>
            <p><b>QUÝ KHÁCH ĐÃ ĐẶT HÀNG THÀNH CÔNG</b></p><br>

            <p> Thông tin đơn hàng đã đặt :</p><br>
            <p><b>ID/Mã đơn hàng: </b> ${order.id }</p><br>
            <p><b>Họ tên người nhận hàng: </b> ${order.nameCustomer}</p><br>
            <p><b>Số điện thoại: </b>${order.phone}</p><br>
            <p><b>Địa chỉ: </b>${order.addressReceive}</p><br>
            <p><b>Ghi chú: </b>${order.note } </p><br>
            <p>Vào lúc ${order.dateOrder} chúng tôi đã nhận được đơn đặt hàng với những sản phẩm như sau:</p><br>



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
                        </td>
                        <td style="color:green">
                                ${product.nameProduct}
                        </td>

                        <td class="price" >
                            <div class="check " style ="display: inline-block; ">${product.price}</div>
                            <div style="display: inline-block; "> x ${quantity[product.id]}</div>
                        </td>

                        <td>
                            <div class="total">${product.price*quantity[product.id]}</div>
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <br>
            <p>Tổng giá trị đơn hàng:     <b id="ordertotal"> </b></p>
            <br>





            <p>BookShop xin chân thành cảm ơn quý khách hàng đã tin tưởng sử dụng dịch vụ, sản phẩm của chúng tôi</p><br>
            <a href="<%=request.getContextPath()%>/">Nhấn vào đây để tiếp tục mua sắm</a>
        </div>
        <div class="col-md-1">
        </div>



    </div>


</div>

<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->
<script src="<c:url value='/resources/js/client/checkoutAjaxx.js'/>" ></script>
</body>
</html>

