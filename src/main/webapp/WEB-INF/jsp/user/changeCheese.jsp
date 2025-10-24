<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Diary" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    Diary diary = (Diary) session.getAttribute("tentative");
    if (diary == null) {
%>
    <p>データが見つかりません。</p>
    <a href="MyCheese">戻る</a>
<%
    } else {
    	String fileNameFromServer = "";
    	if (diary.getFile_name() != null && !diary.getFile_name().isEmpty()) {
        	fileNameFromServer = diary.getFile_name();
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
<input type="hidden" name="id" value="${tentative.id}">
	  <p class="msg_info">Cheese Diaryを変更チュウ🐭</p>
		 <label for="name">店名：</label>
    <input type="text" name="name" value="${fn:escapeXml(sessionScope.tentative.name)}"><br>


  <label for="memorial_year">記念年：</label>
<select name="memorial_year" id="memorial_year">
    <option value="">分からない</option>
    <c:forEach var="year" begin="2015" end="2027">
        <option value="${year}" 
            <c:if test="${periodYearStr eq year}">selected</c:if>>
            ${year}
        </option>
    </c:forEach>
</select><br>

<label for="memorial_month">記念月：</label>
<select name="memorial_month" id="memorial_month">
    <option value="">分からない</option>
    <c:forEach var="month" begin="1" end="12">
        <option value="${month}" 
            <c:if test="${periodMonthStr eq month}">selected</c:if>>
            ${month}
        </option>
    </c:forEach>
</select><br>


 <label for="area_id">場所：</label>
<select name="area_id" id="area_id">
    <option value="">選択しない</option>
    <c:if test="${not empty sessionScope.areaList}">
        <c:forEach var="area" items="${sessionScope.areaList}">
            <option value="${area.id}"
        <c:if test="${sessionScope.tentative.area_id == area.id}">selected</c:if>>
    <c:out value="${area.area_name}" />
</option>

        </c:forEach>
    </c:if>
</select>
<br>

    <label for="review">Diary：</label>
    <textarea name="review" rows="5" cols="33" maxlength="1000">${fn:escapeXml(sessionScope.tentative.review)}</textarea><br>

<style>
  #fileNameDisplay {
    font-size: 0.9em;
    margin-left: -90px;
    vertical-align: middle;
    max-width: 200px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: inline-block;
  }
  #img_name {
    vertical-align: middle;
  }
  input[type="file"] {
    color: transparent;
    width: 200px; /* 必要に応じて調整してください */
    cursor: pointer;
  }

  input[type="file"]::-webkit-file-upload-button {
    cursor: pointer;
  }
</style>

<label for="img_name">画像：</label>
<c:choose>
    <c:when test="${not empty sessionScope.tentative.file_name}">
        <img id="preview" src="upload/${sessionScope.tentative.file_name}" alt="画像" style="max-width:200px;">
    </c:when>
    <c:otherwise>
        <img id="preview" src="images/no_image.png" alt="No Image" style="max-width:200px;">
    </c:otherwise>
</c:choose>
<br>
<input type="file" name="file_name" id="img_name" accept="image/*">
<span id="fileNameDisplay">ファイルが選択されていません</span>

<script>
    window.addEventListener('DOMContentLoaded', () => {
        const fileNameDisplay = document.getElementById('fileNameDisplay');
        const fileNameFromServer = "<%= fileNameFromServer %>";
        if (fileNameFromServer) {
            fileNameDisplay.textContent = fileNameFromServer;
        } else {
            fileNameDisplay.textContent = 'ファイルが選択されていません';
        }
    });
</script>


<br>

    <input type="submit" name="action" value="確認">
	
	</form>
	  <form action="MyCheese" method="post">
                    <input type="hidden" name="action" value="detail">
                    <input type="hidden" name="id" value="<%= diary.getId() %>">
                    <button class="btn-view" type="submit">戻る</button>
                </form>
</body>
</html>

<%
}  // ← これがないとコンパイルエラーになる
%>