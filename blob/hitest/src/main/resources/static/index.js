function blob(){
    this.user_id = "";
    this.content_id0 = "";
    this.content_id1="";
}

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
    blob.content_id0 = data.content_id0;
    $("#code_id01").html(data.code0);
    $("#artile_id01").html(data.content0);
    $("#title_id01").html(data.title0);
    $("#img_01").attr('src',data.imgUrl0);
    $("#time_01").html("创建时间:" + data.crTime0)
    if (data.artile1 != "") {
        $("#code_id02").show();
        blob.content_id1 = data.content_id1;
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
            blob.content_id0 = data.content_id;
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
    blob.content_id0 = data.content_id;
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

function massage(data) {
    if(data == 1){
        if($("#comment").is(":hidden")){
            commentSelect(data)
            $("#comment").show();
            $("#comment_two").hide();
        } else {
            $("#comment").hide();
        }
    } else {
        if($("#comment_two").is(":hidden")){
            commentSelect(data);
            $("#comment_two").show();
            $("#comment").hide();

        } else {
            $("#comment_two").hide();
        }
    }


    console.log("展开评论区")
}

function chageCode(){
    $('#codeImage').attr('src','authCode.do?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
}

/*评论查询*/
function commentSelect(data) {
    var content_id = 0;
    if(data == 1) {
        content_id = blob.content_id0;
    } else {
        content_id = blob.content_id1;
    }

    var titleByTwoUrl = defaultWebUrl + "user/commentSelect"
    $.ajax({
        type:"GET",
        url: titleByTwoUrl,
        data: {"content_id" : content_id},
        dataType: "json",
        error:function(data){
            console.log(data);
        },
        success:function(data){
            var valueData;
            var count = 0;
            console.log(data.length);
        }
    });
}

/*插入评论*/
function commentInsert() {
    console.log("user_id:", blob.user_id);
    console.log("content_id:", blob.content_id);

}