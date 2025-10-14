<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ユーザー情報変更画面</title>
</head>
<body>
<!--ヘッダー-->
 <main class="wrapper">
		    <h2>Change Plofile</h2>
            <c:if test="${not empty error}">
		        <p style="color:red;">${error}</p>
		    </c:if>
		
		    <form action="ChangePlofile" method="post" class="regist">
		        <input type="hidden" name="step" value="formsubmit">
				<p>
			        <label for="name">名前：
			        <input type="text" name="name" value="${tentative.name}"></label>
				</p>
				<p>
			        <label for="email">メールアドレス：
			        <input type="email" name="email" value="${tentative.email}"></label>
				</p>
				<p>
			        <label for="pass">パスワード（4文字以上）：
			        <input type="password" name="pass" value=""></label>
				</p>
				<p>
			        <label for="confirmPass">パスワード（確認用）：
			        <input type="password" name="confirmPass" ></label>
				</p>
		        <input type="submit" name="check" value="確認" class="nav_btn">
		        <input type="submit" name="back" value="戻る" class="nav_btn">
		    </form>
	    
		</main>
</body>
</html>