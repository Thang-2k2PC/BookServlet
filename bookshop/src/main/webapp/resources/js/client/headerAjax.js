$(document).ready(function(){
    ajaxGet2();
    function ajaxGet2(){
        $.ajax({
            type: 'GET',
            url: '/api/categories',
            success: function(result){
                $.each(result, function(i, category){
                    var content = '<li><a href="/bookshop/store?category='+category.nameCategory+'"><span style=" font-size: 16px; font-weight: 900; ">'+category.nameCategory+'</span></a></li>';
                    var t = '<li><a href="/bookshop/store?category='+category.nameCategory+'"><span  style="padding-right: 100px">'+category.nameCategory+'</span></a></li>';
                    $('#category').append(content);
                    $('#category_1').append(t);
                })
            }
        });

    }
})