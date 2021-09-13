/**
 * 
 */

window.onload = function() {
	$("#error").hide();
	$("#success").hide();
	getAllCVs();
};

function getAllCVs() {
	$.ajax({
		type: 'POST',
		url: 'cv_list/get_all_cvs',
		dataType: 'JSON',
		success: function(data) {

			if (data.error) {
				$("#error").html(data.message);
				$("#error").show();
			} else {
				displayData(data.data);
			}
		}
	})
}

function displayData(data) {
	var st = "";

	data.forEach((el, i) => {
		st += `<tr><th scope="row">` + el.cvId + `</th>`
			+ `<td>` + el.cvName + `</td>`
			+ `<td>` + el.date + `</td>`
			+ `<td><button class="btn btn-success btn-sm" onclick="viewCV(` + el.cvId + `)">View</button> <button class="btn btn-danger btn-sm" onclick="deleteCV(` + el.cvId + `)">Delete</button></td>`
			+ `</tr>`;
	})

	$("#rows").html(st);
}

function viewCV(id) {
	window.location.href = "create_cv?id=" + id;
}

function deleteCV(id) {
	$.ajax({
		type: 'POST',
		url: 'cv_list/delete_cv',
		data: { cv_id: id },
		dataType: 'JSON',
		success: function(data) {

			if (data.error) {
				$("#error").html(data.message);
				$("#error").show();
				$("#success").hide();
			} else {
				$("#success").html(data.message);
				$("#success").show();
				$("#error").hide();
				getAllCVs();
			}
		}
	})
}