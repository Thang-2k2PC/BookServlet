$(document).ready(function(){
	$(".add-to-cart").click(function(){
		ajaxGet($("#productId").text());
	});

	function ajaxGet(id){
		$.ajax({
			type: "GET",
			url: "/api/cart/addProduct?id="+id,
			success: function(result){
				if(result.status == "false")
				{
					window.alert("Sản phẩm đang hết hàng, quý khách vui lòng quay lại sau");
				}else
				{
					window.alert("Đã thêm sản phẩm vào giỏ hàng");
				}
			},
			error : function(e){
				alert("Error: ",e);
				console.log("Error" , e );
			}
		});
	}
})
