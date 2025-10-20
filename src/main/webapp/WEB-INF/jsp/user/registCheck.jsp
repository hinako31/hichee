<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 会員登録確認画面</title>
</head>
<body>
<!--ヘッダー-->
 <main class="wrapper">
        <h2>New Account</h2>
        	<div class="msg_frame">
        	下記の内容で登録しますか？🐭
				<p>名前：${sessionScope.tentative.name}<br>
				       メールアドレス：${sessionScope.tentative.email}<br>
				       パスワード：******（表示しません）<br>
				</p>
			</div><!-- msg_frame -->
			
			   <form action="Regist" method="post">
			   <input type="submit" name="go" value="会員登録" class="nav_btn"><br>
			   <input type="submit" name="back2" value="戻る" class="nav_btn">
			   
			   </form>
		</main>
</body>
</html>