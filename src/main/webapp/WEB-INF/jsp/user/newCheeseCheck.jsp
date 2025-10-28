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
</head>
<body>
<!--ヘッダー-->
<h2>New Cheese</h2>
<main class="wrapper">
		<p class="msg_info">このCheese Diaryを作成しますか？🐭</p>
		<p>
          店名：${fn:escapeXml(sessionScope.diary.name)}<br>
          記念年：${fn:escapeXml(memorialYearDisplay)}<br>
          記念月：${fn:escapeXml(memorialMonthDisplay)}<br>
          場所：${fn:escapeXml(areaName)}<br>
          レビュー：<br>${fn:escapeXml(sessionScope.diary.review)}<br>
          添付ファイル: ${sessionScope.diary.file_name == null ? "なし" : sessionScope.diary.file_name}<br>
 <!-- 画像プレビュー -->
        <c:if test="${not empty sessionScope.diary.file_path}">
            <img src="${pageContext.request.contextPath}/${sessionScope.diary.file_path}" alt="画像プレビュー">
        </c:if>
    </p>

		<form action="NewCheese" method="post">
    <button type="submit" name="step" value="作成" class="confirm_btn">作成</button>
    <button type="submit" name="step" value="戻る" class="back_btn">戻る</button>

</form>
</main>
<jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
</body>
</html>