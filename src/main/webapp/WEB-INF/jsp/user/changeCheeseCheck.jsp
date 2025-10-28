<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ChangeCheese確認画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">
</head>
<body>
<div class="outerWrapper">
  <div class="login-card">
 
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>
    
  <div class="form-area">
      <h2 class="login-title">Change Cheese</h2>
		 <p class="confirm-msg">このCheese Diaryを変更しますか？🐭</p>

      <div class="form-row">
        <label>店名：</label>
        <strong> ${fn:escapeXml(sessionScope.tentative.name)}<br>
           </div>
          <div class="form-row">
        <label>記念年：</label>
        <strong>${fn:escapeXml(memorialYearDisplay)}<br>
          </div>
          <div class="form-row">
        <label>記念月：</label>
        <strong>${fn:escapeXml(memorialMonthDisplay)}<br>
         </div>
          <div class="form-row">
        <label>場所：</label>
        <strong>${fn:escapeXml(areaName)}<br>
        </div>
           <div class="form-row">
        <label>レビュー：</label>
        <strong>${fn:escapeXml(sessionScope.tentative.review)}<br>
          </div>
           <div class="form-row">
        <label>添付ファイル：</label>
        ${sessionScope.tentative['file_name'] == null ? "null" : sessionScope.tentative['file_name']}<br>
          </div>

   <div class="button-area">
		<form action="ChangeCheese" method="post" enctype="multipart/form-data">
    <button type="submit" name="steps" value="変更登録" class="confirm_btn">変更登録</button>
    <button type="submit" name="steps" value="戻る" class="back_btn">戻る</button>
</form>
 </div>
</div>
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
  </div>
</div>

</body>
</html>