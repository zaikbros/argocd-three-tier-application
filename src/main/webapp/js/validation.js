function doValidations(f) {

	//empty old form validation error messages
	document.getElementById("empnameErr").innerHTML = "";
	document.getElementById("jobErr").innerHTML = "";
	document.getElementById("salErr").innerHTML = "";
	document.getElementById("deptnoErr").innerHTML = "";

	//read from component values
	let name = f.empname.value;
	let job = f.job.value;
	let sal = f.sal.value;
	let deptno = f.deptno.value;

    f.vflag.value="yes";
	let isValid = true;
	//Let client side form  validations logics
	if (name = "") {//required rule
		document.getElementById("empnameErr").innerHTML = "Employee name is mandatory";
		isValid = false;
	}
	else if (name.length() < 5 || name.length() >= 15) {
		document.getElementById("empnameErr").innerHTML = "Employee name must contains >=5 chars <=15";
		isValid = false;
	}
	if (job == "") {
		document.getElementById("jobErr").innerHTML = "Employee job mandatory";
		isValid = false;
	} else if (job.length() < 5 || job.length() >= 10) {
		document.getElementById("jobErr").innerHTML = "Employee job must contains >=5 chars <=10";
		isValid = false;
	} if (sal == "") {
		document.getElementById("salErr").innerHTML = "Employee Salary is mandatory";
		isValid = false;
	} else if (isNaN(sal)) {
		document.getElementById("salErr").innerHTML = "Employee salary must be numeric value";
		isValid = false;
	} else if (sal <= 1000 && sal >= 200000) {
		document.getElementById("salErr").innerHTML = "Employee salary must be in the range of 1000 to 200000";
		isValid = false;
	} if (deptno = "") {
		document.getElementById("deptnoErr").innerHTML = "Employee deptno is mandatory";
		isValid = false;
	}

	return isValid;
}