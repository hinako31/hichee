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
 <!-- 共通CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
 
    <!-- Googleフォント -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">
 
    <style>
        .detail-container { width: 80%; margin: auto; }
        .diary-img { width: 300px; height: auto; }
        .btn { margin: 10px; }
    </style>
</head>
<body>
<div class="outerWrapper">
      <div class="login-card">

   
    <!-- ヘッダー -->
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>

    <div class="form-area">
      <h2 class="login-title">My Cheese</h2>
       

       <div class="form-row">
        <label>店名：</label>
         <strong><%= diary.getName() %></strong>
    </div>
    
        <div class="form-row">
        <label>記念年：</label>
        <strong><%= diary.getPeriod_year() != null ? diary.getPeriod_year() : "不明" %></strong>
        </div>
        
        <div class="form-row">
        <label>記念月：</label>
        <strong><%= diary.getPeriod_month() != null ? diary.getPeriod_month() : "不明" %></strong>
        </div>
        
        <div class="form-row">
        <label>場所：</label>
        <strong><%= request.getAttribute("areaName") != null ? request.getAttribute("areaName") : "不明" %></strong>
        </div>
        
        <div class="diary-area">
        <label>Diary：</label>
        <div class="confirm-diary"><%= diary.getReview() %></div>
         </div>
         
         <div class="form-row image-area">
        <label>画像：</label>
            <% if (diary.getFile_path() != null && !diary.getFile_path().isEmpty()) { %>
                <img src="<%= diary.getFile_path() %>" alt="画像" class="diary-img">
            <% } else { %>
                画像なし
            <% } %>
      </div>
    </div>
   
    
     <div class="button-area-vertical2">
        <!-- ボタンでPOSTに送る -->
        <form action="ChangeCheese" method="post" style="display:inline;">
            <input type="hidden" name="action" value="editForm">
            <input type="hidden" name="id" value="<%= diary.getId() %>">
             <button type="submit" class="btn confirm_btn">Change Cheese</button>
        </form>
       
        <form action="ByeCheese" method="post" style="display:inline;">
            <input type="hidden" name="action" value="deleteConfirm">
            <input type="hidden" name="id" value="<%= diary.getId() %>">
            <button type="submit" class="btn back_btn">Bye Cheese</button>
        </form>
      
      <form action="MyCheese" method="get" style="display:inline;">
      <button type="submit" class="btn back_btn">戻る</button>
      </form>
      </div>    
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
</div>
</div>
</body>
</html>
