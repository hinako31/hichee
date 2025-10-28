<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ChangeCheese確認画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">
</head>
<body>
<div class="outerWrapper">
  <div class="login-card">
 
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>
    
  <div class="form-area">
  
       <h2 class="login-title">Change Profile</h2>
		<p class="confirm-msg">下記の内容で変更しますか？🐭</p>
        	<div class="form-row">
				 <label>ニックネーム：</label>
				 <strong>${sessionScope.tentative.name}</strong>
				 </div>
				 
				 <div class="form-row">
				 <label>メールアドレス：</label>
				 <strong>${sessionScope.tentative.email}</strong>
				 </div>
				 
				 <div class="form-row">
				  <label>パスワード：</label>
				 <strong> <c:choose>
             <c:when test="${not sessionScope.isPasswordChanged}">
      変更なし
    </c:when>
    <c:otherwise>
      ******（表示しません）
    </c:otherwise>
  </c:choose>
  </strong>
</div>

			<div class="button-area">
			   <form action="ChangeProfile" method="post">
			      <button type="submit" name="step" value="変更">変更</button>
                  <button type="submit" name="step" value="戻る">戻る</button>
        </form>
      </div>
    </div>

    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
  </div>
</div>
</body>
</html>
