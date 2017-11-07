function InitializationMyGraduationProject() {

}

function Taskbook_Teacher() {
	var banner_Taskbook_Teacher = document
			.getElementById("banner_Taskbook_Teacher");
	banner_Taskbook_Teacher.click();
	banner_Taskbook_Teacher.style.color = 'white';
	banner_Taskbook_Teacher.parentNode.style.backgroundColor = '#428bca';
	/*
	 * 
	 */
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert(xhr.responseText);

				var tab1 = document.getElementById("tab1");
				var table = document.createElement("table");
				var tbody = document.createElement("tbody");
				var tr = document.createElement("tr");

				tab1.appendChild(table);
				table.appendChild(tbody);
				tbody.appendChild(tr);

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_Taskbook");

	xhr.send(formData);
}

function Taskbook_Section() {
	var banner_Taskbook_Section = document
			.getElementById("banner_Taskbook_Section");
	banner_Taskbook_Section.click();
	banner_Taskbook_Section.style.color = 'white';
	banner_Taskbook_Section.parentNode.style.backgroundColor = '#428bca';
}