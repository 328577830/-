<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <title>SSMO Demo</title>
    <script src="${pageContext.request.contextPath }/resoures/js/jquery-2.2.2.min.js"></script>
	<script type="text/javascript">
	//加载完页面自动设置 tr 标签的颜色
	$(function() {
		$("tr").filter(":odd").css("background-color", "skyblue");
		$("tr").filter(":even").css("background-color", "white");
	});
	</script>
  </head>
  
  <body>
    <h1>SpringMVC - Spring - Mybatis - Oracle</h1>
    <img alt="qq" src="${pageContext.request.contextPath }/resoures/images/box_art.jpg">
     <form action="carController_find" method="post" name="f">
    	<input type="text" name="name" placeholder="车名">
    	<input type="date" name="beginDate">
    	<input type="date" name="endDate">
    	<input type="submit" value="查询">
    </form> 
    <table border="1">
    	<tr>
    		<th>ID</th>
    		<th>Name</th>
    		<th>Sale Date</th>
    		<th>Price</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach var="car" items="${requestScope.cars }">
    		<tr>
    			<td>${car.id }</td>
    			<td>${car.name }</td>
    			<td><fmt:formatDate value="${car.saleDate }" pattern="yyyy-MM-dd"/></td>
    			<td>${car.price }</td>
    			<td><a href="carController_findById?id=${car.id }">Modify</a> &nbsp;&nbsp;
    			<a onclick="return confirm('是否query删除${car.name }?')" href="carController_remove?id=${car.id }">Remove</a></td>
    		</tr>
    	</c:forEach>
    </table>
    
    <a href="caredit.jsp">add a Car</a>
  </body>
</html>
