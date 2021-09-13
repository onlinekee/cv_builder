<%@ include file="header.jsp"%>

<script
	src="${pageContext.request.contextPath}/resources/js/create_cv.js"></script>

<title>Create CV</title>

<div class="container">
	<div class="my-4">
		<h3 class="title">Create Your CV</h3>
		<hr>
		<div class="center">
			<div class="col-md-6 py-2" id="start_div">
				<div class="mb-3">
					<label for="cv_name" class="form-label">Enter name for the
						CV </label> <input type="text" class="form-control" id="cv_name"
						name="cv_name"> <span class="error-text"
						id="cv_name_error"></span>
				</div>
				<div class="center">
					<div class="d-grid col-md-4 mb-3 ">
						<button class="btn btn-primary btn-sm" onclick="createCV()">Start</button>
					</div>
				</div>
			</div>
		</div>
		<div id="step2">
			<div>
				<label class="label">CV Name:</label> <span id="cv"></span>
				<button type="button" class="btn btn-success btn-sm mx-4" onclick="printCV()">Print</button>
			</div>
			<div class="col-md-12">
				<div class="row">
					
					<div class="col-md-6 ">
						<div id="basic" class="pt-4">
							<h5>Add Basic Information</h5>
							<div class="mt-4">
								<form id="basic_info_form" name="basic_info_form">
									<div class="mb-3">
										<label for="first_name" class="form-label">First Name</label>
										<input type="text" class="form-control" id="first_name"
											name="first_name">
									</div>
									<div class="mb-3">
										<label for="last_name" class="form-label">Last Name</label> <input
											type="text" class="form-control" id="last_name"
											name="last_name">
									</div>
									<div class="mb-3">
										<label for="phone" class="form-label">Phone</label> <input
											type="text" class="form-control" id="phone" name="phone">
									</div>
									<div class="mb-3">
										<label for="address" class="form-label">Address</label> <input
											type="text" class="form-control" id="address" name="address">
									</div>
									<div class="mb-3">
										<label for="email" class="form-label">Email</label> <input
											type="text" class="form-control" id="email" name="email">
									</div>
									<div class="mb-3">
										<label for="website" class="form-label">Website</label> <input
											type="text" class="form-control" id="website" name="website">
									</div>
									<div class="center">
										<div class="d-grid col-md-4 mb-3 ">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="addBasicInfo()">Save</button>
										</div>
									</div>
								</form>

							</div>
						</div>
						<div id="section" class="pt-4">
							<h5>Add Section</h5>
							<div class="mt-4">
								<form>

									<div class="mb-3">
										<label for="title" class="form-label">Title</label> <input
											type="text" class="form-control" id="title" name="title">
									</div>
									<div class="p-2 preview" id="sub_section_preview"></div>
									<h6 class="my-3">Sub Section</h6>
									<div class="mb-3">
										<select class="form-select"
											aria-label="Default select example" id="type"
											onchange="selectType()">
											<option selected value="">Select Section Type</option>
											<option value="Paragraph">Paragraph</option>
											<option value="Points">Points</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="title" class="form-label">Sub Title</label> <input
											type="text" class="form-control" id="sub_title" name="sub_title">
									</div>
									<div id="paragraphs">
										<div class="mb-3">
											<label for="lname" class="form-label">Content</label>
											<textarea type="text" class="form-control" id="paragraph"
												name="paragraph"></textarea>
										</div>
									</div>
									<div id="points">
										<div class="p-2 preview" id="points_preview"></div>
										<div class="my-3">
											<label for="point" class="form-label">Point</label> <input
												type="text" class="form-control" id="point" name="point">
											<button type="button" class="btn btn-success btn-sm mt-2"
												onclick="addPoint()">Add Point</button>
										</div>
									</div>
									<span id="section_error" class="error-text"></span>
									<div class="right">
										<div class="mb-3 ">
											<button class="btn btn-success btn-sm" type="button"
												onclick="addSubSection()">Add Sub Section</button>
										</div>
									</div>
									
									<div class="center">
										<div class="d-grid col-md-4 mb-3" id="add-button">
											<button type="button" class="btn btn-primary btn-sm"
												onclick="addSection()">Add</button>
										</div>

									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="col-md-6 my-4">
						<div class="preview" id="preview"></div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>