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
    <input type="text" name="name" value="${fn:escapeXml(sessionScope.diary.name)}"><br>

    <label for="memorial_year">記念年：</label>
    <select name="memorial_year" id="memorial_year">
        <option value="">分からない</option>
        <c:forEach var="year" begin="2015" end="2027">
            <c:if test="${year <= currentYear}">
                <option value="${year}" 
                    <c:if test="${sessionScope.diary.memorial_year == year}">selected</c:if>>${year}</option>
            </c:if>
        </c:forEach>
    </select><br>

    <label for="memorial_month">記念月：</label>
    <select name="memorial_month" id="memorial_month">
        <option value="">分からない</option>
        <c:forEach var="month" begin="1" end="12">
            <c:set var="monthStr" value="${month < 10 ? '0' + month : month}" />
            <c:if test="${sessionScope.diary.memorial_year lt currentYear || 
                         (sessionScope.diary.memorial_year == currentYear && month <= currentMonth) || 
                         sessionScope.diary.memorial_year == null}">
                <option value="${monthStr}" 
                    <c:if test="${sessionScope.diary.memorial_month == monthStr}">selected</c:if>>${month}</option>
            </c:if>
        </c:forEach>
    </select><br>

    <label for="area_id">場所：</label>
<select name="area_id">
  <option value="">選択しない</option>
  <c:forEach var="area" items="${areaList}">
    <option value="${area.id}" <c:if test="${param.area_id == area.id}">selected</c:if>>
        ${area.area_name}
    </option>
  </c:forEach>
</select><br>

<c:if test="${empty areaList}">
  <p>エリアリストが空、または null です</p>
</c:if>

    <label for="review">Diary：</label><br>
    <textarea name="review" rows="5" cols="33" maxlength="1000">${fn:escapeXml(sessionScope.diary.review)}</textarea><br>

    <label for="file_name">画像：</label>
    <img id="preview" src="<c:out value='${sessionScope.diary.file_name != null ? "upload/" + sessionScope.diary.file_name : "images/no_image.png"}'/>" alt=""><br>
    <input type="file" name="file_name" id="image"><br>

    <input type="submit" value="確認">
	
	</form>
		<button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>My Pageへ</button>
</main>	
</body>
</html>