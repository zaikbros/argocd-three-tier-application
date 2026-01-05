<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page isELIgnored="false"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<h1 style="text-align: center; color: red;">Employee Data</h1>
<c:choose>
	<c:when test="${!empty empsData}">
		<div style="margin-left: 32%">
			<table border="2"
				style="text-align: center; border-bottom-color: orange;">

				<tr style="color: green; background-color: yellow;">
					<th>NO</th>
					<th>Name</th>
					<th>Job</th>
					<th>Salary</th>
					<th>&nbsp;DeptNO</th>
					<th>Operations</th>



				</tr>
				<c:forEach var="emp" items="${empsData}">
					<tr>
						<td>${emp.empno}</td>
						<td>${emp.empname}</td>
						<td>${emp.job}</td>
						<td>${emp.sal}</td>
						<td>&nbsp;&nbsp;&nbsp;${emp.deptno}</td>

						<td><a href="edit?no=${emp.empno}"> <img
								src="images/edit.png" width="30px" height="40px" />
						</a> <a href="delete?no=${emp.empno}"
							onclick="return confirm('Do you want to delete the employee') ">
								<img src="images/delete.jpeg" width="41px" height="36px" />
						</a></td>

					</tr>
				</c:forEach>
			</table>
			<h1 style="text-align: center; color: red">${resultMsg }</h1>
		</div>
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