<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊- 购物车</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
<script type="text/javascript" src="scripts/shopping.js"></script>
</head>
<body>
<%@ include file="index_top.jsp"  %>

<div id="position" class="wrap">
	您现在的位置：<a href="doQuery">亚马逊</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="doBuy" method="post" id ="settle" onsubmit="return mySettle()">
			<table id="tb_1">
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				
				
				<!-- 根据用户购物车生成列表 -->
				<c:forEach items="${requestScope.cart }" var="shopping">
				<tr id="product_${shopping.id }">
					<td class="thumb"><img style="width: 100px; height: 100px;" src="${shopping.goods.hp_file_name }" />
					<a href="doView?pId=${shopping.goods.hp_id }">${shopping.goods.hp_name }</a></td>
					<td class="price" id="price_id_1">
						￥<span id="span_1">${shopping.goods.hp_price }</span>
						<input type="hidden" id="subPrice" value="${shopping.goods.hp_price*shopping.quantity }" name="sumPrice"/>
						<input type="hidden" id="subPrice" value="${shopping.goods.hp_price}" name="price"/>
						<input type="hidden"  value="${shopping.goods.hp_id }" name="pId"/>
						<input type="hidden"  value="${shopping.goods.hp_stock }" name="hpStock" id="hpStock${shopping.id }"/>
					</td>
					<td class="number">
							<c:set var="hcid" value="${shopping.id }"></c:set>
							
							<input type="button" id="minus"value=" - " width="3px" onclick=" reduce(${hcid })" name="minusButton">
							<input id="${hcid }" type="text" name="number" value="${shopping.quantity }" maxlength="5"
							size="1"  style="text-align:center; vertical-align:middle" onblur="checkStock(${hcid })"/>							
							<input type="button" id="add" value=" + " width="2px" onclick=" increase(${hcid })" name="addButton">		
							
						
					</td>
					<td class="delete"><a href="javascript:deleteCart(${shopping.id})">删除</a></td>
				</tr>
				</c:forEach>
			</table>
			<div class="button"><input type="submit" value=""/></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2016 上海海文 All Rights Reserved.
</div>
<script type="text/javascript">
	function mySettle(){
		var flag = false;
		$.ajax({
			type:"post",
			url:"doCartNoEmpty",
			async:false,
			success:function(msg){
				if(msg==0){
					alert("购物车不能为空");
					flag=false;
				}else if(msg==1){
					alert("结算产生订单");
					flag=true;				
				}
			}
		})
		return flag;
	}
	
</script>
</body>
</html>

