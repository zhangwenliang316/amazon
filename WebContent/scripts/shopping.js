function deleteCart(cid) {
	$.ajax({
		url:"doDeleteCart",
		type:"POST",
		data:"cid="+cid,
		success:function(msg){
			if(msg==1){
				alert("删除成功");
				//需要将自身这条记录删除
				$("#product_"+cid).remove();
			}else{
				alert("删除失败");
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}



function alterCount(cid) {
//	window.location.href="alterQuantity?"+cid+"_"+$("#"+cid).val()
	$.ajax({
		url : "doChangeCartCount",// 请求的servlet地址
		type : "POST",// 请求方式
		data : "cid="+cid+"&count="+$("#"+cid).val(),// 发送到服务器的数据
		dataType : "text",// 设置返回数据类型
		success : function(msg) {
			if(msg==1){
			}else{
			}
			//$("#cartCount").html(total);
		},// 响应成功后执行的回调方法data响应文本
		complete : function(XMLHttpRequest, statusText) {

		},// 响应完成后执行的回调方法
		error : function(XMLHttpRequest, statusText) {
			alert("操作失败!")
		}// 响应失败后执行的回调方法
	})
}


//-按钮事件
function reduce(id){
	
	if($("#"+id).val()==1){
		$("#"+id).val(1)
	}else if($("#"+id).val()>=2){
		//获取原先值
		var old=$("#"+id).val()
		$("#"+id).val(parseInt(old)-1)
	}else{
		alert("您选择的数量不能为负数!")
	}
	alterCount(id)
}

//+按钮事件
function increase(id){
	
	var stock=$("#hpStock"+id).val()//获得库存
	var old=$("#"+id).val()//获得原来的数量	
	if(parseInt(old)<parseInt(stock)){
		$("#"+id).val(parseInt(old)+1)
		alterCount(id)
	}else if(parseInt(old)<=0){
		alert("您选择的数量不能为负数!")
		location.href ="doShowCart";
	}else{
		alert("您选择的数量超过库存!")
		location.href ="doShowCart";
	}
	
}



function checkStock(id){
	var stock=$("#hpStock"+id).val()//获得库存
	var old=$("#"+id).val()//获得原来的数量
	var old1 = /^[0-9]{1,5}$/;
	if(old==""||old1.test(old)==false){
		alert("不能存在空格或输入字母或小于0的数字")
		$("#"+id).val(1)
	}else if(parseInt(old)>parseInt(stock)){
		alert("您选择的数量超过库存!")
		$("#"+id).val(1)
		location.href ="doShowCart";
	}else if(parseInt(old)<=0){
		alert("您选择的数量不能为负数!")
		location.href ="doShowCart";
	}else{
		alterCount(id)
	}
}




function buttonTest(id) {
	alert("测试id="+id)
}











