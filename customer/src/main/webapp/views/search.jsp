<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Search</title>
<style><%@include file="/views/style.css" %></style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/header.jsp"%>
		<section class="search">
			<form id="formSearch" action="${pageContext.request.contextPath}/search" method="post">
				<div class="formSearch">
					<label>Fullname</label>
					<input type="text" name="fullname" value="${fullname }" />
					<label>Sex</label> 
					<select name="sex">
						<option hidden="" value="${sex }">${sex }</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select> 
					<label>Birthday</label>
					<input type="text" 
						   name="birthdayFirst" 
						   id="birthdayFirst" 
						   value="${birthdayFirst }" 
						   pattern="^(?:\d{4}\/(?:(?:(?:(?:0[13578]|1[02])\/(?:0[1-9]|[1-2][0-9]|3[01]))|(?:(?:0[469]|11)\/(?:0[1-9]|[1-2][0-9]|30))|(?:02\/(?:0[1-9]|1[0-9]|2[0-8]))))|(?:(?:\d{2}(?:0[48]|[2468][048]|[13579][26]))|(?:(?:[02468][048])|[13579][26])00)\/02\/29)$"
					/>
					~ 
					<input type="text" 
						   name="birthdayLast" 
						   id="birthdayLast" 
						   value="${birthdayLast }" 
						   pattern="^(?:\d{4}\/(?:(?:(?:(?:0[13578]|1[02])\/(?:0[1-9]|[1-2][0-9]|3[01]))|(?:(?:0[469]|11)\/(?:0[1-9]|[1-2][0-9]|30))|(?:02\/(?:0[1-9]|1[0-9]|2[0-8]))))|(?:(?:\d{2}(?:0[48]|[2468][048]|[13579][26]))|(?:(?:[02468][048])|[13579][26])00)\/02\/29)$"
					/>
					<script>
						function clickBtnSearch(){
							let birthdayFirst = document.getElementById("birthdayFirst");
							let birthdayLast = document.getElementById("birthdayLast");
							let date1 = new Date(birthdayFirst.value);
							let date2 = new Date(birthdayLast.value);
							if(birthdayFirst != "" && birthdayLast != ""){
								if(date1 > date2){
									alert("birthdayFirst > birthdayLast")
								}
							}else{
								document.getElementById("formSearch").submit();
							}
						}
					</script>
					<button onclick="clickBtnSearch()">Search</button>
				</div>
				<div class="pagination">
					<div>
						<script>
							function clickBtnFirst(){
								let page = document.getElementById("page");
								page.value = 1;
								document.getElementById("formSearch").submit();
							}
						</script>
						<c:if test="${currentPage <= 1 }">
						<button disabled="disabled">First</button>
						</c:if>
						<c:if test="${currentPage > 1 }">
						<button onclick="clickBtnFirst()" id="btnFirst" >First</button>
						</c:if>
						<script>
							function clickBtnPre(){
								let page = document.getElementById("page");
								page.value = ${currentPage-1 };
								document.getElementById("formSearch").submit();
							}
						</script>
						<c:if test="${currentPage <= 1 }">
						<button disabled="disabled">&lt;</button>
						</c:if>
						<c:if test="${currentPage > 1 }">
						<button onclick="clickBtnPre()">&lt;</button>
						</c:if>
						Pre
					</div>
					<input id="page" hidden="" name="page" value="${currentPage }" />
					<input id="totalPage" hidden="" name="totalPage" value="${totalPage }" />
					<div>
						Next
						<script>
							function clickBtnNext(){
								let page = document.getElementById("page");
								page.value = ${currentPage+1 };
								document.getElementById("formSearch").submit();
							}
						</script>
						<c:if test="${currentPage >= totalPage }">
						<button disabled="disabled">&gt;</button>
						</c:if>
						<c:if test="${currentPage < totalPage }">
						<button onclick="clickBtnNext()">&gt;</button>
						</c:if>
						<script>
							function clickBtnLast(){
								let page = document.getElementById("page");
								page.value = ${totalPage };
								document.getElementById("formSearch").submit();
							}
						</script>
						<c:if test="${currentPage >= totalPage }">
						<button disabled="disabled">Last</button>
						</c:if>
						<c:if test="${currentPage < totalPage }">
						<button onclick="clickBtnLast()">Last</button>
						</c:if>
					</div>
				</div>
				<div class="listCustomer">
					<table>
						<thead>
							<tr>
								<th><input type="checkbox" onclick="ClickCheckAll(this)"/></th>
								<th>id</th>
								<th>Fullname</th>
								<th>Sex</th>
								<th>Birthday</th>
								<th>email</th>
								<th>phone</th>
								<th>address</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${listCustomer }">
								<tr>
									<td><input type="checkbox" name="checkBoxItem" value="${item.id }"/></td>
									<td><a href="${pageContext.request.contextPath}/edit?customerID=${item.id }&currentPageSearch=${currentPage }&fullname=${fullname }&sex=${sex }&birthdayFirst=${birthdayFirst }&birthdayLast=${birthdayLast }">${item.id }</a></td>
									<td>${item.fullname }</td>
									<td>${item.sex }</td>
									<td>${item.birthday }</td>
									<td>${item.email }</td>
									<td>${item.phone }</td>
									<td>${item.address }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<script>
					function ClickCheckAll(checkAll) {
						let checkBoxItem = document.getElementsByName('checkBoxItem');
						for (let i = 0; i < checkBoxItem.length; i++) {
							checkBoxItem[i].checked = checkAll.checked;
						}
					}
					</script>
					<button type="submit" name="btnAdd">Add</button>
					<button type="submit" name="btnDelete">Delete</button>
				</div>
			</form>
		</section>
		<%@include file="/views/footer.jsp"%>
	</div>
</body>
</html>