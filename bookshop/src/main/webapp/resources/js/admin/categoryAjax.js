$(document).ready(function () {

    // click event button Them moi danh muc
    $('.btnAddCategory').on("click", function (event) {
        event.preventDefault();
        $('.categoryForm #categoryModal').modal();
        $('.categoryForm #id').prop("disabled", true);
        $('#form').removeClass().addClass("addForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary btnSaveForm");
    });

    ajaxGet(1);

    // do get
    function ajaxGet(page) {
        $.ajax({
            type: "GET",
            url: "/api/category/all" + "?page=" + page,
            success: function (result) {
                $.each(result.categories, function (i, category) {
                    var categoryRow = '<tr style="text-align: center;">' +
                        '<td width="20%">' + category.id + '</td>' +
                        '<td>' + category.nameCategory + '</td>' +
                        '<td>' + '<input type="hidden"  id="category_id" value=' + category.id + '>'
                        + '<button class="btn btn-primary btnUpdateCategory" >Cập nhật</button>' +
                        '   <button class="btn btn-danger btnDeleteCategory">Xóa</button></td>'
                    '</tr>';
                    $('.categoryTable tbody').append(categoryRow);
                });
                if (result.totalPages > 1) {
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

    // event khi click vào phân trang Category
    $(document).on('click', '.pageNumber', function (event) {
		event.preventDefault();
        var page = $(this).text();
        $('.categoryTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });

    $(document).on('click', '.btnSaveForm', function (event) {
        event.preventDefault();
        ajaxPost();
        resetData();
    });


    function ajaxPost() {
        // PREPARE FORM DATA
        var formData = {nameCategory: $("#nameCategory").val()};
        // DO POST
        $.ajax({
            async: false,
            type: "POST",
            contentType: "application/json",
            url: "/api/category/save",
            data: JSON.stringify(formData),
            success: function (response) {
                if (response.status == "success") {
                    $('#categoryModal').modal('hide');
                    alert("Thêm thành công");
                    // location.reload();
                } else {
                    var error = response.errorMessages;
                    $.each(error, function (key, value) {
                        $('#nameCategory').after('<span class="error">' + value + '</span>');
                    });
                }

            },
            error: function (e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    };

    // click edit button
    $(document).on("click",".btnUpdateCategory",function(){
        event.preventDefault();
        $('.categoryForm #id').prop("disabled", true);
        var category_id = $(this).parent().find('#category_id').val();
        $('#form').removeClass().addClass("updateForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateForm");
        var href = "/api/category?category_id="+category_id;
        $.get(href, function(category) {
            $('.updateForm #id').val(category.id);
            $('.updateForm #nameCategory').val(category.nameCategory);
        });

        removeElementsByClass("error");

        $('.updateForm #categoryModal').modal();
    });

    // put request
    $(document).on('click', '.btnUpdateForm', function (event) {
        event.preventDefault();
        ajaxPut();
        resetData();
    });


    function ajaxPut(){
        // PREPARE FORM DATA
        var formData = {
            id : $('#id').val(),
            nameCategory : $("#nameCategory").val(),
        }
        // DO PUT
        $.ajax({
            async:false,
            type : "PUT",
            contentType : "application/json",
            url : "/api/category/update",
            data : JSON.stringify(formData),
            success : function(responseObject) {

                if(responseObject.status == "success"){
                    $('#categoryModal').modal('hide');
                    alert("Cập nhật thành công");
                } else {
                    $('input').next().remove();
                    $.each(responseObject.errorMessages, function(key,value){
                        $('#nameCategory').after('<span class="error">'+value+'</span>');
                    });
                }
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    };

    // reset table after post, put
    function resetData() {
        $('.categoryTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        ajaxGet(page);
    };




    // delete request
    $(document).on("click",".btnDeleteCategory", function() {

        var category_id = $(this).parent().find('#category_id').val();

        var confirmation = confirm("Bạn chắc chắn xóa danh mục này ?");
        if(confirmation){
            $.ajax({
                type : "DELETE",
                url : "/api/category/delete?category_id=" + category_id,
                success: function(resultMsg){
                    if(resultMsg.status == "success"){
                        resetDataForDelete();
                        alert("Xóa thành công");
                    }
                },
                error : function(e) {
                    alert("Không thể xóa danh mục này ! Hãy kiểm tra lại");
                    console.log("ERROR: ", e);
                }
            });
        }
    });

    // reset table after delete
    function resetDataForDelete(){
        var count = $('.categoryTable tbody').children().length;
        $('.categoryTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        console.log(page);
        if(count == 1){
            ajaxGet(page -1 );
        } else {
            ajaxGet(page);
        }

    };

    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }

});
