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
	src="${ctx}/jquery/plugins/pagination/kkpager.js"></script>

<link rel="stylesheet" type="text/css"
	href="${ctx}/jquery/plugins/pagination/kkpager_blue.css">
<script type="text/javascript">
function  refresh()
{
   var current = $("#currentPageno").val();
   var total = $("#totalPages").val();
   if(current == total)
   {
	    $("#currentPageno").val(1);
		$("#hiddenForm").submit();  
   }
   else
   {
	   var no = parseInt(current)+1;
	   $("#currentPageno").val(no);
	   $("#hiddenForm").submit();
   }
}


function  reBorn()
{
	$("#currentPageno").val(1);
	$("#hiddenForm").submit();
}

$(function(){
	
	$("#perPage").change(function()
	{
		var newPerPage = parseInt($(this).val());
		$("#eachPageRows").val(newPerPage);
		$("#currentPageno").val(1);
		$("#hiddenForm").submit();
	})
	
	setInterval("refresh()",5000);
	setInterval("reBorn()",60000);
	
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : '${pager.currentPageno}',
		//总页码
		total : '${pager.totalPages}',
		//总数据条数
		totalRecords : '${pager.totalRows}',
		mode : 'click',//默认值是link，可选link或者click
		click : function(n){
			
			
			//document.location.href= '${ctx}'+"/scrollInit?pager.currentPageno="+n;
			// do something
			//手动选中按钮
			this.selectPage(n);
			$("#currentPageno").val(n);
			$("#hiddenForm").submit();
			//console.log(kkpager.total);
			//console.log(kkpager.pno);
		}	
	});
});
</script>
</head>
<body>
<form id="hiddenForm" action="${ctx}/scrollInit"  method="post">
<input type="hidden" id="currentPageno"  name="currentPageno"  value="${pager.currentPageno}">
<input type="hidden" id="totalRows"  name="totalRows"  value="${pager.totalRows}">
<input type="hidden" id="eachPageRows"  name="eachPageRows" value="${pager.eachPageRows}">
<input type="hidden" id="totalPages"  name="totalPages" value="${pager.totalPages}">
</form>

	<table>
		<tr>
			<th>牛号</th>
			<th>操作</th>
			<th>操作时间</th>

		</tr>

		<c:forEach var="display" items="${actionDispalyList}">
			<tr>
				<td>${display.nh}</td>
				<td>${display.actionName}</td>
				<td><fmt:formatDate value="${display.actionTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>

	</table>
	<div id="kkpager"  style="width:600px;float:left"></div>
    
    <div>
    
	<label>每页显示:</label> 
	<select id="perPage">
    <option value="5"   ${pager.eachPageRows eq 5   ? 'selected':''}>5</option>	
    <option value="10"  ${pager.eachPageRows eq 10  ? 'selected':''}>10</option>	
    <option value="15"  ${pager.eachPageRows eq 15  ? 'selected':''}>15</option>	
	</select><label>条记录</label>
	</div>

</body>
</html>