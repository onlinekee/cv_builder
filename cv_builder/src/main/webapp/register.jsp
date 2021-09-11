<%@ include file="header.jsp"%>

<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet">

<title>Register</title>
<div class="container">

	<div class="login">
		<div class="login-form">
			
			<h3 class="text-center">Register</h3>
			<form name="form-register" id="form-register">
				<div class="mb-3">
					<label for="username" class="form-label">Username</label> <input
						type="text" class="form-control" name="username" id="username">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" name="password" id="password">
				</div>

				<div class="mb-3">
					<label for="password" class="form-label">Confirm Password</label> <input
						type="password" class="form-control" id="confPassword">
				</div>
				
				<div class="alert alert-danger" role="alert" id="error"></div>
				
				<div class="d-grid mb-3">
					<button type="button" class="btn btn-primary" onClick="register()">Submit</button>
				</div>

				<div class="right">
					Already have an account? <a href="login">Login</a>
				</div>
			</form>

		</div>
	</div>

</div>

<%@ include file="footer.jsp"%>