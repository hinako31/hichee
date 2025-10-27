<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Diary" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
Diary diary = (Diary) session.getAttribute("diary");

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
<title>Insert title here</title>
<style>
    body { font-family: "Hiragino Kaku Gothic ProN", sans-serif; background-color: #fffdf8; color: #333; }
    .wrapper { width: 80%; margin: 30px auto; padding: 20px; background: #fff; border-radius: 10px; box-shadow: 0 0 8px rgba(0,0,0,0.1); }
    .msg_info { font-size: 18px; color: #d9534f; margin-bottom: 20px; font-weight: bold; }
    .confirm_btn { background-color: #d9534f; color: white; border: none; padding: 8px 16px; border-radius: 4px; margin-right: 10px; cursor: pointer; }
    .confirm_btn:hover { background-color: #c9302c; }
    .back_btn { background-color: #ccc; color: #333; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer; }
    .back_btn:hover { background-color: #bbb; }
        .detail-container { width: 80%; margin: auto; }
        .diary-img { width: 300px; height: auto; }
        .btn { margin: 10px; }
    </style>
</head>
<body>
<h2>Bye Cheese</h2>
<main class="wrapper">
		<p class="msg_info">このCheese Diaryを削除しますか？🐭</p>
		   <p>
        店名：${fn:escapeXml(sessionScope.diary.name)}<br>
        記念年：${fn:escapeXml(memorialYearDisplay)}<br>
        記念月：${fn:escapeXml(memorialMonthDisplay)}<br>
        場所：${fn:escapeXml(areaName)}<br>
        レビュー：<br>${fn:escapeXml(sessionScope.diary.review)}<br>
        添付ファイル：${sessionScope.diary['file_name'] == null ? "なし" : sessionScope.diary['file_name']}<br>
    </p>
    <form action="ByeCheese" method="post">
        <input type="hidden" name="action" value="delete">  <!-- 削除実行に使う -->
        <input type="hidden" name="id" value="${sessionScope.diary.id}">
        <button type="submit" name="steps" value="削除" class="confirm_btn">削除</button>
        </form>
        <button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>戻る</button>
		
    
</main>
</main>
</body>
</html>