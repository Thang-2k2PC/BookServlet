<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý Nhà sa xuất</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/sidebar.jsp"></jsp:include>

<div class="col-md-9 animated bounce">
    <h3 class="page-header">Quản lý Nhà xuất bản</h3>

    <button class="btn btn-success btnAddNxb">Thêm mới Nhà xuất bản</button>

    <hr/>

    <table class="table table-hover nxbTable">
        <thead>
        <tr>
            <th>Mã</th>
            <th>Tên Nhà Xuất Bản</th>
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
    <form class="nxbForm" id="form">

        <div>
            <div class="modal fade" id="nxbModal" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Tạo mới/Cập nhật Nhà xuất bản</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>ID:</label>
                                <input type="text" class="form-control" id="id" />
                            </div>
                            <div class="form-group">
                                <label>Tên nhà xuất bản:</label>
                                <input type="text" class="form-control" id="nameNxb" placeholder="Nhập vào tên nhà xuất bản" required />
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy</button>
                                <input class="btn btn-primary" id="btnSubmit" type="button"
                                       value="Xác nhận"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</div>


<jsp:include page="/admin/template/footer.jsp"></jsp:include>
<script src="<c:url value='/resources/js/admin/nxbAjaxx.js'/>" ></script>
</body>
</html>