


window.onload = function() {
	$("#error").hide();;
};

function login() {

	if ($('#username').val() == "" || $('#password').val() == "") {
		$("#error").html("Please fill all the fields!");
		$("#error").show();
		return false;
	} else {
		var data = $("#form-login").serialize();

		$.ajax({
			type: 'POST',
			url: 'check_login',
			data: data,
			dataType: 'JSON',
			success: function(data) {
				
				if (data.error) {
					$("#error").html(data.message);
					$("#error").show();
				} else {
					window.location.replace("your_cvs");
				}
			}
		})
	}
}

function register() {

	if (!validateRegister()) {
		return false;
	} else {

		var data = $("#form-register").serialize();

		$.ajax({
			type: 'POST',
			url: 'insert',
			data: data,
			dataType: 'JSON',
			success: function(data) {

				if (data.error) {
					$("#error").html(data.message);
					$("#error").show();
				} else {
					$("#error").hide();
					window.location.replace("login");
				}
			}
		})
	}
}

function validateRegister() {
	if ($('#username').val() == "" || $('#password').val() == "" || $('#confPassword').val() == "") {
		$("#error").html("Please fill all the fields!");
		$("#error").show();
		return false;
	} else if ($('#password').val() !== $('#confPassword').val()) {
		$("#error").html("Password and Confirm Password does not match!");
		$("#error").show();
		return false;
	} else {
		$("#error").html("");
		$("#error").hide();
		return true;
	}
}

