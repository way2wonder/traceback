<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>操作记录</title>


<!-- 引入分页控件 -->
<script type="text/javascript" src="${ctx}/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/jquery/plugins/jpage/jPages.js"></script>

<link rel="stylesheet" type="text/css"
	href="${ctx}/jquery/plugins/jpage/jPages.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/jquery/plugins/jpage/animate.css">
<script type="text/javascript">
function go()
{
	var current = 	$("#current").val();
	var total = $("#total").val();
	if(current < total)
	{
		$("div.holder").jPages(parseInt(current,10)+1);
	}
	
}

$(function(){
	$("div.holder").jPages({
        containerID : "itemContainer",
        perPage     : 5,
        first       : "首页",
        previous    : "上一页",
        next        : "下一页",
        last        : "末页",
        startPage : 1,
        midRange :5,
        keyBrowse :true,
        scrollBrowse :true,
        callback:function(pages,items)
        {
        	$("#current").val(pages.current);
        	$("#total").val(pages.count);
        }
  });
   
  setInterval("go()",6000);
});
</script>
</head>
<body>
<input type="hidden" id="current"></input>
<input type="hidden" id="total"></input>
	<table>
		<tr>
			<th>牛号</th>
			<th>操作</th>
			<th>操作时间</th>

		</tr>
<tbody  id="itemContainer">
		<c:forEach var="display" items="${actionDispalyList}">
			<tr>
				<td>${display.nh}</td>
				<td>${display.actionName}</td>
				<td><fmt:formatDate value="${display.actionTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>
</tbody>
	</table>
	<div class="holder" style="float:left"></div>
	
	<form style="float:left;margin-top:15px; margin-left:15px; font-size:12px">
        <label>每页: </label>
        <select>
          <option>5</option>
          <option>10</option>
          <option>15</option>
        </select>
         <label>条记录</label>
      </form>

</body>
</html>