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
</head>
<body>
<!--ヘッダー-->
<h2>Change Cheese</h2>
<main class="wrapper">
		<p class="msg_info">このCheese Diaryを変更しますか？🐭</p>
		<p>
          店名：${fn:escapeXml(sessionScope.tentative.name)}<br>
          記念年：${fn:escapeXml(memorialYearDisplay)}<br>
          記念月：${fn:escapeXml(memorialMonthDisplay)}<br>
          場所：${fn:escapeXml(areaName)}<br>
          レビュー：<br>${fn:escapeXml(sessionScope.tentative.review)}<br>
          添付ファイル: ${sessionScope.tentative['file_name'] == null ? "null" : sessionScope.tentative['file_name']}<br>

</p>

		<form action="ChangeCheese" method="post" enctype="multipart/form-data">
    <button type="submit" name="steps" value="変更登録" class="confirm_btn">変更登録</button>
    <button type="submit" name="steps" value="戻る" class="back_btn">戻る</button>

</form>
</main>
</body>
</html>