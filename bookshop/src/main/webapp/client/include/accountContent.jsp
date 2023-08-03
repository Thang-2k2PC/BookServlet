<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<script src="<c:url value='/resources/js/client/information.js'/>"></script>
<script src="<c:url value='/resources/js/client/password.js'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $(".mytable .tongGiaTri ").each(function() {
            var value = accounting.formatMoney($(this).text()) + ' VND';
            console.log(value)
            $(this).html(value);
        });

    });
</script>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <br>
            <p style="font-size: 20px">
                <b>Thông tin tài khoản:</b>
            </p>
            <br>
            <h3 style="line-height: 2;">
                <span style="font-weight: bold">Họ tên: </span>${user.username}</h3>
            <h3 style="line-height: 2;">
                <span style="font-weight: bold">Số điện thoại: </span>${user.phone}</h3>
            <h3 style="line-height: 2;">
                <span style="font-weight: bold">Email: </span>${user.email}</h3>
            <h3 style="line-height: 2;">
                <span style="font-weight: bold"> Địa chỉ: </span>${user.address}</h3>
            <br> <a class="btn btn-primary" data-toggle="modal"
                    data-target="#modalInformation">Cập nhật thông tin cá nhân</a>
            &nbsp; &nbsp; &nbsp; <a class="btn btn-danger" data-toggle="modal"
                                    data-target="#modalChangePassword">Đổi mật khẩu</a> <br> <br>

        </div>
        <div class="col-md-1"></div>
    </div>
</div>
<!-- Modal cập nhật thông tin -->
<div class="modal fade" id="modalInformation" tabindex="-1"
     role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" >
                    <b>Cập nhật thông tin tài khoản</b>
                </h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="form-group ">
                    <label style="line-height: 2">Họ tên khách hàng*:</label><br>
                    <textarea rows="3" cols="1" class="form-control" id="name">${user.username}</textarea>
                </div>
                <div class="form-group ">
                    <label style="line-height: 2">Số điệnt thoại*:</label><br> <label
                        id="phoneWarning" style="color: red"></label> <input
                        class="form-control" id="phone" name="phone" type="text"
                        value=${user.phone}>
                </div>
                <div class="form-group">
                    <label style="line-height: 2">Địa chỉ*:</label><br> <label
                        id="addressWarning" style="color: red"></label>
                    <textarea rows="3" cols="1" class="form-control" id="address">${user.address}</textarea>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-primary"
                        onclick="changeInformation()">Cập nhật</button>
            </div>

        </div>
    </div>
</div>
<!-- Modal cập nhật thông tin -->

<!-- Modal đổi pass -->

<div class="modal fade" id="modalChangePassword" tabindex="-1"
     role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                    <b>Thay đổi mật khẩu</b>
                </h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group ">
                    <label style="line-height: 2">Mật khẩu cũ*:</label><br> <label
                        id="oldWarning" style="color: red"></label> <input
                        class="form-control" id="old" name="old" type="password">
                </div>
                <div class="form-group ">
                    <label style="line-height: 2">Mật khẩu mới*:</label><br> <label
                        id="new1Warning" style="color: red"></label> <input
                        class="form-control" id="new1" name="new1" type="password">
                </div>
                <div class="form-group ">
                    <label style="line-height: 2">Xác nhận lại mật khẩu mới*:</label><br>
                    <label id="new2Warning" style="color: red"></label> <input
                        class="form-control" id="new2" name="new2" type="password">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-primary"
                        onClick="changePass()">Đổi mật khẩu</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal đổi pass -->


</body>
</html>