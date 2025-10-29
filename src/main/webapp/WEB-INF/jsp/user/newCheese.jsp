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

<!-- 共通CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<!-- Googleフォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">
</head>


<body>
<div class="outerWrapper">
  <div class="login-card">

    <!-- ヘッダー -->
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>

    <!-- メインフォーム -->
    <div class="form-area">
      <h2 class="login-title">New Cheese</h2>
      <p class="confirm-msg">新しいCheese Diaryを作成チュウ🐭</p>

	
<!-- エラーメッセージ表示 -->
<c:if test="${not empty errorMessage}">
    <div style="color:red;">
        <c:out value="${errorMessage}" escapeXml="false"/>
    </div>
</c:if>
	
<form action="NewCheese" method="post" enctype="multipart/form-data">	
	
	<div class="form-row">
          <label>店名：</label>
          <input type="text" name="name" value="${fn:escapeXml(sessionScope.diary.name)}">
        </div>

    <div class="form-row">
          <label>記念年：</label>
    <select name="memorial_year" id="memorial_year">
        <option value="">分からない</option>
        <c:forEach var="year" begin="2015" end="2027">
            <c:if test="${year <= currentYear}">
                <option value="${year}" 
                    <c:if test="${sessionScope.diary.period_year == year}">selected</c:if>>${year}</option>
            </c:if>
        </c:forEach>
    </select>
       </div>
    
     <div class="form-row">
          <label>記念月：</label>
    <select name="memorial_month" id="memorial_month">
        <option value="">分からない</option>
        <c:forEach var="month" begin="1" end="12">
            <c:set var="monthStr" value="${month < 10 ? '0' + month : month}" />
            <c:if test="${sessionScope.diary.period_year lt currentYear || 
                         (sessionScope.diary.period_year == currentYear && month <= currentMonth) || 
                         sessionScope.diary.period_year == null}">
                <option value="${monthStr}" 
                    <c:if test="${sessionScope.diary.period_month == monthStr}">selected</c:if>>${month}</option>
            </c:if>
        </c:forEach>
    </select>
        </div>

      <div class="form-row">
    <label for="area_id">場所：</label>
<select name="area_id">
  <option value="">選択しない</option>
  <c:forEach var="area" items="${areaList}">
    <option value="${area.id}" <c:if test="${sessionScope.diary.area_id == area.id}">selected</c:if>>
        ${area.area_name}
    </option>
  </c:forEach>
</select>
    </div>

<c:if test="${empty areaList}">
  <p>エリアリストが空、または null です</p>
</c:if>

      <div class="diary-area">
    <label for="review">Diary：</label><br>
    <textarea name="review" rows="5" cols="33" maxlength="1000">${fn:escapeXml(sessionScope.diary.review)}</textarea><br>
       </div>
       
       
        <div class="image-area">
    <label for="file_name">画像：</label>
    <c:choose>
  <c:when test="${not empty sessionScope.diary.file_name}">
    <img id="preview" src="upload/${fn:escapeXml(sessionScope.diary.file_name)}" alt=""><br>
  </c:when>
  <c:otherwise>
    <img id="preview" src="images/no_image.png" alt=""><br>
  </c:otherwise>
</c:choose>
  <input type="file" name="file_name" id="image">
        </div>
 </div>

    <div class="button-area-vertical2">
    <button type="submit" name="step" value="確認">確認</button>
          
        </div>
      </form>

      <div class="button-area-vertical2">
		<form action="Login" method="post">
	<button type="submit" name="mypage" value="My Pageへ">My Pageへ</button>
	</form>
 </div>

   

    <!-- フッター -->
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>

  
</div>
</body>
</html>