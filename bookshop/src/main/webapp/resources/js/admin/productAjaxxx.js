$(document).ready(function () {


    // load first when coming page
    ajaxGet(1);


    // do get
    function ajaxGet(page) {
        // prepare data
        var data = $('#searchForm').serialize();
        $.ajax({
            type: "GET",
            data: data,
            contentType: "application/json",
            url: "/api/product/all" +"?page=" + page,
            success: function (result) {
                $.each(result.listProduct, function (i, product) {
                    var productRow  = '<tr>' +
                        '<td>' + '<img src="/resources/images/'+product.id+'.png" id="images" class="img-responsive" data-product-id="' + product.id + '" style="height: 50px; width: 50px" />'+'</td>' +
                        '<td>' + product.nameProduct + '</td>' +
                        '<td>' + product.category.nameCategory + '</td>' +
                        '<td>' + product.nxb.nameNxb + '</td>' +
                        '<td>' + product.price + '</td>' +
                        '<td>' + product.wareHouse + '</td>' +
                        '<td width="0%">'+'<input type="hidden" id="product_id" value=' + product.id + '>'+ '</td>' +
                        '<td> <button class="btn btn-warning btnDetail" style="margin-right: 6px">Chi tiết</button>' ;
                    var checkNameCategory = (product.category.nameCategory.toLowerCase()).indexOf("Book".toLowerCase());
                    productRow += ( checkNameCategory != -1)?'<button class="btn btn-primary btnUpdateBook" >Cập nhật</button>':'<button class="btn btn-primary btnUpdateBook" >Cập nhật</button>';
                    productRow += '  <button class="btn btn-danger btnDeleteBook">Xóa</button></td>'+'</tr>';
                    $('.productTable tbody').append(productRow);
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
    // event khi click vào phân trang Product
    $(document).on('click', '.pageNumber', function (event) {
        var page = $(this).text();
        $('.productTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });

    // event khi click vào  mục thêm sản phẩm
    $('#categoryDropdown').mouseup(function() {
        var open = $(this).data("isopen");
        if (open) {
            var label = $('#categoryDropdown option:selected').text();
                $('.bookModal').modal('show');
                $("#category_id").val($(this).val());
                $('#bookForm').removeClass().addClass("addOtherForm");
                $('#bookForm #btnSubmit').removeClass().addClass("btn btn-primary btnSaveBookForm");
            $(".modal-title").text("Thêm mới sản phẩm danh mục "+ label);

        }
        $(this).data("isopen", !open);
    });



    // btnSaveOtherForm event click
    $(document).on('click', '.btnSaveBookForm', function (event) {
        event.preventDefault();
        ajaxPost();
        resetData();
    });

    function ajaxPost() {

        // PREPATEE DATA
        var form = $('.addOtherForm')[0];
        var data = new FormData(form);

        var formData = {};
        $('form').find('input, textarea').each(function() {
            formData[this.name] = $(this).val();
        });
        // do post
        $.ajax({
            async:false,
            type : "POST",
            url : "/api/product/save",
            data : data,

            // prevent jQuery from automatically transforming the data into a
            // query string
            processData: false,
            contentType: false,
            cache: false,
            timeout: 1000000,

            success : function(response) {
                if(response.status == "success"){
                    $('.bookModal').modal('hide');
                    alert("Thêm thành công");
                } else {
                    $('input, textarea').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        if(key != "id"){
                            $('input[name='+ key +']').val(formData[key]).after('<span class="error">'+value+'</span>');
                            $('textarea[name='+ key +']').val(formData[key]).after('<span class="error">'+value+'</span>');
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


    // // click vào button chi tiết
    $(document).on("click",".btnDetail", function() {

        var product_id = $(this).parent().prev().children().val();
        console.log(product_id);

        var href = "/api/product?product_id="+product_id;
        $.get(href, function(product) {
            $('.images').attr("src", "/resources/images/"+product.id+".png");
            $('.nameProduct').html("<span style='font-weight: bold'>Tên sản phẩm: </span> "+ product.nameProduct);
            $('.product_id').html("<span style='font-weight: bold'> Mã sản phẩm: </span>"+ product.id);
            $('.nxb').html("<span style='font-weight: bold'>Nhà xuất bản: </span>"+ product.nxb.nameNxb);

            var checkNameCategory = (product.category.nameCategory.toLowerCase()).indexOf("Book".toLowerCase());

            console.log(checkNameCategory != -1);
            if(checkNameCategory != -1){

            }
            $('.infoProduct').html("<span style='font-weight: bold'>Thông tin chung: </span>"+ product.infoProduct);
            $('.price').html("<span style='font-weight: bold'>Đơn giá: </span>"+ product.price +" VNĐ");
            $('.wareHouse').html("<span style='font-weight: bold'>Đơn vị trong kho: </span>"+ product.wareHouse);

        });

        $('#detailModal').modal('show');

    });



    // Update
    $(document).on("click",".btnUpdateBook", function(event){
        event.preventDefault();
        var product_id = $(this).parent().prev().children().val();
        $('#bookForm').removeClass().addClass("updateBookForm");
        $('#bookForm #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateBookForm");

        var href = "/api/product?product_id="+product_id;
        $.get(href, function(product) {
            populate('.updateBookForm', product);
            $("#category_id").val(product.category.id);
            var nxb_id = product.nxb.id;
            $("#nxb_id").val(nxb_id);
        });
        removeElementsByClass("error");
        $('.updateBookForm .bookModal').modal();
    });

    // btnUpdateOtherForm event click
    $(document).on('click', '.btnUpdateBookForm', function (event) {
        event.preventDefault();
        ajaxPut();
        resetData();
    });

    function ajaxPut() {
        // PREPARE DATA
        var form = $('.updateBookForm')[0];
        var data = new FormData(form);

        // do put
        $.ajax({
            async:false,
            type : "PUT",
            // contentType : "application/json",
            url : "/api/product/save",
            data : data,

            // prevent jQuery from automatically transforming the data into a
            // query string
            processData: false,
            contentType: false,
            cache: false,
            timeout: 1000000,

            success : function(response) {
                if(response.status == "success"){
                    $('.bookModal').modal('hide');
                    alert("Cập nhật thành công");
                } else {
                    $('input, textarea').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        if(key != "id"){
                            $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
                            $('textarea[name='+ key +']').after('<span class="error">'+value+'</span>');
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


    // click vào button xóa
    $(document).on("click",".btnDeleteBook", function() {

        var product_id = $(this).parent().prev().children().val();
        var workingObject = $(this);

        var confirmation = confirm("Bạn chắc chắn xóa sản phẩm này ?");
        if(confirmation){
            $.ajax({
                async:false,
                type : "DELETE",
                url : "/api/product/delete?product_id=" + product_id,
                cache: false,
                success: function(resultMsg){
                    resetDataForDelete();
                    alert("Xóa thành công");
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            });
        }
        // resetData();
    });

    // fill input form với JSon Object
    function populate(frm, data) {
        $.each(data, function(key, value){
            $('[name='+key+']', frm).val(value);
        });
    }

    // // event khi ẩn modal chi tiết
    $('#detailModal').on('hidden.bs.modal', function(e) {
        e.preventDefault();
        $(".detailForm p").text(""); // reset text thẻ p
    });

    // reset table after delete
    function resetDataForDelete(){
        var count = $('.productTable tbody').children().length;
        $('.productTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        console.log(page);
        if(count == 1){
            ajaxGet(page -1 );
        } else {
            ajaxGet(page);
        }

    };

    // reset table after post, put, filter
    function resetData(){
        // var product_id = $(this).parent().prev().children().val();
        var page = $('li.active').children().text();
        $('.productTable tbody tr').remove();
        $('.pagination li').remove();
        // $('.images').attr("src", "/resources/images/"+product_id+".png").remove();


        ajaxGet(page);
    };

    // remove element by class name
    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }

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
