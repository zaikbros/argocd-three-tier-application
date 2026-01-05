<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<link rel="stylesheet" type="text/css" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<h1 style="color: red; text-align: center">Register Employee</h1>
<!--  -->
<link lang="JavaScript" href=js/validation.js">

<script language="JavaScript" src="js/validation.js"></script>
<div style="color: fuchsia;">
<f:form action="register" modelAttribute="emp"
	onsubmit=" return doValidations(this)">
	<p style="color: red; text-align: center;">
		<f:errors path="*" />
		<f:input type="hidden" path="vflag" />
	<table align="center" border="1" bgcolor="cyan"
		style="align-content: center; ">

		<tr>
			<td>Employee Name</td>
			<td><f:input type="text" path="empname" /> <f:errors
					path="empname"></f:errors></td>

		</tr>
		<tr>
			<td>Employee Job</td>
			<td><f:input type="text" path="job" /> <f:errors path="job"></f:errors></td>

		</tr>
		<tr>
			<td>Employee Salary</td>
			<td><f:input type="text" path="sal" /> <f:errors path="sal"></f:errors></td>

		</tr>
		<tr>
			<td>Employee Deptno</td>
			<td><f:input type="text" path="deptno" /> <f:errors
					path="deptno"></f:errors></td>

		</tr>
		<tr>
			<td><input type="submit" value="Add Employee" /></td>
			<td><input type="reset" value="Cancel" /></td>

		</tr>

	</table>


</f:form>
</div>















