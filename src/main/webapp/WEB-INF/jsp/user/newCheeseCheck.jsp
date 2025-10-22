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
</head>
<body>
<!--ヘッダー-->
<h2>New Cheese</h2>
<main class="wrapper">
		<p class="msg_info">このCheese Diaryを作成しますか？🐭</p>
		<p>
          店名：${fn:escapeXml(sessionScope.diary.name)}<br>
          記念年：${fn:escapeXml(sessionScope.diary.period_year)}<br>
          記念月：${fn:escapeXml(sessionScope.diary.period_month)}<br>
          場所：${fn:escapeXml(areaName)}<br>
          レビュー：${fn:escapeXml(sessionScope.diary.review)}<br>
          添付ファイル：${fn:escapeXml(sessionScope.diary.img_name)}<br>
</p>

		<form action="NewCheese" method="post">
    <button type="submit" name="step" value="作成" class="confirm_btn">作成</button>
    <button type="submit" name="step" value="戻る" class="back_btn">戻る</button>

</form>
</main>
</body>
</html>