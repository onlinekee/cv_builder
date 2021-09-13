<%@ include file="header.jsp"%>

<script src="${pageContext.request.contextPath}/resources/js/cvs.js"></script>

<title>Your CVs</title>

<div class="container">
	<div class="my-4">
		<h3 class="title">Your CV List</h3>
		<hr>
		<div class="my-2">
			<a href="${pageContext.request.contextPath}/create_cv"
				class="btn btn-success btn-sm">Create New CV</a>
		</div>
		<div class="alert alert-danger" role="alert" id="error"></div>
		<div class="alert alert-success" role="alert" id="success"></div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">CV Name</th>
					<th scope="col">Date Created</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody id="rows">
				<!-- <tr>
					<th scope="row">1</th>
					<td>Mark</td>
					<td>Otto</td>
					<td><button class="btn btn-warning btn-sm">Edit</button> <button class="btn btn-danger btn-sm">Delete</button></td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Jacob</td>
					<td>Thornton</td>
					<td>@fat</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td colspan="2">Larry the Bird</td>
					<td>@twitter</td>
				</tr> -->
			</tbody>
		</table>
	</div>
</div>

<%@ include file="footer.jsp"%>