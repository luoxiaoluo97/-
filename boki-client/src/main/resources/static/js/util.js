function formToJson(form) {
    var strdata = form.serializeArray();
    var obj={};
    for(var i =0;i<strdata.length;i++){
        obj[strdata [i]['name']]=strdata [i]['value'];
    }
    return JSON.stringify(obj);
}

function commonLogin() {
    $.ajax({
        url: basePath+"/login",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: formToJson($('#loginForm')),
        async: true,
        success: function (data) {
            alert(data.code+data.msg);
            if (data.data != null){
                alert(data.data.userName);
                $('#responseData').text(JSON.stringify(data.data));
            }
        },
        error: function () {
            alert("请求失败.");
        }
    });
}