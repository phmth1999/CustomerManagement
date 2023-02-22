<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Edit</title>
<style><%@include file="/views/style.css" %></style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/header.jsp"%>
		<section class="login">
			<div class="container">
				<div class="row justify-center">
					<form class="form-login" action="${pageContext.request.contextPath}/edit" method="post">
						<c:if test="${add == 'add' }">
							<h2>Add</h2>
						</c:if>
						<c:if test="${edit == 'edit' }">
							<h2>Edit</h2>
						</c:if>
						<c:if test="${edit == 'edit' }">
							<div class="group-input">
								<label for="id">Id:</label> 
								<input value="${customer.id }" 
								   id="id" 
								   name="id" 
								   type="text" 
								   maxlength="16"
								   required="required"
								   readonly="readonly"
								/>
							</div>
						</c:if>
						<div class="group-input">
							<label for="fullname">Fullname:</label> 
							<input value="${customer.fullname }" 
								   id="fullname" 
								   name="fullname" 
								   type="text" 
								   maxlength="16"
								   required="required"
							/>
						</div>
						<div class="group-input">
							<label for="sex">Sex:</label> 
							<select name="sex"  required="required">
								<option hidden="" value="${customer.sex }">${customer.sex }</option>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
							</select>
						</div>
						<div class="group-input">
							<label for="birthday">Birthday:</label> 
							<input value="${customer.birthday }" 
								   id="birthday" 
								   name="birthday" 
								   type="text" 
								   maxlength="16"
								   required="required"
								   pattern="^(?:\d{4}\/(?:(?:(?:(?:0[13578]|1[02])\/(?:0[1-9]|[1-2][0-9]|3[01]))|(?:(?:0[469]|11)\/(?:0[1-9]|[1-2][0-9]|30))|(?:02\/(?:0[1-9]|1[0-9]|2[0-8]))))|(?:(?:\d{2}(?:0[48]|[2468][048]|[13579][26]))|(?:(?:[02468][048])|[13579][26])00)\/02\/29)$"
							/>
						</div>
						<div class="group-input">
							<label for="email">Email:</label> 
							<input value="${customer.email }" 
								   id="email" 
								   name="email" 
								   type="text" 
								   maxlength="16"
								   required="required"
								   pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
							/>
						</div>
						<div class="group-input">
							<label for="phone">Phone:</label> 
							<input value="${customer.phone }" 
								   id="phone" 
								   name="phone" 
								   type="text" 
								   maxlength="16"
								   required="required"
								   pattern="^\d{10}$"
							/>
						</div>
						<div class="group-input">
							<label for="address">Address:</label> 
							<input value="${customer.address }" 
								   id="address" 
								   name="address" 
								   type="text" 
								   maxlength="255"
								   required="required"
							/>
						</div>
						<div class="group-input">
							<p class="errMessage">${errMessage }</p>
						</div>
						<div class="btn">
							<c:if test="${add == 'add' }">
								<button type="submit">Add</button>
							</c:if>
							<c:if test="${edit == 'edit' }">
								<button type="submit">Edit</button>
							</c:if>
							
						</div>
					</form>
				</div>
			</div>
		</section>
		<%@include file="/views/footer.jsp"%>
	</div>
</body>
</html>