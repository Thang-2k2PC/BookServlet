<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Book Shop - Đăng ký</title>
  <link rel="stylesheet" href="/resources/Frontend/css/login.css">
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script
          src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script
          src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="login-page">
  <div class="form">
    <form method="POST" action="/bookshop/register" >
      <h2 class="form-signin-heading" style="text-align: center">BookShop - Đăng ký tài khoản</h2>
      <hr/>
      <div class="form-group">
        <input type="email" name="email" class="form-control" placeholder="Email" autofocus="true" required="required">
          <c:if test="${errorValid['errorEmail'] != null}">
              <div class="error">${errorValid['errorEmail']}</div>
          </c:if>
      </div>

      <div class="form-group">
        <input type="password" name="password" class="form-control" required="required" placeholder="Mật khẩu">
          <c:if test="${errorValid['errorPassword'] != null}">
              <div class="error">${errorValid['errorPassword']}</div>
          </c:if>
      </div>

      <div class="form-group">
        <input type="password" name="confirmPassword" class="form-control" placeholder="Nhắc lại mật khẩu" required="required">
          <c:if test="${errorValid['errorConfirmPassword'] != null}">
              <div class="error">${errorValid['errorConfirmPassword']}</div>
          </c:if>
      </div>

      <div class="form-group">
        <input type="text" name="username" class="form-control" placeholder="Họ và tên" required="required">
      </div>

      <div class="form-group">
        <input type="number" name="phone" class="form-control" placeholder="Số điện thoại" required="required">
      </div>

      <div class="form-group">
        <input type="text" name="address" class="form-control" placeholder="Địa chỉ" required="required">
      </div>
      <input id="submit" type="submit" value="ĐĂNG KÝ">
      <p class="message" style="font-size: 18px; padding-top:10px"> Đã có tài khoản? <a href="<c:url value='/bookshop/login'/> ">Đăng nhập</a></p>
    </form>
  </div>
</div>
<script
        src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

</body>
</html>