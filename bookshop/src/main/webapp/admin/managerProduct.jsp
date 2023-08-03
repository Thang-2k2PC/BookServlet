<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý sản phẩm</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        .modal-open {
            overflow: scroll;
        }
    </style>
</head>
<body>
<jsp:include page="/admin/template/header.jsp"></jsp:include>
<jsp:include page="/admin/template/sidebar.jsp"></jsp:include>

<div class="col-md-9 animated bounce">
    <h3 class="page-header">Quản lý Sản Phẩm</h3>

    <div class="form-group form-inline">
        <div>
            <label >THÊM MỚI Book (THEO THỂ LOẠI): </label>
        </div>
        <select id="categoryDropdown" class="form-control">
            <c:forEach var="category" items="${listCategory}">
                <option value="${category.id }">${category.nameCategory }</option>
            </c:forEach>
        </select>
    </div>

    <hr>


    <table class="table table-hover productTable"
           style="text-align: center;">
        <thead>
            <tr>
                <th>Hình ảnh</th>
                <th>Tên Book</th>
                <th>Thể Loại </th>
                <th>Nhà suất bản</th>
                <th>Giá Tiền</th>
                <th>Số lượng</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <ul class="pagination">
    </ul>
</div>

<div class="row col-md-6">
    <form class="bookForm" id="bookForm">
        <div class="modal fade bookModal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg" role="document"
                 style="width: 60%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div>
                                <input type="hidden" class="form-control" name='category_id'
                                       id="category_id">
                            </div>
                            <div>
                                <input type="hidden" class="form-control" name="id"
                                       id="product_id" readonly="true">
                            </div>
                            <div class="form-group col-md-12">
                                <label>Tên sản phẩm</label> <input
                                    type="text" class="form-control" name="nameProduct">
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-6">
                                <label>Nhà Sản Xuất</label> <select
                                    name="nxb_id" id="nxb_id" class="form-control">
                                <c:forEach var="nxb" items="${listNxb }">
                                    <option value="${nxb.id }">${nxb.nameNxb}
                                    </option>
                                </c:forEach>
                            </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label>Đơn giá</label> <input
                                    type="number" class="form-control" name="price" min="0"
                                    value="0" id="price">
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label>Mô tả chung</label>
                                <textarea class="form-control"
                                          placeholder="" rows="2" name="infoProduct"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label>Số lượng</label> <input
                                    type="number" class="form-control" name="wareHouse" min="0"
                                    value="0">
                            </div>
                        </div>
                        <div>
                            <label>Hình ảnh</label>
                            <input type="file" class="form-control" name="images">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">Hủy
                        </button>
                        <button class="btn btn-primary" id="btnSubmit" type="submit">Xác
                            nhận
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    </form>
</div>

<div class="row col-md-10">
    <form class="detailForm">
        <div class="modal fade" id="detailModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog " role="document" style="width: 60%">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="title" style="text-align: center; font-weight: bolder;">Chi tiết sản phẩm</h3>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="card">
                            <div class="container-fliud">
                                <div class="wrapper row">
                                    <div class="preview col-md-6">

                                        <div class="preview-pic tab-content">
                                            <div class="tab-pane active" id="pic-1">
                                                <img style="width: 350px; height: 350px" class="images"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="details col-md-6" style="font-size: 16px">
                                        <p class="product_id"></p>
                                        <p class="nameProduct"></p>
                                        <p class="nxb"></p>
                                        <p class="infoProduct"></p>
                                        <p class="price"></p>
                                        <p class="wareHouse"></p>
<%--                                        <p class="donViBan"></p>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">Đóng
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</div>



<jsp:include page="/admin/template/footer.jsp"></jsp:include>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>
<script src="<c:url value='/resources/js/admin/productAjaxxx.js'/>"></script>
</body>
</html>