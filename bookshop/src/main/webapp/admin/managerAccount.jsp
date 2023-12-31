<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý tài khoản</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/sidebar.jsp"></jsp:include>

<div class="col-md-9 animated bounce">
    <h3 class="page-header">Quản lý Tài khoản</h3>

    <button class="btn btn-success btnAddAccount">Thêm mới tài khoản</button>
    <hr>
    <div class="form-group form-inline">
        <label><strong>Lọc tài khoản:</strong> </label>
        <select id="roleName" class="form-control">
        <c:forEach var="role" items="${listRole}">
            <option value="${role.name }">${role.name }</option>
        </c:forEach>
        </select>
    </div>
    <hr>


    <table class="table table-hover accountTable"
           style="text-align: center;">
        <thead>
        <tr>
            <th>Mã User</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>Số điện thoại</th>
            <th>Địa chỉ</th>
            <th>Vai trò</th>
            <th></th>
        </tr>

        </thead>
        <tbody>
        </tbody>

    </table>

    <ul class="pagination">
    </ul>
</div>
<div class="row col-md-6">
    <form class="accountForm" id="form">
        <div>
            <div class="modal fade" id="accountModal" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Tạo mới tài khoản</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!-- 								<div class="form-group">
                                                                <input type="hidden" class="form-control" name="id" />
                                                            </div> -->
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" name="email" required />
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu:(8-32 ký tự)</label>
                                <input type="password" class="form-control" name="password" required />
                            </div>
                            <div class="form-group">
                                <label>Nhắc lại mật khẩu:</label>
                                <input type="password" class="form-control" name="confirmPassword" required />
                            </div>

                            <div class="form-group">
                                <label>Chọn vai trò:</label>
                                <c:forEach var="role" items="${listRole}">
                                    <label class="radio-inline">
                                        <input type="radio"
                                               name="role_name" value="${role.name}" checked="checked">
                                            ${role.name}
                                    </label>
                                </c:forEach>
                            </div>

                            <div class="form-group">
                                <label>Họ tên:</label>
                                <input type="text" class="form-control" name="username" required />
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại:</label>
                                <input type="text"  class="form-control" name="phone" required />
                            </div>
                            <div class="form-group">
                                <label >Địa chỉ:</label>
                                <input type="text" class="form-control" name="address" required />
                            </div>


                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy</button>
                                <input class="btn btn-primary" id="btnSubmit" type="button"
                                       value="Xác nhận" />
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
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>
<script src="<c:url value='/resources/js/admin/accountAjax.js'/>"></script>
</body>
</html>