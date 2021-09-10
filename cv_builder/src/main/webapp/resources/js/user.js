
function login(){
	console.log("here");
	if($('#username').val() == "" || $('#password').val() == ""){
		return false;
	}else {
		var data = $("#form-login").serialize();
		console.log(data);
		$.ajax({
			type:'POST',
			url:'check_login',
			data:data,
			dataType:'JSON',
			success:function(data){
				var msg = data.message;
				console.log(data);
				
				if(data.message == "success"){
					window.location.replace("your_cvs");
				}
			}
		})
	}
}