<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Diary" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 削除完了画面</title>
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

    <!-- ヘッダー共通化 -->
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>

    <!-- メインコンテンツ -->
    <div class="form-area">
      <h2 class="login-title">Bye Cheese</h2>
      <p class="confirm-msg">Cheese Diaryを削除しました🧀</p>

      <div class="button-area">
	<form action="MyCheese" method="get">
	        <button type="submit" name="mycheese" value="My Cheese一覧へ class="nav_btn">My Cheese一覧へ</button>
        </form>
      </div>
    </div>

    <!-- フッター共通化 -->
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>

  </div>
</div>
</body>
</html>