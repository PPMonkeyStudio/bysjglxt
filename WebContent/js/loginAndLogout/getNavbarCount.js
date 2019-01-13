(function getNavbarTutorCount() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var bu = document.getElementById("graduation_tutor");
				bu.innerHTML = '我指导的学生('+xhr.responseText+')'
			} else {
			}
		}
	}
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_getGraduationTutorCount");
	formData.append("state",1);
	xhr.send(formData);
})();
;(function getNavbarReviewCount() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var bu = document.getElementById("graduation_review");
				bu.innerHTML = '我评阅的学生('+xhr.responseText+')'
			} else {
			}
		}
	}
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_getGraduationTutorCount");
	formData.append("state",2);
	xhr.send(formData);
})()