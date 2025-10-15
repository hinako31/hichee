<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.time.LocalDate" %>

<%
    LocalDate now = LocalDate.now();
    request.setAttribute("currentYear", now.getYear());
    request.setAttribute("currentMonth", now.getMonthValue());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 新規Diary入力画面</title>
</head>
<body>
<!--ヘッダー-->
<main class="wrapper">
	<h2>New Cheese</h2>
	
<!-- エラーメッセージ表示 -->
<c:if test="${not empty errorMessage}">
    <div style="color:red;">
        <c:out value="${errorMessage}" escapeXml="false"/>
    </div>
</c:if>
	
<form action="NewCheese" method="post" enctype="multipart/form-data">	
	  <p class="msg_info">新しいCheese Diaryを作成チュウ🐭</p>
		<label for="name">店名：</label>
       <input type="text" name="name" value="${fn:escapeXml(sessionScope.diary != null ? sessionScope.diary.name : '')}"><br>

		
		 <label for="memorial_year">記念年：</label>
         <select name="memorial_year" id="memorial_year">
         <option value="">分からない</option>
         <c:forEach var="i" begin="2015" end="2027">
         <c:if test="${i <= currentYear}">
        <option value="${i}" <c:if test="${sessionScope.diary != null && sessionScope.diary.memorial_year == i}">selected</c:if>>${i}</option>
         </c:if>
         </c:forEach>
         </select><br>

        
		 <label for="memorial_month">記念月：</label>
         <select name="memorial_month" id="memorial_month">
         <option value="">分からない</option>
         <c:forEach var="i" begin="1" end="12">
         <c:set var="monthStr" value="${i < 10 ? '0' + i : i}" />
         <c:if test="${sessionScope.diary.memorial_year lt currentYear || (sessionScope.diary.memorial_year == currentYear && i <= currentMonth) || sessionScope.diary.memorial_year == null}">
            <option value="${monthStr}" <c:if test="${sessionScope.diary != null && sessionScope.diary.memorial_month == monthStr}">selected</c:if>>${i}</option>
          </c:if>
         </c:forEach>
         </select><br>

         <label for="area_id">場所：</label>
         <select name="area_id" id="area_id">
         <c:forEach var="area" items="${areaList}">
          <option value="${area.area_id}" <c:if test="${sessionScope.diary!= null && sessionScope.diary.area_id == area.area_id}">selected</c:if>>
         <c:out value="${area.area_name}" />
         </option>
         </c:forEach>
         </select><br>
        
      	<label for="review">Diary：</label>
		 <textarea name="review" rows="5" cols="33" maxlength="1000">${fn:escapeXml(sessionScope.diary != null ? sessionScope.diary.review : '')}</textarea><br>
		 
	<label for="img_name">画像：</label>
		<img id="preview" src="images/no_image.png" alt="">
		<input type="file" name="img_name" id="image"><br>
	 
	 <input type="submit" value="確認">
	
	</form>
		<button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>My Pageへ</button>
</main>	
</body>
</html>