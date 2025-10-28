<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee myCheese検索画面</title>
</head>
<body>
<!--ヘッダー-->
<h2>My Cheese</h2>

<form action="MyCheese" method="post">

    <label>店名: </label>
    <input type="text" name="name" value="${param.name != null ? param.name : ''}" /><br><br>

    <label>記念年: </label>
    <select name="period_year">
        <option value="">選択しない</option>
        <c:forEach var="year" begin="2015" end="2027">
            <option value="${year}" <c:if test="${param.period_year == year.toString()}">selected</c:if>>${year}</option>
        </c:forEach>
        <option value="unknown" <c:if test="${param.period_year == 'unknown'}">selected</c:if>>分からない</option>
    </select><br><br>

    <label>記念月: </label>
    <select name="period_month">
        <option value="">選択しない</option>
        <c:forEach var="month" begin="1" end="12">
            <option value="${month}" <c:if test="${param.period_month == month.toString()}">selected</c:if>>${month}月</option>
        </c:forEach>
        <option value="unknown" <c:if test="${param.period_month == 'unknown'}">selected</c:if>>分からない</option>
    </select><br><br>

    <label>場所: </label>
    <select name="area_id">
        <option value="">選択しない</option>
        <c:forEach var="area" items="${areaList}">
            <option value="${area.id}" <c:if test="${param.area_id == area.id.toString()}">selected</c:if>>${area.area_name}</option>
        </c:forEach>
        <option value="unknown" <c:if test="${param.area_id == 'unknown'}">selected</c:if>>分からない</option>
    </select><br><br>

    <input type="submit" name="action" value="検索" class="btn">

</form>
<form action="Login" method="post">
<input type="submit" name="mypage" value="My Pageへ" class="nav_btn">
</form>
<jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
</body>
</html>
