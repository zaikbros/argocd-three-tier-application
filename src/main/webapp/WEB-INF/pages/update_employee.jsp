<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/style.css">
<script language="JavaScript" src="js/validation.js"></script>

<h1 style="color: red; text-align: center">Update Employee</h1>


<f:form modelAttribute="emp" method="post"
	onsubmit="return doValidations(this)">

	<f:input type="hidden" path="vflag" />

	<table align="center" border="1" bgcolor="cyan"
		style="align-content: center;">

		<tr>
			<td>Employee Number::</td>
			<td><f:input readonly="true" path="empno" /> <f:errors
					path="empno"></f:errors></td>

		</tr>
		<tr>
			<td>Employee Name::</td>
			<td><f:input path="empname" /> <f:errors path="empname"></f:errors></td>

		</tr>
		<tr>
			<td>Employee Job::</td>
			<td><f:input path="job" /> <f:errors path="job"></f:errors></td>

		</tr>
		<tr>
			<td>Employee Salary::</td>
			<td><f:input path="sal" /> <f:errors path="sal"></f:errors></td>

		</tr>
		<tr>
			<td>Employee Deptno::</td>
			<td><f:input type="text" path="deptno" /> <f:errors
					path="deptno"></f:errors></td>

		</tr>
		<tr>
			<td><input type="submit" value="Update Employee" /></td>
			<td><input type="reset" value="cancel" /></td>

		</tr>

	</table>

</f:form>















