function contentFun() {
    var articleList = defaultWebUrl + "user/articleList"
    var first = 0;
    var lenght = 2;
    var array ={'first': first, 'lenght': lenght};
    var trReq = new jsonReq();
    trReq.setEventListener("receive", pageFun);
    trReq.send(articleList, array)
    /*$.ajax({
        type:"GET",
        url: articleList,
        data: array,
        dataType: "json",
        error:function(data){
            console.log(data);
        },
        success:function(data){

            $("#code_id01").html(data.code0);
            $("#artile_id01").html(data.content0);
            $("#title_id01").html(data.title0);
            $("#img_01").attr('src',data.imgUrl0);
            $("#time_01").html("创建时间:" + data.crTime0)
            if (data.artile1 != "") {
                $("#code_id02").show();
                $("#code_id02").html(data.code1);
                $("#artile_id02").html(data.content1);
                $("#title_id02").html(data.title1);
                $("#img_02").attr('src',data.imgUrl1);
                $("#time_02").html("创建时间" + data.crTime1)
            } else {
                $("#code_id02").hide();
            }

        }
    });*/

}
function pageFun(data) {
    $("#code_id01").html(data.code0);
    $("#artile_id01").html(data.content0);
    $("#title_id01").html(data.title0);
    $("#img_01").attr('src',data.imgUrl0);
    $("#time_01").html("创建时间:" + data.crTime0)
    if (data.artile1 != "") {
        $("#code_id02").show();
        $("#code_id02").html(data.code1);
        $("#artile_id02").html(data.content1);
        $("#title_id02").html(data.title1);
        $("#img_02").attr('src',data.imgUrl1);
        $("#time_02").html("创建时间" + data.crTime1)
    } else {
        $("#code_id02").hide();
    }
    $("#pageBar").show();

    pageBar= fm_page({
        id : 'pageBar',
        pageNo : 0,
        pageSize : 2,
        pageCount : data.pageCount,
        pageChange : function(pageNo) {

        }
    });
}

function titleByOne(data) {
    var titleByOneUrl = defaultWebUrl + "user/titleByOneUrl"
    $("#pageBar").hide();
    var titleRes = data.innerHTML
    var cond = {"titleRes": titleRes}
    var trReq = new jsonReq();
    trReq.setEventListener("receive", resultData);
    trReq.send(titleByOneUrl, cond)

}

function titleByTwo() {
    /*var titleByTwoUrl = defaultWebUrl + "user/titleByOneUrl"
    $("#pageBar").hide();
    var tags = $("#tags").val();
    var cond = {"titleRes": tags}
    var trReq = new jsonReq();
    trReq.setEventListener("receive", resultData);
    trReq.send(titleByTwoUrl, cond)*/
    var titleByTwoUrl = defaultWebUrl + "user/titleByTwoUrl"
    $("#pageBar").hide();
    var tags = $("#tags").val();
    $.ajax({
        type:"GET",
        url: titleByTwoUrl,
        data: {"title" : tags},
        dataType: "json",
        error:function(data){
            console.log(data);
        },
        success:function(data){

            if(data.code == null || data.code == "") {
                $("#code_id01").hide();
            } else {
                $("#code_id01").show();
                $("#code_id01").html(data.code);
            }

            $("#artile_id01").html(data.content);
            $("#title_id01").html(data.title);
            $("#img_01").attr('src',data.imgUrl);
            $("#time_01").html(data.crTime)
            $("#code_id02").hide();
            $("#artile_id02").html("");
            $("#title_id02").html("");
            $("#img_02").attr('src',"");
            $("#time_02").html("")

        }
    });
}

function resultData(data) {
    if(data.code == null || data.code == "") {
        $("#code_id01").hide();
    } else {
        $("#code_id01").show();
        $("#code_id01").html(data.code);
    }

    $("#artile_id01").html(data.content);
    $("#title_id01").html(data.title);
    $("#img_01").attr('src',data.imgUrl);
    $("#time_01").html(data.crTime)
    $("#code_id02").hide();
    $("#artile_id02").html("");
    $("#title_id02").html("");
    $("#img_02").attr('src',"");
    $("#time_02").html("")
}

function hotContentFun() {
    var hotContentUrl = defaultWebUrl + "user/hotByList"
    $.ajax({
        type:"GET",
        url: hotContentUrl,
        error:function(data){
            //alert("出错了！！:"+data);
        },
        success:function(data){

            for (var i = 0; i < data.hotByList.length; i++) {
                $("#hotList_div").append("<li><a href=\"javascript:void(0)\" onclick=\"titleByOne(this.valueOf())\">"+data.hotByList[i]+"</a></li>")
            }
        }
    })
}
function  fail() {
    //alert("获取文本失败");
}

function massage() {
    if($("#comment").is(":hidden")){
        $("#comment").show();

    } else {
        $("#comment").hide();
    }

    console.log("展开评论区")
}

function chageCode(){
    $('#codeImage').attr('src','authCode.do?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
}