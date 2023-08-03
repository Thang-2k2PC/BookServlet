$(document).ready(function(){

    // click event button Them moi danh muc
    $('.btnAddNxb').on("click", function(event) {
        event.preventDefault();
        $('.nxbForm #nxbModal').modal();
        $('.nxbForm #id').prop("disabled", true);
        $('#form').removeClass().addClass("addForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary btnSaveForm");
    });

    $('#nxbModal').on('hidden.bs.modal', function () {
        $('#form').removeClass().addClass("nxbForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary");
        resetText();
    });

    // reset text trong form
    function resetText(){
        $("#id").val("");
        $("#nameNxb").val("");
    };


    ajaxGet(1);

    // do get
    function ajaxGet(page){
        $.ajax({
            type: "GET",
            url: "/api/nxb/all?page=" + page,
            success: function(result){
                $.each(result.datas, function(i, nxb){
                    var nxbRow = '<tr style="text-align: center;">' +
                        '<td width="20%"">' + nxb.id + '</td>' +
                        '<td>' + nxb.nameNxb + '</td>' +
                        '<td>'+'<input type="hidden"  id="nxb_id" value=' + nxb.id + '>'
                        + '<button class="btn btn-primary btnUpdateNxb" >Cập nhật</button>' +
                        '   <button class="btn btn-danger btnDeleteNxb">Xóa</button></td>'
                    '</tr>';
                    $('.nxbTable tbody').append(nxbRow);
                });

                if(result.totalPages > 1 ){
                    for(var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
                        var li = '<li class="page-item "><a class="pageNumber">'+numberPage+'</a></li>';
                        $('.pagination').append(li);
                    };

                    // active page pagination
                    $(".pageNumber").each(function(index){
                        if($(this).text() == page){
                            $(this).parent().removeClass().addClass("page-item active");
                        }
                    });
                };
            },
            error : function(e){
                alert("Error: ",e);
                console.log("Error" , e );
            }
        });
    };

    // event khi click vào phân trang nxb
    $(document).on('click', '.pageNumber', function (event) {
        // event.preventDefault();
        var page = $(this).text();
        $('.nxbTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });

    //Add Nxb new
    $(document).on('click', '.btnSaveForm', function (event) {
        event.preventDefault();
        ajaxPost();
        resetData();
    });

    function ajaxPost(){
        // PREPARE FORM DATA
        var formData = { nameNxb : $("#nameNxb").val() } ;

        // DO POST
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "/api/nxb/save",
            data : JSON.stringify(formData),
            success : function(response) {
                if(response.status == "success"){
                    $('#nxbModal').modal('hide');
                    alert("Thêm thành công");
                } else {
                    $('input').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        $('input[id='+ key +']').after('<span class="error">'+value+'</span>');
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
    function resetData(){
        $('.nxbTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        var count = $('.nxbTable tbody').children().length;
        ajaxGet(page);
    };

    // click edit button
    $(document).on("click",".btnUpdateNxb",function(){
        event.preventDefault();
        $('.nxbForm #id').prop("disabled", true);
        var nxb_id = $(this).parent().find('#nxb_id').val();

        $('#form').removeClass().addClass("updateForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateForm");
        var href = "/api/nxb?nxb_id=" + nxb_id;
        $.get(href, function(result) {
            let nxb = result.data;
            $('.updateForm #id').val(nxb.id);
            $('.updateForm #nameNxb').val(nxb.nameNxb);
        });

        removeElementsByClass("error");

        $('.updateForm #nxbModal').modal();
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
            nameNxb : $("#nameNxb").val(),
        }
        // DO PUT
        $.ajax({
            async:false,
            type : "PUT",
            contentType : "application/json",
            url : "/api/nxb/update",
            data : JSON.stringify(formData),
            success : function(response) {

                if(response.status == "success"){
                    $('#nxbModal').modal('hide');
                    alert("Cập nhật thành công");
                } else {
                    $('input').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        $('input[id='+ key +']').after('<span class="error">'+value+'</span>');
                    });
                }
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    };

    // delete request
    $(document).on("click",".btnDeleteNxb", function() {

        var nxb_id = $(this).parent().find('#nxb_id').val();
        var workingObject = $(this);

        var confirmation = confirm("Bạn chắc chắn xóa nhãn hiệu này ?");
        if(confirmation){
            $.ajax({
                type : "DELETE",
                url : "/api/nxb/delete?nxb_id=" + nxb_id,
                success: function(result){
                    if(result.status=="success"){
                        resetDataForDelete();
                        alert("Xóa thành công");
                    }
                },
                error : function(e) {
                    alert("Không thể xóa nhãn hiệu này, hãy kiểm tra lại");
                    console.log("ERROR: ", e);
                }
            });
        }
        resetDataForDelete();
    });

    // reset table after post, put
    function resetDataForDelete(){
        var count = $('.nxbTable tbody').children().length;
        $('.nxbTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
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