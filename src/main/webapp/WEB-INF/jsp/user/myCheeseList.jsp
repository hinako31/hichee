<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Diary" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.net.URLEncoder" %>

<%
    List<model.Diary> diaryList = (List<Diary>) request.getAttribute("diaryList");
    if (diaryList != null) {
        // 店名のあいうえお順（日本語のソートは通常、名前の昇順）で並べ替え
        Collections.sort(diaryList, Comparator.comparing(Diary::getName, Comparator.nullsLast(String::compareTo)));
    }
%>

    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>検索結果一覧</title>
    <style>
        body {
            font-family: 'メイリオ', sans-serif;
            margin: 30px;
        }
        .diary-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .diary-card {
            border: 1px solid #ccc;
            padding: 15px;
            width: 250px;
            text-align: center;
            background-color: #f9f9f9;
        }
        .diary-card img {
            max-width: 100%;
            height: auto;
        }
        .diary-card h3 {
            margin-top: 10px;
            font-size: 18px;
        }
        .btn-view {
            margin-top: 10px;
            padding: 8px 12px;
            background-color: #87cefa;
            border: none;
            color: white;
            cursor: pointer;
        }
        .btn-view:hover {
            background-color: #4682b4;
        }
    </style>
</head>
<body>

    <h2>My Cheese</h2>

    <%
        if (diaryList == null || diaryList.isEmpty()) {
    %>
        <p>一致する日記は見つかりませんでした。</p>
    <%
        } else {
    %>
    <p>Cheese Diaryを表示チュウ🐭</p><br>
        <div class="diary-container">
        <%
            for (Diary diary : diaryList) {
        %>
            <div class="diary-card">
               <!-- 画像は非表示にしました -->

                <h3><%= diary.getName() %></h3>

                <form action="MyCheese" method="post">
                    <input type="hidden" name="action" value="detail">
                    <input type="hidden" name="id" value="<%= diary.getId() %>">
                    <button class="btn-view" type="submit">Diaryをのぞく</button>
                </form>
            </div>
        <%
            }
        %>
        </div>
    <%
        }
    %>
 <form action="MyCheese" method="post">
      <input type="submit" name="action" value="戻る" class="btn">
      </form>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
</body>
</html>