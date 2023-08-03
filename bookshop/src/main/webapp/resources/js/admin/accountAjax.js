$(document).ready(function() {

    // load first when coming page
    ajaxGet(1);

    function ajaxGet(page) {
        var data = {roleName: $("#roleName").val()};
        $.ajax({
            type: "GET",
            data: data,
            contentType: "application/json",
            url: "/api/account/all"+ '?page=' + page,
            success: function (result) {
                // var datas = result.datas;
                $.each(result.datas, function (i, account) {
                    var accountRow = '<tr>' +
                        '<td>' + account.id + '</td>' +
                        '<td>' + account.username + '</td>' +
                        '<td>' + account.email + '</td>' +
                        '<td>' + account.phone + '</td>' +
                        '<td>' + account.address + '</td>' +
                        '<td>' + account.role.name + '</td>' ;


                    accountRow += '</td>' +
                        '<td width="0%">' + '<input type="hidden" id="user_id" value=' + account.id + '>' + '</td>' +
                        //					                  '<td><button class="btn btn-primary btnCapNhat" >Cập nhật</button></td>' +
                        '<td><button class="btn btn-danger btnDelete" >Xóa</button></td>';
                    ;
                    $('.accountTable tbody').append(accountRow);

                });

                if (result.totalPages >= 1) {
                    for (var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
                        var li = '<li class="page-item "><a class="pageNumber">' + numberPage + '</a></li>';
                        $('.pagination').append(li);
                    }
                    ;

                    //     // active page pagination
                    $(".pageNumber").each(function (index) {
                        if ($(this).text() == page) {
                            $(this).parent().removeClass().addClass("page-item active");
                        }
                    });
                };
            },
            error: function (e) {
                alert("Error: ", e);
                console.log("Error", e);
            }
        });
    };

    // event khi click vào phân trang User
    $(document).on('click', '.pageNumber', function (event) {
        var page = $(this).text();
        $('.accountTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });

    // event khi click vào role
    $('#roleName').mouseup(function() {
        var open = $(this).data("isopen");
        if (open) {
            resetData();
        }
        $(this).data("isopen", !open);
    });

    // click thêm tài khoản
    $(document).on('click', '.btnAddAccount', function (event) {
        event.preventDefault();
        $("#accountModal").modal();
    });

    // xác nhận thêm tài khoản
    $(document).on('click', '#btnSubmit', function (event) {
        event.preventDefault();
        ajaxPostAccount();
        resetData();
    });

    function ajaxPostAccount() {
        var data = JSON.stringify($('.accountForm').serializeJSON());
        console.log(data);

        // do post
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "/api/account/save",
            data : data,
            success : function(response) {
                if(response.status == "success"){
                    $('#accountModal').modal('hide');
                    alert("Thêm thành công");
                } else {
                    $('input').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        if(key != "id"){
                            $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
                        };
                    });
                }

            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }
    // event khi ẩn modal form
    $('#accountModal').on('hidden.bs.modal', function(e) {
        e.preventDefault();
        $('.accountForm input').next().remove();
    });

    // delete request
    $(document).on("click",".btnDelete", function() {

        var user_id = $(this).parent().prev().children().val();
        var confirmation = confirm("Bạn chắc chắn xóa tài khoản này ?");
        if(confirmation){
            $.ajax({
                type : "DELETE",
                url : "/api/account/delete?user_id=" + user_id,
                success: function(resultMsg){
                    resetData();
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            });
        }
    });


    // reset table after post, put, filter
    function resetData(){
        var page = $('li.active').children().text();
        $('.accountTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    };

    (function ($) {
        $.fn.serializeFormJSON = function () {

            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
    })(jQuery);

});