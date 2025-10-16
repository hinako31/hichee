<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<p>店名：${sessionScope.diary.name}<br>
		記念年：${sessionScope.diary.period_year}<br>
        記念月：${sessionScope.diary.period_month}<br>
        場所：${sessionScope.diary.area_id}<br>
        レビュー：${fn:escapeXml(sessionScope.diary.review)}<br>      
	    添付ファイル：${sessionScope.diary.img_name}<br>
	    	  
		 </p>
		<form action="NewCheese" method="post">
    <button type="submit" name="step" value="作成">作成</button>
    
    <button type="submit" name="step" value="戻る">戻る</button>
</form>
</body>
</html>