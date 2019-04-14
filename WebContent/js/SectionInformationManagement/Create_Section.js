function Create_Section() {

	var input_section_name = document.getElementById("input_section_name");
	var select_section_leader = document
			.getElementById("select_section_leader");

	/*
	 * 
	 */
	if (input_section_name.value.length == 0) {
		toastr.error("教研室名不能为空");
		return;
	}

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {

				window.location = "/bysjglxt/section/SectionInformationManagement_SectionManagementPage";

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST",
			"/bysjglxt/section/SectionInformationManagement_CreateSection");

	formData.append("newSection.section_name", input_section_name.value);

	if (!select_section_leader.value == "") {
		formData.append("newSection.section_leader",
				select_section_leader.value);
	}

	xhr.send(formData);
}