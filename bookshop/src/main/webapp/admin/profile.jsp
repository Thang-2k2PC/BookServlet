<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:set var="contextPath" value="${pageContext.request.contextPath}" />--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Thông tin cá nhân</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style>
        .form-profile input {
            border: 0;
        }
    </style>

</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/sidebar.jsp"></jsp:include>

<div class="col-md-9 animated bounce">
    <h3 class="page-header">Thông tin cá nhân</h3>

    <button class="btn btn-success btnUpdateInfo">Cập nhật
        thông tin
    </button>
    &nbsp;&nbsp;&nbsp;
    <button class="btn btn-primary btnChangePassword">Đổi mật khẩu</button>
    <hr/>

    <form class="form-profile">

        <div class="form-group row">
            <div class="col-sm-10">
                <input type="hidden" class="form-control-plaintext user_id"
                       value="${loggedInUser.id }">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Họ
                Tên</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${loggedInUser.username }">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email đăng ký</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${loggedInUser.email }">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Số điện thoại</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${loggedInUser.phone }">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Địa chỉ</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${loggedInUser.address }">
            </div>
        </div>
    </form>
</div>

<div class="row col-md-6">
    <%--      <form class="formUpdate" method="POST">--%>
    <%--    <form class="formUpdate"--%>
    <%--          action="<c:url value='/bookshop/admin/profile/update' />" id="formLogin" method="post">--%>
    <div class="formUpdate">
        <div>
            <div class="modal fade" id="updateModal" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Cập nhật
                                thông tin cá nhân</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <%--              <input type="hidden" name="${_csrf.parameterName}"--%>
                            <%--                     value="${_csrf.token}" />--%>
                            <div class="form-group">
                                <input type="hidden" class="form-control" name="id"
                                       value="${loggedInUser.id }"/>
                            </div>
                            <div class="form-group">
                                <label>Họ tên</label>
                                <input type="text" class="form-control" name="username" required="required"/>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <input type="text" class="form-control" name="phone" required="required"/>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <input type="text" class="form-control" name="address" required="required"/>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy
                                </button>
<%--                                <input class="btn btn-primary" id="btnSubmit" type="submit"--%>
<%--                                       value="Xác nhận"/>--%>
                                <button type="button" class="btn btn-primary"
                                        onclick="ajaxPost()">Cập nhật</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <form class="formChangePassword">
        <div>
            <div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true"
                 data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Đổi mật khẩu</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div>
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </div>

                            <div class="form-group">
                                <label>Mật khẩu cũ</label>
                                <input type="password" class="form-control" name="oldPassword" required="required"/>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu mới</label>
                                <input type="password" class="form-control" name="newPassword" required="required"/>
                            </div>
                            <div class="form-group">
                                <label>Nhắc lại mật khẩu mới</label>
                                <input type="password" class="form-control" name="confirmNewPassword"
                                       required="required"/>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy
                                </button>
                                <input class="btn btn-primary" type="button"
                                       id="btnConfirmPassword" value="Xác nhận"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</div>
<jsp:include page="template/footer.jsp"></jsp:include>
<script src="<c:url value='/resources/js/admin/profileAdminAjax.js'/>"></script>
</body>
</html>