<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<style><%@include file="/views/style.css" %></style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/header.jsp"%>
		<section class="login">
			<div class="container">
				<div class="row justify-center">
					<form class="form-login" action="${pageContext.request.contextPath}/login" method="post">
						<h2>Đăng nhập</h2>
						<div class="group-input">
							<label for="username">Tên đăng nhập *:</label> 
							<input value="${userName }" 
								   id="username" 
								   name="username" 
								   type="text" 
								   placeholder="Tên đăng nhập" 
								   maxlength="16"
								   required="required"
							/>
						</div>
						<div class="group-input">
							<label for="password">Mật khẩu *:</label> 
							<input value="${password }" 
								   id="password" 
								   name="password" 
								   type="password" 
								   placeholder="Nhập mật khẩu" 
								   maxlength="16"
								   required="required"
							/>
						</div>
						<div class="group-input">
							<p class="errMessage">${errMessage }</p>
						</div>
						<div class="btn">
							<button type="submit">Login</button>
						</div>
					</form>
				</div>
			</div>
		</section>
		<%@include file="/views/footer.jsp"%>
	</div>
</body>
</html>