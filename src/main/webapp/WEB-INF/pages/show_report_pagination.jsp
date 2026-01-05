<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page isELIgnored="false"%>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<h1 style="text-align: center; color: red;">Employee Data</h1>
<c:choose>
	<c:when test="${!empty pageData.getContent()}">
		<div style="margin-left: 28%">
			<table border="1"
				style="text-align: center; border-bottom-color: orange; background-color: cyan; list-style: circle; width: 60%; height: 60%">

				<tr style="color: green; background-color: yellow;">
					<th>NO</th>
					<th>Name</th>
					<th>Job</th>
					<th>Salary</th>
					<th>&nbsp;DeptNO</th>
					<th>Operations</th>



				</tr>
				<c:forEach var="emp" items="${pageData.getContent()}">
					<tr>
						<td>${emp.empno}</td>
						<td>${emp.empname}</td>
						<td>${emp.job}</td>
						<td>${emp.sal}</td>
						<td>&nbsp;&nbsp;&nbsp;${emp.deptno}</td>

						<td><a href="edit?no=${emp.empno}"> <img
								src="images/edit.png" width="40px" height="40px" />
						</a> <a href="delete?no=${emp.empno}"
							onclick="return confirm('Do you want to delete the employee') ">
								<img src="images/delete.jpeg" width="40px" height="40px" />
						</a></td>

					</tr>
				</c:forEach>
			</table>
			<h1 style="text-align: center; color: red">${resultMsg }</h1>
		</div>
		<center>
			<c:if test="${!pageData.isFirst() }">
				<a href="page_report?page=0 ">First</a> &nbsp; &nbsp;&nbsp;
			</c:if>
			<c:if test="${pageData.hasPrevious() }">
				<a
					href="page_report?page=${pageData.getPageable().getPageNumber()-1 } ">Previous</a> &nbsp; &nbsp;&nbsp;
	        </c:if>
			<c:forEach var="i" begin="1" end="${pageData.getTotalPages()-1 }"
				step="1">
				<a href="page_report?page=${i-1} ">${i}</a> &nbsp; &nbsp;&nbsp;
			</c:forEach>

			<c:if test="${!pageData.isLast()}">
				<a href="page_report?page=${pageData.getTotalPages()-1 }">Last</a> &nbsp; &nbsp;&nbsp;
             </c:if>

			<c:if test="${pageData.hasNext()}">
				<a
					href="page_report?page=${pageData.getPageable().getPageNumber()+1 }">Next</a> &nbsp; &nbsp;&nbsp;
             </c:if>
		</center>
	</c:when>
	<c:otherwise>
		<h1 style="color: red; text-align: center;">Records not found</h1>
	</c:otherwise>
</c:choose>

<center>

	<a href="./" style="align-items: flex-end;">Home <img
		src="images/home.jpeg" width="50px" height="60px">
	</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="register">Register <img
		src="images/register.png" width="50px" height="60px">
	</a>
</center>