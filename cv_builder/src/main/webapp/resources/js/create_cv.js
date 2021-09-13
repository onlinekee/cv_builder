/**
 * 
 */
var position = 0;
var sections = [];
var subSections = [];
var points = [];
var cvId = null;
var basicInfo = null;

window.onload = function() {
	$("#step2").hide();
	$("#paragraphs").hide();
	$("#points").hide();
	$("#sub_section_preview").hide();
	$("#points_preview").hide();

	var url = new URL(window.location.href);
	var id = url.searchParams.get("id");

	if (id != null || id != "") {
		getCVById(id);
	}

};

function printCV() {
	/*	$(".edit-buttons").hide();
		$(".position-buttons").hide();*/

	window.print();

}

function addSubSection() {
	var error = "";
	if ($('#type').val() == "Paragraph") {

		if ($('#paragraph').val() != "") {
			$("#section_error").hide();
			var obj = {
				type: "Paragraph",
				subTitle: $('#sub_title').val(),
				paragraph: $('#paragraph').val(),
			}
			subSections.push(obj);

		} else {
			error = "Enter content!";
			$("#section_error").html(error);
			$("#section_error").show();
			return false;
		}

	} else if ($('#type').val() == "Points") {

		if (points.length != 0) {
			$("#section_error").hide();
			var obj = {
				type: "Points",
				subTitle: $('#sub_title').val(),
				points: [...points],
			}

			subSections.push(obj);
			points = [];
			viewPoints();
		} else {
			error = "Add Poinst!";
			$("#section_error").html(error);
			$("#section_error").show();
			return false;
		}

	} else {
		error = "Please Select a Type!";
		$("#section_error").html(error);
		$("#section_error").show();
		return false;
	}

	$("#sub_section_preview").html("");
	$("#sub_section_preview").hide();
	$("#poinsts_preview").html("");
	$("#poinsts_preview").hide();
	$("#section_error").hide();
	$('#sub_title').val("");
	$('#paragraph').val("");
	$('#point').val("");

	viewSubSections();
}

function addPoint() {
	var point = $("#point").val();

	if ($('#type').val() == "Points") {
		if (point != "") {
			points.push(point);
			$("#point").val("");
			viewPoints();
		}
	}
}

function viewSubSections() {
	var st = "";

	subSections.forEach((el, i) => {

		if (el.type == "Paragraph") {
			st += `<button type="button" class="btn btn-sm btn-danger" onclick="removeSubSection(` + i + `)">&times</button>` + `<h4 class="my-2">` + el.subTitle + "</h4> <p>" + el.paragraph + "</p>";
		} else if (el.type == "Points") {
			st += `<button type="button" class="btn btn-sm btn-danger" onclick="removeSubSection(` + i + `)">&times</button>` + `<h4 class="my-2">` + el.subTitle + "</h4><ul>";

			el.points.forEach((p, j) => {
				st += `<li>` + p + `</li>`;
			})

			st += "</ul>"
		}
	})

	$("#sub_section_preview").html(st);

	if (subSections.length == 0) {
		$("#sub_section_preview").hide();
	} else {
		$("#sub_section_preview").show();
	}
}

function viewPoints() {
	var st = "<ul>"

	points.forEach((p, j) => {
		st += `<li>` + p + `<span class="mx-2 red-icon" onclick="removePoint(` + j + `)">&times</span></li>`;
	})

	st += "</ul>";

	$("#points_preview").html(st);

	if (points.length == 0) {
		$("#points_preview").hide();
	} else {
		$("#points_preview").show();
	}
}

function removePoint(id) {
	points.splice(id, 1);
	viewPoints();

}

function removeSubSection(id) {
	subSections.splice(id, 1);
	viewSubSections();
}



function selectType() {
	var type = $('#type').val()
	if (type == "Paragraph") {
		$("#paragraphs").show();
		$("#points").hide();
	} else if (type == "Points") {
		$("#points").show();
		$("#paragraphs").hide();
	}
}

function generatePreview() {

	var st = `<div class="info-section"><div >`;

	if (basicInfo.firstName != null) {
		st += `<h2>` + basicInfo.firstName + `</h2>`;
	}
	if (basicInfo.lastName != null) {
		st += `<h3>` + basicInfo.lastName + `</h3>`;
	}

	st += `</div><div class="contact-info">`;

	if (basicInfo.address != null && basicInfo.address != "") {
		st += `<span>` + basicInfo.address + `<i class="fas fa-home mx-2"></i></span>`;
	}

	if (basicInfo.phone != null && basicInfo.phone != "") {
		st += `<span>` + basicInfo.phone + `<i class="fas fa-phone-alt mx-2"></i></span>`;
	}

	if (basicInfo.email != null && basicInfo.email != "") {
		st += `<span>` + basicInfo.email + `<i class="fas fa-envelope mx-2"></i></span>`;
	}

	if (basicInfo.website != null && basicInfo.website != "") {
		st += `<span>` + basicInfo.website + `<i class="fas fa-globe mx-2"></i></span>`;
	}


	st += `</div></div><div class="content-section">`;

	sections.forEach((el, i) => {
		var content = JSON.parse(el.content);

		st += `<div><div>`;

		st += `<div class="section-title"><h4>` + el.title + `</h4>`;
		st += `<div class="position-buttons right "><i class="fas fa-caret-up fa-2x" onclick="positionUP(` + el.position + "," + el.sectionId + `)"></i> <i class="fas fa-caret-down fa-2x" onclick="positionDown(` + el.position + "," + el.sectionId + `)"></i></div></div><div class="content">`;
		content.forEach((c, j) => {
			if (c.type == "Paragraph") {
				st += `<h5 class="sub-title">` + c.subTitle + `</h5>`
					+ `<p>` + c.paragraph + `</p>`;
			} else if (c.type == "Points") {
				st += `<h5 class="sub-title">` + c.subTitle + `</h5><ul class="points-list">`;

				c.points.forEach((p, k) => {
					st += `<li>` + p + `</li>`;
				})
				st += `</ul>`;
			}
		})
		st += `</div>`;
		st += `<div class="edit-buttons right "><button type="button" class="btn btn-danger btn-sm mx-1" onclick="deleteSection(` + el.sectionId + `)">Delete</button></div></div>`;

	})

	st += `</div></div>`;
	$("#preview").html(st);
	$("#preview").show();

}

