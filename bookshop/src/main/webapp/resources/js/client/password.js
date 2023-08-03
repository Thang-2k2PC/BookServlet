function changePass()
{
    var old = document.getElementById("old").value;
    var new1 = document.getElementById("new1").value;
    var new2 = document.getElementById("new2").value;

    var object = new Object();
    object.oldPassword = old;
    object.newPassword = new1;
    object.confirmPassword = new2;
    var data = JSON.stringify(object)
    $.ajax({
        type: "POST",
        data: data,
        contentType : "application/json",
        url: "/bookshop/updatePassword",
        success: function(result){
            if(result.status == "Wrong oldPassword")
            {
                alert("Sai mật khẩu cũ");
//					window.location.reload();
                return;
            }else if(result.status == "PasswordNew != confirmPassword"){
                alert("Mat khau moi voi xac nhan khong khop")
            }else{
                alert("Mật khẩu đã thay đổi");
                window.location.href = "/bookshop/account";
            }

        },
        error : function(e){
            alert("Error: ",e);
            console.log("Error" , e );
        }
    });
}