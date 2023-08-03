$(document).ready(function(){

    // click event button Cap nhật thông tin
    $('.btnUpdateInfo').on("click", function(event) {
        event.preventDefault();
        var user_id = $(".user_id").val();

        var href = "/api/profile/user?user_id="+user_id;
        $.get(href, function(result) {
            let user = result.data;
            populate('.formUpdate', user);
        });

        $('.formUpdate #updateModal').modal();
    });

    // fill input form với JSon Object
    function populate(frm, data) {
        $.each(data, function(key, value){
            if(key != "id"){
                $('[name='+key+']', frm).val(value);
            }
        });
    }





    // reset table after post, put
    function resetData(){
        $('.nxbTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        var count = $('.nxbTable tbody').children().length;
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

    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }
})

function ajaxPost() {
    var form = $('.formUpdate');
    $.ajax({
        type: 'POST',
        // data: form.serialize(),
        data: JSON.stringify(form),
        // data: form.serialize(),
        success: function(response) {
            if (response.status === 'success') {
                alert('Cập nhật thông tin cá nhân thành công!');
                $('#updateModal').modal('hide');
                // Cập nhật thông tin cá nhân trên trang nếu cần
            } else {
                alert('Cập nhật thông tin cá nhân thất bại!');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}