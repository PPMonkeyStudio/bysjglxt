(function getDaiFinish() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var dai_list = JSON.parse(xhr.responseText);
				var dai_table = document.getElementById("daiFinishTask");
				for(var num = 0;num<dai_list.length;num++){
					//移除数据
					var new_tr;
					new_tr = document.createElement("tr");
					dai_table.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					//
					new_td = document.createElement("td");
					if(dai_list[num].launchName == 'admin'){
						new_tr.style.color="red";
					}
					new_td.innerHTML = (num+1)+'. ' + dai_list[num].bysjglxt_notice.notice_content;
					new_tr.appendChild(new_td);
				}
			} else {
			}
		}
	}
	
	var formData = new FormData();
	formData.append("leixing",2);
	xhr.open("POST","/bysjglxt/notice/NoticeManagement_getTaskByLiXing");
	xhr.send(formData);
})();
;(function getYiFinish() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var dai_list = JSON.parse(xhr.responseText);
				var dai_table = document.getElementById("yiFinishTask");
				for(var num = 0;num<dai_list.length;num++){
					//移除数据
					var new_tr;
					new_tr = document.createElement("tr");
					dai_table.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					//
					new_td = document.createElement("td");
					if(dai_list[num].launchName == 'admin'){
						new_tr.style.color="red";
					}
					new_td.innerHTML = (num+1)+'. ' + dai_list[num].bysjglxt_notice.notice_content;
					new_tr.appendChild(new_td);
				}
			} else {
			}
		}
	}
	
	var formData = new FormData();
	formData.append("leixing",1);
	xhr.open("POST","/bysjglxt/notice/NoticeManagement_getTaskByLiXing");
	xhr.send(formData);
})()
;(function getYiFinish() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var dai_list = JSON.parse(xhr.responseText);
				var dai_table = document.getElementById("yiGongTask");
				for(var num = 0;num<dai_list.length;num++){
					//移除数据
					var new_tr;
					new_tr = document.createElement("tr");
					dai_table.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					//
					new_td = document.createElement("td");
					if(dai_list[num].launchName == 'admin'){
						new_tr.style.color="red";
					}
					new_td.innerHTML = (num+1)+'. ' + dai_list[num].bysjglxt_notice.notice_content;
					new_tr.appendChild(new_td);
				}
			} else {
			}
		}
	}
	
	var formData = new FormData();
	formData.append("leixing",4);
	xhr.open("POST","/bysjglxt/notice/NoticeManagement_getTaskByLiXing");
	xhr.send(formData);
})()