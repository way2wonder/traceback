<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>大通种牛场屠宰牛只列表</title>


<!-- 引入分页控件 -->
<script type="text/javascript" src="${ctx}/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/jquery/plugins/jpage/jPages.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/jquery/plugins/jpage/jPages.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/jquery/plugins/jpage/animate.css">

<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/base.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/index.css">
<script type="text/javascript">
function go()
{
	var current = 	$("#current").val();
	var total = $("#total").val();
	if(current < total)
	{
		$("div.holder").jPages(parseInt(current,10)+1);
	}
	else
	{
		refresh();
	}
	
}

function refresh()
{
	var pagesize = $("#pageNum").val();
	var href =window.location.href;
	href= href.substr(0,href.indexOf("?"));
	href =href +"?pagesize="+pagesize;
	window.location.href=href;
}



$(function(){
	$('#itemContainer tr:even').css('background-color','#efefef');
	$("div.holder").jPages({
        containerID : "itemContainer",
        perPage     : parseInt('${pagesize}'),
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
	
  
    /* on select change */
    $("#pageNum").change(function(){
      /* get new nº of items per page */
      var newPerPage = parseInt( $(this).val() );

      /* destroy jPages and initiate plugin again */
      $("div.holder").jPages("destroy").jPages({
        containerID   : "itemContainer",
        perPage       :  newPerPage,
        first       : "首页",
        previous    : "上一页",
        next        : "下一页",
        last        : "末页",
      });
    });
	
   
  setInterval("go()",6000);
  setInterval("refresh()",60000);
});
</script>

<style type="text/css">
.red {color:red}


</style>
</head>
<body>

<div class="bodybg">
<input type="hidden" id="current"></input>
<input type="hidden" id="total"></input>
<h1>大通种牛场屠宰牛只列表</h1>
	<table id="list" border="0" cellspacing="0" cellpadding="0">
		<tr>
		    <th>序号</th>
			<th>牛号</th>
			<th>进入屠宰时间</th>
			<th>腿肉包装时间</th>
			<th>带骨肉包装时间</th>

		</tr>
<tbody  id="itemContainer">
		<c:forEach var="display" items="${recordList}" varStatus="t">
			<tr>
			<td>${t.index+1}</td>
				<td><img src="${ctx}/resources/images/cattle.png"/>${display.nh}</td>
				<td class="${display.mostRecent eq display.killTime?'red':''}"><fmt:formatDate value="${display.killTime}"
						pattern="MM-dd HH:mm" /></td>
				<td class="${display.mostRecent eq display.legPackTime?'red':''}"><fmt:formatDate value="${display.legPackTime}"
						pattern="MM-dd HH:mm" /></td>
				<td class="${display.mostRecent eq display.bonePackTime?'red':''}"><fmt:formatDate value="${display.bonePackTime}"
						pattern="MM-dd HH:mm" /></td>
			</tr>
		</c:forEach>
</tbody>
	</table>
	<form style="float:right;margin-top:15px; margin-left:15px; font-size:12px">
        <label>每页显示: </label>
        <select id="pageNum">
        <option value="5"   ${pagesize eq 5   ? 'selected':''}>5</option>	
        <option value="10"  ${pagesize eq 10  ? 'selected':''}>10</option>	
        <option value="15"  ${pagesize eq 15  ? 'selected':''}>15</option>
        </select>
         <label>条</label>
      </form>
	<div class="holder" style="float:right"></div>
	
</div>
</body>
</html>