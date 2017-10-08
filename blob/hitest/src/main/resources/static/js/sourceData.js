function keyup(tags) {
    $("#oul").html("");
    $("#oul").show();
    $("#oli").show();
    var hotContentUrl = defaultWebUrl + "user/titleListAll"
    $.ajax({
        type:"GET",
        url: hotContentUrl,
        error:function(data){
            alert("出错了！！:"+data);
        },
        success:function(data){

            for (var i = 0; i < data.length; i++) {
                if (data[i].indexOf(tags) >= 0 && tags != "") {
                    var oli = "<li style='list-style:none' onclick='press(this.valueOf())'>"+data[i]+"</li>"
                    $("#oul").append(oli);
                    //
                }
            }
        }
    })
}

//点击li赋值给输入框
function press(titleFrom) {
    $("#tags").val(titleFrom.innerHTML) ;
}