<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý Đơn hàng</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <style>
        .form-detail input {
            border: 0;
        }
    </style>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/sidebar.jsp"></jsp:include>

<div class="col-md-9 animated bounce">
    <h3 class="page-header form-inline">Quản lý Đơn hàng</h3>

    <form class="form-inline" id="searchForm" name="searchObject">

        <select class="form-control" id="status">
            <option value="Đang giao">Đang giao</option>
            <option value="Đang chờ giao">Đang chờ giao</option>
<%--            <option value="Đang chờ duyệt">Chờ duyệt</option>--%>
            <option value="Hoàn thành">Hoàn thành</option>
            <option value="Đã bị hủy">Đã bị hủy</option>
            <!-- <option value="">Tất cả</option> -->
        </select>

        <div class="form-group">
            <input class="form-control" type="text" id="fromDate"
                   placeholder="Từ ngày">
        </div>

        <div class="form-group">
            <input class="form-control" type="text" id="toDate"
                   placeholder="Đến ngày">
        </div>
        &nbsp;&nbsp; &nbsp;&nbsp;
        <button type="button" class="btn btn-primary" id="btnApproveOrder">Duyệt
            Đơn</button>
        <div class="form-group" style="float: right; margin-right: 20px">
            <input class="form-control" type="number" id="searchById"
                   placeholder="Nhập mã để tìm nhanh"> <span
                class="glyphicon glyphicon-search" aria-hidden="true"
                style="left: -30px; top: 4px"></span>
        </div>
    </form>

    <hr />
    <table class="table table-hover orderTable"
           style="text-align: center">
        <thead>
        <tr>
            <th>Mã</th>
            <th>Người nhận</th>
            <th>Trạng thái</th>
            <th>Giá trị</th>
            <th>Ngày đặt</th>
            <th>Ngày giao</th>
            <th>Ngày nhận</th>
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
    <form class="detailForm">
        <div class="modal fade" id="detailModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document" style="width: 700px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <p class="h4 modal-title" id="order_id"></p>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12">
                                <div class="card" style="padding-left: 40px;padding-right: 40px">

                                    <div class="row pb-5 p-5">
                                        <div class="col-md-6">
                                            <h5 class="font-weight-bold mb-4">
                                                <strong>Thông tin khách</strong>
                                            </h5>
                                            <p class="mb-1" id="nameCustomer"></p>
                                            <p class="mb-1" id="addressReceive"></p>
                                            <p class="mb-1" id="phone"></p>
                                        </div>

                                        <div class="col-md-6 text-right"
                                             style="text-align: left; padding-left: 100px">
                                            <h5 class="font-weight-bold mb-4">
                                                <strong>Thông tin đơn hàng</strong>
                                            </h5>
                                            <p class="mb-1" id="statusOrder"></p>
                                            <p class="mb-1" id="dateOrder"></p>
                                            <p class="mb-1" id="dateDelivery"></p>
                                            <p class="mb-1" id="dateReceive"></p>
                                        </div>
                                    </div>
                                    <hr />
                                    <div class="row p-5">
                                        <div class="col-md-12">
                                            <table class="table detailTable"
                                                   style="text-align: center;">
                                                <thead>
                                                <tr>
                                                    <th
                                                            class="border-0 text-uppercase small font-weight-bold">STT</th>
                                                    <th
                                                            class="border-0 text-uppercase small font-weight-bold">Tên
                                                        sản phẩm</th>

                                                    <th
                                                            class="border-0 text-uppercase small font-weight-bold">Đơn
                                                        giá</th>
                                                    <th
                                                            class="border-0 text-uppercase small font-weight-bold">Số
                                                        lượng đặt</th>

                                                </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row-reverse bg-dark text-white p-4">
                                        <div class="py-3 px-5 text-right">
                                            <div class="mb-2">
                                                <p id="sumPrice"></p>
                                            </div>
                                        </div>
                                    </div>
                                    <hr />

                                    <div class="col-md-6">
                                        <h5 class="font-weight-bold mb-4">
                                            <strong>Thông tin khác</strong>
                                        </h5>
                                        <p class="mb-1" id="shipper_id"></p>
                                        <p class="mb-1" id="user_id_order"></p>
                                        <p id="note"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="row col-md-6">
    <form class="assignForm" id="form">
        <div>
            <div class="modal fade" id="assignModal" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >Phân công đơn hàng</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" id="id" value="">
                            </div>
                            <div class="form-group">
                                Chọn shipper cho đơn hàng: <select class="form-control"
                                                                   name="shipper">
                                <c:forEach var="shipper" items="${allShipper }">
                                    <option value="${shipper.email }">${shipper.username }</option>
<%--                                        (${fn:length(shipper.listDonHang)})</option>--%>
                                </c:forEach>
                            </select>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy</button>
                                <input class="btn btn-primary" id="btnAssignSubmit"
                                       type="button" value="Xác nhận" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="row col-md-6">
    <form class="updateStatusForm" id="form">
        <div>
            <div class="modal fade" id="updateStatusModal" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog " role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Xác nhận hoàn thành đơn</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" id="idOrder" value="">
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <table class="table detailTable"
                                           style="text-align: center;">
                                        <thead>
                                        <tr>
                                            <th class="border-0 text-uppercase small font-weight-bold">STT</th>
                                            <th class="border-0 text-uppercase small font-weight-bold">Tên
                                                sản phẩm</th>
                                            <th class="border-0 text-uppercase small font-weight-bold">Đơn
                                                giá</th>
                                            <th class="border-0 text-uppercase small font-weight-bold">Số
                                                lượng đặt</th>
                                            <th class="border-0 text-uppercase small font-weight-bold">Số
                                                lượng nhận</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <h4 id="sumPriceConfirm" style="float: right; font-weight: bold;padding-right: 50px">abc</h4>
                                </div>

                                <div>
                                    <h5 id="note" style="font-weight: bold; padding-top: 10px">Ghi
                                        chú</h5>
                                    <textarea rows="3" cols="75" id="noteAdmin"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy</button>
                                <input class="btn btn-primary" id="btnXacNhan" type="button"
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
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
    $(function() {

        if (${param.trangThai != null}){
            $("#trangThai").val('${param.trangThai}');
            console.log('${param.trangThai}');
        };

        var from = $("#fromDate").datepicker({
            dateFormat : "yy-mm-dd",
            changeMonth : true
        }).on("change", function() {
            to.datepicker("option", "minDate", getDate(this));
        }), to = $("#toDate").datepicker({
            dateFormat : "yy-mm-dd",
            changeMonth : true
        }).on("change", function() {
            from.datepicker("option", "maxDate", getDate(this));
        });

        function getDate(element) {
            var date;
            var dateFormat = "yy-mm-dd";
            try {
                date = $.datepicker.parseDate(dateFormat, element.value);
            } catch (error) {
                date = null;
            }

            return date;
        }
    });
</script>

<script src="<c:url value='/resources/js/admin/orderAjax.js'/>"></script>
</body>
</html>