function addBasicInfo() {
	if (cvId == null) {
		$("#cv_name_error").html("CV not found!");
		$("#error").show();
		return false;
	} else {
		var data = {
			cv_id: cvId,
			first_name: $("#first_name").val(),
			last_name: $("#last_name").val(),
			phone: $("#phone").val(),
			address: $("#address").val(),
			email: $("#email").val(),
			website: $("#website").val(),
		}


		$.ajax({
			type: 'POST',
			url: 'create_cv/add_basic_info',
			data: data,
			dataType: 'JSON',
			success: function(data) {

				if (data.error) {
					$("#cv_name_error").html(data.message);
					$("#error").show();
				} else {
					getCVById(cvId);

				}
			}
		})
	}
}

function createCV() {
	if ($('#cv_name').val() == "") {
		$("#cv_name_error").html("Please enter a name!");
		$("#error").show();
		return false;
	} else {
		var data = $("#cv_name").serialize();

		$.ajax({
			type: 'POST',
			url: 'create_cv/start_cv',
			data: data,
			dataType: 'JSON',
			success: function(data) {

				if (data.error) {
					$("#cv_name_error").html(data.message);
					$("#error").show();
				} else {
					$("#start_div").hide();
					$("#cv").html(data.data.cvName);
					cvId = data.data.cvId;
					$("#step2").show();

				}
			}
		})
	}
}

function getCVById(id) {

	$.ajax({
		type: 'POST',
		url: 'create_cv/get_cv',
		data: { "id": id },
		dataType: 'JSON',
		success: function(data) {

			if (data.error) {
				$("#cv_name_error").html(data.message);
				$("#error").show();
			} else {
				$("#start_div").hide();
				$("#cv").html(data.data.cvName);
				$("#step2").show();
				cvId = data.data.cvId;
				basicInfo = data.data;
				pupulateBasicInfo();
				getSectionsByCV();

			}
		}
	})
}

function pupulateBasicInfo() {
	$("#first_name").val(basicInfo.firstName);
	$("#last_name").val(basicInfo.lastName);
	$("#phone").val(basicInfo.phone);
	$("#email").val(basicInfo.email);
	$("#address").val(basicInfo.address);
	$("#website").val(basicInfo.website);
}


function addSection() {
	var error = "";
	if (cvId == null) {
		error = "No CV Selected";
		$("#section_error").html(error);
	} else if ($('#title').val() == "") {
		error = "Please enter a title.";
		$("#section_error").html(error);
	} else if (subSections.length == 0) {
		error = "Please add a sub section.";
		$("#section_error").html(error);
	} else {
		var data = { cv_id: cvId, title: $('#title').val(), content: JSON.stringify(subSections), position: (position + 1) }

		$.ajax({
			type: 'POST',
			url: 'create_cv/add_section',
			data: data,
			dataType: 'JSON',
			success: function(data) {

				if (data.error) {
					$("#cv_name_error").html(data.message);
					$("#error").show();
				} else {
					$("#sub_section_preview").html("");
					$("#sub_section_preview").hide();
					$("#poinsts_preview").html("");
					$("#poinsts_preview").hide();

					subSections = [];
					points = [];
					$('#title').val("");
					$('#sub_title').val("");
					$('#paragraph').val("");
					$('#point').val("");

					getSectionsByCV();

				}
			}
		})
	}

	viewSubSections();
}

function getSectionsByCV() {

	if (cvId != null) {
		$.ajax({
			type: 'POST',
			url: 'create_cv/get_sections',
			data: { id: cvId },
			dataType: 'JSON',
			success: function(data) {

				if (data.error) {
					$("#cv_name_error").html(data.message);
					$("#error").show();
				} else {
					sections = data.data;
					position = data.data.length;
					generatePreview();
				}
			}
		})
	}
}

function deleteSection(id) {

	$.ajax({
		type: 'POST',
		url: 'create_cv/delete_section',
		data: { section_id: id },
		dataType: 'JSON',
		success: function(data) {

			if (data.error) {
				$("#cv_name_error").html(data.message);
				$("#error").show();
			} else {
				getSectionsByCV();
			}
		}
	})
}

function positionUP(current, id) {
	$.ajax({
		type: 'POST',
		url: 'create_cv/change_position',
		data: { cv_id: cvId, section_id: id, cur_pos: current, change_pos: (current - 1) },
		dataType: 'JSON',
		success: function(data) {

			if (data.error) {
				$("#cv_name_error").html(data.message);
				$("#error").show();
			} else {
				getSectionsByCV();
			}
		}
	})
}

function positionDown(current, id) {
	$.ajax({
		type: 'POST',
		url: 'create_cv/change_position',
		data: { cv_id: cvId, section_id: id, cur_pos: current, change_pos: (current + 1) },
		dataType: 'JSON',
		success: function(data) {

			if (data.error) {
				$("#cv_name_error").html(data.message);
				$("#error").show();
			} else {
				getSectionsByCV();
			}
		}
	})
}

