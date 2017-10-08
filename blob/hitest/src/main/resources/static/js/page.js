/**
 * 必须要指定分页ul的id,且要实例化对象，假设id为 userPage,则需要如此实例化
 * userPage= fm_page({  //实例化对象与id一致
				id : 'userPage',//ul元素id
				pageNo : 0,
				pageSize : 10,
				pageCount : 100,
				pageChange : function(pageNo) {
						//分页改变函数 传入当前页
				}
			});
 * @param cnf
 * @returns
 */
function fm_page(cnf) {
	var fmpage = {
		id:'page',
		pageNo : 0,// 当前页
		showPageNum : 5,// 分页元素长度
		pageNum:0,// 总页数
		choseClass:'am-active',//选中样式
		pageCmp:null,
		start:0,
		init : function() {  //遍历外部传进来的值  it = {id: "pageBar", pageNo：0，pageSize:10， pageCount:100, showPageNum:5}
			 var it = this;
			 if (cnf) {
				 $.each(cnf, function(k, v) {
					 it[k]=v;
				 });
			 }
			this.pageNum=Math.ceil(this.pageCount/this.pageSize)-1;  //总条数除以每页条数
			this.renderPage();
		},
		renderPage:function(start) {
			 this.start = start || 0;
			 this.pageCmp=$("#"+this.id);
			 this.pageCmp.empty();
			 this.pageCmp.append('<li ><a onclick="{0}" class="">第一页</a></li>'.replace('{0}',this.id+'.toFirst()'));
			 this.pageCmp.append('<li ><a onclick="{0}" class="">上一页</a></li>'.replace('{0}',this.id+'.toPrev()'));
			 var endNum=this.pageCount/this.pageSize>(this.showPageNum+this.start)?(this.showPageNum+this.start):this.pageCount/this.pageSize;// 截止元素
			
			 for(var i=this.start;i<endNum;i++)
				 {
				    var chose = i == this.pageNo? this.choseClass : '';
				 	var li='<li class={1} ><a  onclick="{2}" class="">{0}</a></li>';
				 	this.pageCmp.append(li.replace('{0}',i+1).replace('{1}',chose).replace('{2}',this.id+'.toPage({0})'.replace('{0}',i)) );
				 }
			 
			 this.pageCmp.append('<li ><a onclick="{0}"  class="">下一页</a></li>'.replace('{0}',this.id+'.toNext()'));
			 this.pageCmp.append('<li ><a onclick="{0}" class="">最末页</a></li>'.replace('{0}',this.id+'.toLast()'));
		},
		//下一页
		toNext:function(){
			this.pageNo = this.pageNo==this.pageNum?this.pageNo:this.pageNo+1;
			if(this.pageNo>=this.start+this.showPageNum&&this.pageNo<(this.pageNum)){
				this.start=this.start+this.showPageNum;
				this.renderPage(this.start);
			}
				
			if(this.pageNo<=(this.pageNum))
				this.liCls();
		},
		//上一页
		toPrev:function(){
			this.pageNo = this.pageNo==0?0:this.pageNo-1;
			if(this.pageNo<(this.start)){
				this.start=this.start-this.showPageNum;
				this.renderPage(this.start);
			}
			if(this.pageNo>=0)
				this.liCls();
		},
		//第一页
		toFirst:function(){
			this.pageNo=0;
			if(this.pageNo<(this.start)){
				this.start=0;
				this.renderPage(this.start);
			}
			this.liCls();
		},
		//最后一页
		toLast:function(){
			this.pageNo=this.pageNum;
			if(this.pageNo>=this.start + this.showPageNum){
				this.start=this.pageNum - this.showPageNum+1;
				this.renderPage(this.start);
			}
			this.liCls();
		},
		//直接点击某一页
		toPage:function(pageNo){
			this.pageNo=pageNo;
			this.liCls();
		},
		liCls : function() {
			this.pageChange(this.pageNo);
            pageJump(this.pageNo * 2, 2);
			this.pageCmp.find('li:eq(' + (this.pageNo - this.start + 2) + ')')
						.siblings('li').removeClass(this.choseClass).end()
						.addClass(this.choseClass);
		},
		pageChange:function(pageNo){
			
		}
	}
	fmpage.init();
	return fmpage;
}

function pageJump(first, lenght) {
    var articleList = defaultWebUrl + "user/articleList"
    var array ={'first': first, 'lenght': lenght};
    var trReq = new jsonReq();
    trReq.setEventListener("receive", pageJumpFun);
    trReq.send(articleList, array)
}

function pageJumpFun(data) {
	if (data.code0 != null) {
        $("#code_id01").show()
        $("#code_id01").html(data.code0);
	} else {
        $("#code_id01").hide()
	}

    $("#artile_id01").html(data.content0);
    $("#title_id01").html(data.title0);
    $("#img_01").attr('src',data.imgUrl0);
    $("#time_01").html("创建时间:" + data.crTime0)
    if (data.content1 != "" && data.content1 != undefined) {
    	if (data.code1 != null) {
            $("#code_id02").show();
            $("#code_id02").html(data.code1);
		} else {
            $("#code_id02").hide();
		}
        $("#artile_id02").html(data.content1);
        $("#title_id02").html(data.title1);
        $("#img_02").attr('src',data.imgUrl1);
        $("#time_02").html("创建时间" + data.crTime1)
    } else {
        $("#code_id02").hide();
        $("#code_id02").html("");
        $("#artile_id02").html("");
        $("#title_id02").html("");
        $("#img_02").attr('src',"");
        $("#time_02").html("")
    }
}