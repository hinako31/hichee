<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Diary" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Diary diary = (Diary) request.getAttribute("diary");
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
    <title>hichee MyCheese詳細</title>
    <style>
        .detail-container { width: 80%; margin: auto; }
        .diary-img { width: 300px; height: auto; }
        .btn { margin: 10px; }
    </style>
</head>
<body>

    <div class="detail-container">
        <h2>My Cheese</h2>

        <p><strong>店名：</strong> <%= diary.getName() %></p>

        <p>
            <strong>画像：</strong><br>
            <% if (diary.getFile_path() != null && !diary.getFile_path().isEmpty()) { %>
                <img src="<%= diary.getFile_path() %>" alt="画像" class="diary-img">
            <% } else { %>
                画像なし
            <% } %>
        </p>

        <p><strong>記念年：</strong> <%= diary.getPeriod_year() != null ? diary.getPeriod_year() : "不明" %></p>
        <p><strong>記念月：</strong> <%= diary.getPeriod_month() != null ? diary.getPeriod_month() : "不明" %></p>
        <p><strong>場所：</strong> <%= diary.getArea_name() != null ? diary.getArea_name() : "不明" %></p>
        <p><strong>Diary：</strong><br><%= diary.getReview() %></p>

        <!-- ボタンでPOSTに送る -->
        <form action="ChangeCheese" method="post" style="display:inline;">
            <input type="hidden" name="action" value="editForm">
            <input type="hidden" name="id" value="<%= diary.getId() %>">
            <input type="submit" value="Change Cheese" class="btn">
        </form>

        <form action="ByeCheese" method="post" style="display:inline;">
            <input type="hidden" name="action" value="deleteConfirm">
            <input type="hidden" name="id" value="<%= diary.getId() %>">
            <input type="submit" value="Bye Cheese" class="btn">
        </form>

      <form action="MyCheese" method="get" style="display:inline;">
      <input type="submit" value="戻る" class="btn">
      </form>

    </div>

</body>
</html>
