<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Diary" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Diary diary = (Diary) request.getAttribute("tentative");
    if (diary == null) {
%>
    <p>データが見つかりません。</p>
    <a href="MyCheese">戻る</a>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee changeCheese画面</title>
</head>
<body>
	<h2>Change Cheese</h2>
	
<!-- エラーメッセージ表示 -->
<c:if test="${not empty errorMessage}">
    <div style="color:red;">
        <c:out value="${errorMessage}" escapeXml="false"/>
    </div>
</c:if>

<form action="ChangeCheese" method="post" enctype="multipart/form-data">	
	  <p class="msg_info">Cheese Diaryを変更チュウ🐭</p>
		 <label for="name">店名：</label>
    <input type="text" name="name" value="${fn:escapeXml(sessionScope.tentative.name)}"><br>

    <label for="memorial_year">記念年：</label>
    <select name="memorial_year" id="memorial_year">
        <option value="">分からない</option>
        <c:forEach var="year" begin="2015" end="2027">
            <c:if test="${year <= currentYear}">
                <option value="${year}" 
                    <c:if test="${sessionScope.tentative.memorial_year == year}">selected</c:if>>${year}</option>
            </c:if>
        </c:forEach>
    </select><br>

    <label for="memorial_month">記念月：</label>
    <select name="memorial_month" id="memorial_month">
        <option value="">分からない</option>
        <c:forEach var="month" begin="1" end="12">
            <c:set var="monthStr" value="${month < 10 ? '0' + month : month}" />
            <c:if test="${sessionScope.tentative.memorial_year lt currentYear || 
                         (sessionScope.tentative.memorial_year == currentYear && month <= currentMonth) || 
                         sessionScope.tentative.memorial_year == null}">
                <option value="${monthStr}" 
                    <c:if test="${sessionScope.tentative.memorial_month == monthStr}">selected</c:if>>${month}</option>
            </c:if>
        </c:forEach>
    </select><br>

    <label for="area_id">場所：</label>
    <select name="area_id" id="area_id">
        <c:forEach var="area" items="${areaList}">
            <option value="${area.area_id}" 
                <c:if test="${sessionScope.tentative.area_id == area.area_id}">selected</c:if>>
                <c:out value="${area.area_name}" />
            </option>
        </c:forEach>
    </select><br>

    <label for="review">Diary：</label>
    <textarea name="review" rows="5" cols="33" maxlength="1000">${fn:escapeXml(sessionScope.tentative.review)}</textarea><br>

    <label for="img_name">画像：</label>
    <img id="preview" src="<c:out value='${sessionScope.diary.img_name != null ? "upload/" + sessionScope.tentative.img_name : "images/no_image.png"}'/>" alt=""><br>
    <input type="file" name="img_name" id="image"><br>

    <input type="submit" name="action" value="確認">
	
	</form>
		<button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>戻る</button>
</body>
</html>