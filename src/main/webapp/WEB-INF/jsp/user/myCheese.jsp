<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee myCheese検索画面</title>

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
      <h2 class="login-title">My Cheese</h2>
      <p class="confirm-msg">Cheese Diaryを検索できます🧀</p>

     
        
        <div class="form-row">
          <label>店名：</label>
    <input type="text" name="name" value="${param.name != null ? param.name : ''}" /><br><br>
  </div>
  
   <div class="form-row">
    <label>記念年: </label>
    <select name="period_year">
        <option value="">選択しない</option>
        <c:forEach var="year" begin="2015" end="2027">
            <option value="${year}" <c:if test="${param.period_year == year.toString()}">selected</c:if>>${year}</option>
        </c:forEach>
        <option value="unknown" <c:if test="${param.period_year == 'unknown'}">selected</c:if>>分からない</option>
    </select>   
    </div>
    
     <div class="form-row">
    <label>記念月: </label>
    <select name="period_month">
        <option value="">選択しない</option>
        <c:forEach var="month" begin="1" end="12">
            <option value="${month}" <c:if test="${param.period_month == month.toString()}">selected</c:if>>${month}月</option>
        </c:forEach>
        <option value="unknown" <c:if test="${param.period_month == 'unknown'}">selected</c:if>>分からない</option>
    </select>
     </div>

        <div class="form-row">
    <label>場所: </label>
    <select name="area_id">
        <option value="">選択しない</option>
        <c:forEach var="area" items="${areaList}">
            <option value="${area.id}" <c:if test="${param.area_id == area.id.toString()}">selected</c:if>>${area.area_name}</option>
        </c:forEach>
        <option value="unknown" <c:if test="${param.area_id == 'unknown'}">selected</c:if>>分からない</option>
    </select>
    </div>
    
</div> <!-- form-area -->

    <div class="button-area-vertical2">
     <form action="MyCheese" method="post">
    <button type="submit" name="action" value="検索">検索</button>
</form>
</div>

    <div class="button-area-vertical2">
<form action="Login" method="post">
<button type="submit" name="mypage" value="My Pageへ">My Pageへ</button>
</form>
 </div>

    <!-- フッター -->
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>

  </div> <!-- login-card -->
</div> <!-- outerWrapper -->
</body>
</html>
