function addToCart(pid) {
	
	var stock=$("#stock").text()
	var count=$("#count").val()
	
	if(stock == null || ""== stock){
		alert("无货")
	}else if(parseInt(count)>parseInt(stock)){
		alert("您选择的数量超过库存!")
	}else{
		$.ajax({
			url:"doAddCart?pid="+ pid+"&count="+ count,// 请求的servlet地址
			type : "POST",// 请求方式
			dataType : "text",// 设置返回数据类型
			success : function(total) {
				if(total==1){
					alert("成功添加到购物车!");
				}else{
					alert("添加到购物车失败!");
				}
			},// 响应成功后执行的回调方法data响应文本
			complete : function(XMLHttpRequest, statusText) {
				
			},// 响应完成后执行的回调方法
			error : function(XMLHttpRequest, statusText) {
				alert("无法添加到购物车!");
			}// 响应失败后执行的回调方法
		})
	}

}

function goingToBuy(pid) {
	var stock=$("#stock").text()
	if(stock == null || ""== stock){
		alert("无货")
	}else{
		window.location.href="goingToBuy?pid="+pid+"&count="+$("#count").val();	
	}
	
}

//-按钮事件
function minus(){

	if($("#count").val()==1){
		$("#count").val(1)
	}else if($("#count").val()>=2){
		var old=$("#count").val()
		$("#count").val(parseInt(old)-1)
	}
}

//+按钮事件
function add(){
	var stock=$("#stock").html()
	var old=$("#count").val()
	if(parseInt(old)<parseInt(stock)){
		$("#count").val(parseInt(old)+1)
	}else if(parseInt(old)<=0){
		alert("您选择的数量不能为负数或零!")
	}
	else{
		alert("您选择的数量超过库存!")
	}
	
}



function checkStock(){
	var stock=$("#stock").html()
	var old=$("#count").val()
	var old1 = /^[0-9]{1,5}$/; 
	if(old==""||old1.test(old)==false){
		alert("不能存在空格或输入字母或小于0的数字")
		$("#count").val(1)
	}else if(parseInt(old)>parseInt(stock)){
		alert("您选择的数量超过库存!")
		$("#count").val(1)
	}else if(parseInt(old)<=0){
		alert("您选择的数量不能为负数或零!")
		$("#count").val(1)
	}else if(parseInt(old).equal(old1)){
		
	}
}



function remaind() {
	alert("请先登录亚马逊！")
	window.location.href="login.jsp"
}




