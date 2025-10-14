<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ユーザー情報変更確認画面</title>
</head>
<body>
<!--ヘッダー-->
  <main class="wrapper">
        <h2>Change Profile</h2>
		<p class="msg_info">下記の内容で変更しますか？🐭</p>
        	
				<p>名前：${sessionScope.tentative.name}<br>
				       メールアドレス：${sessionScope.tentative.email}<br>
				       パスワード：<c:choose>
     <c:when test="${empty sessionScope.tentative.password}">
       変更なし
     </c:when>
     <c:otherwise>
       ******（表示しません）
     </c:otherwise>
   </c:choose><br>
				</p>
			<!-- msg_frame -->
			
			   <form action="ChangePlofile" method="post">
			     <input type="submit" name="step" value="変更" class="nav_btn">
			     <input type="submit" name="step" value="戻る" class="nav_btn">
			   </form>
		</main>

</body>
</html>