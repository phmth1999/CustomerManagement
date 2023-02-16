<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<header class="header">
	<div class="login-logout">
		<c:if test="${accountLogin == null }">
			<a href="${pageContext.request.contextPath}/login">Login</a>
		</c:if>
		<c:if test="${accountLogin != null }">
			<a href="#">${accountLogin.fullname }</a>
			<p>|</p>
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
		</c:if>
	</div>
</header>