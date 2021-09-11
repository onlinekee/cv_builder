
<%@ include file="header.jsp"%>

<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet">
<title>Login</title>
<div class="container">



	<div class="login">
		<div class="login-form">
			<h3 class="text-center">Login</h3>
			<form name="form-login" id="form-login">
				<div class="mb-3">
					<label for="username" class="form-label">Username </label> <input
						type="text" class="form-control" id="username" name="username">

				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" id="password" name="password">
				</div>
				<div class="alert alert-danger" role="alert" id="error"></div>
				<div class="d-grid mb-3">
					<button type="button" class="btn btn-primary" onclick="login()">Submit</button>
				</div>
				<div class="right">
					Don't have an account? <a href="register">Signup</a>
				</div>
			</form>

		</div>
	</div>

</div>


<%@ include file="footer.jsp"%>

