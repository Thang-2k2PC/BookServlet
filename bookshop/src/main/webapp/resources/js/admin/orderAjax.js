$(document).ready(function () {

    // load first when coming page
    ajaxGet(1);

    function ajaxGet(page) {
        var data = {status: $('#status').val(), fromDate: $('#fromDate').val(), toDate: $('#toDate').val()}
        $.ajax({
            type: "GET",
            data: data,
            contentType: "application/json",
            url: "/api/order/all" + '?page=' + page,
            success: function (result) {
                $.each(result.datas, function (i, order) {
                    // tính giá trị đơn hàng\
                    var sum = 0;
                    var check = order.statusOrder == "Hoàn thành" || order.statusOrder == "Chờ duyệt";
                    // if(check){
                    //     $.each(result, function(i, detail){
                    sum += order.price * order.quantity_order;
                    // });
                    // }
                    // else {
                    //     $.each(result, function(i, detail){
                    //         sum += detail.price * detail.quantity_order;
                    //     });
                    // }
                    var formatDateOrder = new Date(order.dateOrder);
                    var dateOrder = formatDateOrder.toLocaleString();

                    if(order.dateDelivery != null){
                        var formatDateDelivery = new Date(order.dateDelivery);
                        var dateDelivery = formatDateDelivery.toLocaleString();
                    }else{
                        dateDelivery="";
                    }

                    // var formatDateReceive = new Date(order.dateReceive);
                    // var dateReceive = formatDateReceive.toLocaleString();

                    var orderRow = '<tr>' +
                        '<td>' + order.id + '</td>' +
                        '<td>' + order.nameCustomer + '</td>' +
                        '<td>' + order.statusOrder + '</td>' +
                        '<td>' + sum + '(VND)</td>' +
                        '<td>' + dateOrder + '</td>' +
                        '<td>' + dateDelivery + '</td>' +
                        '<td>' + order.dateReceive + '</td>' +
                        '<td width="0%">' + '<input type="hidden" class="order_id" value=' + order.id + '>' + '</td>' +
                        '<td><button class="btn btn-warning btnDetail" >Chi Tiết</button>';
                    if (order.statusOrder == "Đang chờ giao" || order.statusOrder == "Đang giao" ) {
                        orderRow += ' &nbsp;<button class="btn btn-primary btnAssign">Phân công</button>' +
                            ' &nbsp;<button class="btn btn-danger btnCancel">Hủy đơn</button>' +
                         ' &nbsp;<button class="btn btn-primary btnUpdate" >Cập Nhật</button> </td>';
                    }
                        // else if (order.statusOrder == "Đang chờ duyệt") {
                    //     orderRow += ' &nbsp;<button class="btn btn-primary btnUpdate" >Cập Nhật</button> </td>';
                    // }

                    $('.orderTable tbody').append(orderRow);

                    $('td').each(function (i) {
                        if ($(this).html() == 'null') {
                            $(this).html('');
                        }
                    });
                });

                if (result.totalPages >= 1) {
                    for (var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
                        var li = '<li class="page-item "><a class="pageNumber">' + numberPage + '</a></li>';
                        $('.pagination').append(li);
                    }
                    ;

                    // active page pagination
                    $(".pageNumber").each(function (index) {
                        if ($(this).text() == page) {
                            $(this).parent().removeClass().addClass("page-item active");
                        }
                    });
                }
                ;
            },
            error: function (e) {
                alert("Error: ", e);
                console.log("Error", e);
            }
        });
    };

    // event khi click vào phân trang Đơn hàng
    $(document).on('click', '.pageNumber', function (event) {
        // event.preventDefault();
        var page = $(this).text();
        $('.orderTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });

    $(document).on('click', '#btnDuyetDonHang', function (event) {
        event.preventDefault();
        resetData();
    });

    // reset table after post, put, filter
    function resetData() {
        var page = $('li.active').children().text();
        $('.orderTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    };

    // event khi click vào button Chi tiết đơn
    $(document).on('click', '.btnDetail', function (event) {
        event.preventDefault();

        var order_id = $(this).parent().prev().children().val();
//		console.log(donHangId);
        var href = "/api/order?order_id=" + order_id;
        $.get(href, function (order) {
            $('#order_id').text("Mã đơn hàng: " + order.datas.id);
            $('#nameCustomer').text("Người nhận: " + order.datas.nameCustomer);
            $('#phone').text("SĐT: " + order.datas.phone);
            $('#addressReceive').text("Địa chỉ: " + order.datas.addressReceive);
            $('#statusOrder').text("Trạng thái đơn: " + order.datas.statusOrder);
            var formatDateOrder = new Date(order.datas.dateOrder);
            var dateOrder = formatDateOrder.toLocaleString();
            $("#dateOrder").text("Ngày đặt: " + dateOrder);


            if (order.datas.dateDelivery != null) {
                var formatDate = new Date(order.dateDelivery);
                var dateDelivery = formatDateOrder.toLocaleString();
                $("#dateDelivery").text("Ngày giao: " + dateDelivery);
            }

            if (order.datas.dateReceive != null) {
                var formatDate = new Date(order.dateReceive);
                var dateReceive = formatDateOrder.toLocaleString();
                $("#dateReceive").text("Ngày nhận: " + dateReceive);
            }

            if (order.datas.note != null) {
                $("#note").html("<strong>Ghi chú</strong>:<br> " + order.note);
            }

            if (order.datas.user_id_order != null) {
                $("#user_id_order").html("<strong>Người đặt</strong>:  " + order.datas.user.username);
            }

            if (order.datas.shipper_id != null) {
                $("#shipper_id").html("<strong>Shipper</strong>: " + order.datas.shipper_name);
            }

            var check = order.datas.statusOrder == "Hoàn thành" || order.datas.statusOrder == "Chờ duyệt";
            // if(check){
            //     $('.detailTable').find('thead tr').append('<th id="soLuongNhanTag" class="border-0 text-uppercase small font-weight-bold"> SỐ LƯỢNG NHẬN </th>');
            // }
            // thêm bảng:
            var sum = order.datas.price * order.datas.quantity_order; // tổng giá trị đơn
            var stt = 1;
            // var productName = order.datas.product.nameProduct;
            // var productPrice = order.datas.price;
            // var quantity_order = order.datas.quantity_order;

            // Sử dụng thông tin sản phẩm
            // console.log("Tên sản phẩm: " + productName);
            // console.log("Giá sản phẩm: " + productPrice);
            // $.each(order, function (i, detail) {
            var nameProduct = order.datas.product.nameProduct;
            var productPrice = order.datas.price;
            var quantity_order = order.datas.quantity_order;
            var detailRow = '<tr>' +
                '<td>' + stt + '</td>' +
                '<td>' + nameProduct + '</td>' +
                '<td>' + productPrice + '</td>' +
                '<td>' + quantity_order + '</td>';
            var sum = order.datas.price * order.datas.quantity_order;

            $('.detailTable tbody').append(detailRow);
            stt++;

            // });
            $("#sumPrice").html("<strong>Tổng</strong> : " + sum);


        });
        $("#detailModal").modal();
    });


    $(document).on('click', '.btnAssign', function (event) {
        event.preventDefault();
        var order_id = $(this).parent().prev().children().val();
        $("#order_id").val(order_id);
        console.log(order_id);
        $("#assignModal").modal();
    });

    $(document).on('click', '#btnAssignSubmit', function (event) {
        var email = $("select[name=shipper]").val();
        var order_id = $("#order_id").val();
        console.log(order_id);
        ajaxPostAssignOrder(email, order_id)

    });

    function ajaxPostAssignOrder(email, order_id) {

        $.ajax({
            async: false,
            type: "POST",
            contentType: "application/json",
            url: "/api/order/assign?shipper=" + email + "&order_id=" + order_id,
            // enctype: 'multipart/form-data',

            success: function (response) {
                alert("Phân công đơn hàng thành công");
                var status = $('#status').val();
                location.href = window.location.href;
            },
            error: function (e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }

    $(document).on('click', '#btnApproveOrder', function (event) {
        event.preventDefault();
        resetData();
    });

    $(document).on('click', '.btnCancel', function (event) {
        event.preventDefault();
        var order_id = $(this).parent().prev().children().val();
        var confirmation = confirm("Bạn chắc chắn hủy đơn hàng này ?");
        if (confirmation) {
            ajaxPostCancelOrder(order_id);
            resetData();
        }
    });

    // post request xác nhận hủy đơn hàng
    function ajaxPostCancelOrder(order_id) {
        $.ajax({
            async: false,
            type: "POST",
            contentType: "application/json",
            url: "/api/order/cancel?order_id=" + order_id,
            success: function (response) {
                alert("Hủy đơn hàng thành công");
            },
            error: function (e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }

});