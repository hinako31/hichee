<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee NewCheese確認画面</title>
<style>
    body { font-family: sans-serif; margin: 40px; }
    .wrapper { max-width: 600px; margin: auto; }
    .msg_info { font-weight: bold; color: #555; margin-bottom: 20px; }
    .confirm_btn, .back_btn {
        margin: 10px;
        padding: 8px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .confirm_btn { background-color: #ffcc66; }
    .back_btn { background-color: #ddd; }
    img { margin-top: 10px; max-width: 200px; border-radius: 5px; }
</style>

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

    <!-- メイン内容 -->
    <div class="form-area">
      <h2 class="login-title">New Cheese</h2>
      <p class="confirm-msg">このCheese Diaryを作成しますか？🐭</p>

      <div class="form-row">
        <label>店名：</label>
         <strong>${fn:escapeXml(sessionScope.diary.name)}</strong>
      </div>
      
       <div class="form-row">
        <label>記念年：</label>
        <strong>${fn:escapeXml(memorialYearDisplay)}</strong>
      </div>
         
          <div class="form-row">
        <label>記念月：</label>
        <strong>${fn:escapeXml(memorialMonthDisplay)}</strong>
      </div>
      
       <div class="form-row">
        <label>場所：</label>
        <strong>${fn:escapeXml(areaName)}</strong>
      </div>
      
      <div class="diary-area">
        <label>Diary：</label>
        <div class="confirm-diary">${fn:escapeXml(sessionScope.diary.review)}</div>
        </div>
      
           <div class="image-area">
        <label>画像：</label>
        ${sessionScope.diary.file_name == null ? "なし" : sessionScope.diary.file_name}<br>
 <!-- 画像プレビュー -->
        <c:if test="${not empty sessionScope.diary.file_path}">
            <img src="${pageContext.request.contextPath}/${sessionScope.diary.file_path}" alt="画像プレビュー">
        </c:if>
     </div>
    </div>
  
<div class="button-area-vertical2">
<form action="NewCheese" method="post">
      <button type="submit" name="step" value="作成">作成</button>
       </form> </div> 
<div class="button-area-vertical2">
<form action="NewCheese" method="post">
<button type="submit" name="step" value="戻る">戻る</button>
   </form> </div>
       
   

    <!-- フッター -->
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>

  </div>
</div>
</body>
</html>