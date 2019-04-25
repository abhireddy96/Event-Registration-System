
function getEmployee() {
	$.get('/employee', function (data) { 
		$('#employeeTable').htmlson({
			data: data,
			headers: {
				0: 'Employee Id',
				1: 'Employee Name',
				2: 'Joining Date',
				3: 'Email',
			},
			debug: true
		});
	});
}

function getEvent() {
	$.get('/event', function (data) { 
		$('#eventTable').htmlson({
			data: data,
			headers: {
				0: 'Event Id',
				1: 'Event Title',
				2: 'Decription',
			},
			debug: true
		});
	});
}

function registerEmployeeToEvent(mid,eid) {
	$.post('/employee/'+mid+'/event/'+eid,
			function(data,status){
		$("#modalTitle").html("Register Employee to Event");
		$("#modalResult").html("<span style='color:green;'><strong>Success!  </strong>"+data+"</span>");
		$('#myModal').modal("show");
		console.log(data);
	}).fail(function(){
		$("#modalTitle").html("Register Employee to Event");
		$("#modalResult").html("<span style='color:red;'><strong>Failure!  </strong>Unable to Register Employee to Event<br/>Either of Employee Id or Event Id is Wrong</span>");
		$('#myModal').modal("show");
		console.log(data);
	});
}

function addEvent() {
	$.ajax({
		type: 'POST',
		url: '/event',
		data: getJson($('#addEventForm').serializeArray()),
		contentType: 'application/json' 
	}).done(function(data,status){
		$("#modalTitle").html("Add a Event");
		$("#modalResult").html("<span style='color:green;'><strong>Success!  </strong>"+data+"</span>");
		$('#myModal').modal("show");
		console.log(data);})
		.fail(function(error,status){
			$("#modalTitle").html("Add a Event");
			console.log(status);
			$("#modalResult").html("<span style='color:red;'><strong>Failure!  </strong>Unable to Add Event</span>");
			$('#myModal').modal("show");});
}

function addEmployee() {
	$.ajax({
		type: 'POST',
		url: '/employee',
		data: getJson($('#addEmployeeForm').serializeArray()),
		contentType: 'application/json' 
	}).done(function(data,status){
		$("#modalTitle").html("Add a Employee");
		$("#modalResult").html("<span style='color:green;'><strong>Success!  </strong>"+data+"</span>");
		$('#myModal').modal("show");
		console.log(data);})
		.fail(function(error,status){
			$("#modalTitle").html("Add a Employee");
			console.log(status);
			$("#modalResult").html("<span style='color:red;'><strong>Failure!  </strong>Unable to Add Employee</span>");
			$('#myModal').modal("show");});
}

function getEventsOfEmployee(mid) {
	$.get('/employee/'+mid+'/event', function (data) { 
		$('#eventOfEmployeeTable').htmlson({
			data: data,
			headers: {
				0: 'Event Id',
				1: 'Event Title',
				2: 'Decription',
			},
			debug: true
		});
	});
}

function getemployeesOfEvent(eid) {
	$.get('/event/'+eid+'/employee', function (data) { 
		$('#employeesOfEventTable').htmlson({
			data: data,
			headers: {
				0: 'Employee Id',
				1: 'Employee Name',
				2: 'Joining Date',
				3: 'Email',
			},
			debug: true
		});
	});
}


function getJson(formArray) { 
	var returnArray = {};
	for (var i = 0, len = formArray.length; i < len; i++)
		returnArray[formArray[i].name] = formArray[i].value;
	return JSON.stringify(returnArray);
}
