function changeInformation()
{
    var name = document.getElementById("name").value;
    var phone = document.getElementById("phone").value;
    var address = document.getElementById("address").value;

    var send = new Object();
    send.username = name;
    send.phone = phone;
    send.address = address;
    var data = JSON.stringify(send);
    $.ajax({
        type: "POST",
        data: data,
        contentType : "application/json",
        url: "/bookshop/updateInfo",
        success: function(result){


            if(result.status == "Số điện thoại không được để trống"){
                window.alert("Số điện thoại không được để trống");
            }
            else if(result.status == "Địa chỉ không được để trống")
            {
                window.alert("Địa chỉ không được để trống");
            }else if(result.status == "Tên không được để trống"){
                window.alert("Tên không được để trống")
            }else{
                alert("Thông tin đã cập nhật");
                window.location.href = "/bookshop/account";
            }
        },
        error : function(e){
            alert("Error: ",e);
            console.log("Error" , e );
        }
    });

}