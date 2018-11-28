function Initialization_evaluate_review() {

	var banner_evaluate_review = document
			.getElementById("banner_evaluate_review");
	banner_evaluate_review.click();
}

function evaluate_review() {
	document.getElementById("GraduationProjectTitle").innerHTML = '评阅老师填写评阅审查表';
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				console.debug("评阅老师填写评阅审查表：" + xhr.responseText);
				var evaluate_review = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab20");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'evaluate_review_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = evaluate_review.evaluate_review_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var table = document.createElement("table");
				table.className = 'table table-bordered table-hover';
				table.style = ""
				table.innerHTML = '<tbody><input id="evaluate_review_student" style="display:none;" value="'
						+ evaluate_review.evaluate_review_student
						+ '"/></tbody>';
				tab.appendChild(table);
				var new_tr = null;
				var new_td = null;
				/*
				 * 
				 * 
				 * 
				 * 
				 */

				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '考核项目';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '满分';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '得分';
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '选题符合专业培养目标，体现综合训练基本要求';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '6';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_training_objective) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()" class="form-control" id="evaluate_review_grade_training_objective" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_training_objective" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_training_objective" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_training_objective" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_training_objective" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_training_objective" >'
							+ '<option value="0">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5"  selected="selected">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_training_objective" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '</select>';
					break;
				}

				}

				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '题目难易度（只分难、适中、容易）';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '4';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_difficulty) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_difficulty" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_difficulty" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_difficulty" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_difficulty" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_difficulty" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '</select>';
					break;
				}

				}

				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '题目工作量（只分大、适中、小）';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '5';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_workload) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_workload" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_workload" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_workload" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_workload" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_workload" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_workload" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '</select>';
					break;
				}

				}

				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '题目与生产、科研、实验室建设等实际的结合程度';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '5';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_bind) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_bind" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_bind" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_bind" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_bind" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_bind" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_bind" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '</select>';
					break;
				}

				}

				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '综合运用知识';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '8';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_comprehensive) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 7: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7" selected="selected">7</option>'
							+ '<option value="8">8</option>' + '</select>';
					break;
				}
				case 8: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_comprehensive" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>'
							+ '<option value="8" selected="selected">8</option>'
							+ '</select>';
					break;
				}

				}

				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '查阅文献资料及资料应用';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '7';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_reference) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 7: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_reference" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7" selected="selected">7</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '实验设计';
				new_tr.appendChild(new_td);
				// 	
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '6';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_experimental_design) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 7: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_experimental_design" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7" selected="selected">7</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '计算能力';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '6';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_computing) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computing" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computing" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computing" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computing" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computing" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computing" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computing" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '外语应用';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '6';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_foreign_language) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_foreign_language" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_foreign_language" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_foreign_language" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_foreign_language" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_foreign_language" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_foreign_language" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_foreign_language" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '</select>';
					break;
				}

				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '计算机应用';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '6';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_computer) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computer" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computer" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computer" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computer" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computer" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computer" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_computer" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '创新';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '7';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_innovate) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 7: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_innovate" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7" selected="selected">7</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '对实验结果的分析能力（或综合分析能力、技术经济分析能力）';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '7';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_analysis) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 7: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_analysis" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7" selected="selected">7</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '插图（或图纸）质量';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '6';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_chart) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_chart" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_chart" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_chart" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_chart" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_chart" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_chart" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_chart" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '设计说明书撰写水平';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '7';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_instructions) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 7: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_instructions" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7" selected="selected">7</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '设计的实用性与科学性';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '6';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_practicability) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_practicability" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_practicability" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_practicability" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_practicability" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_practicability" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_practicability" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_practicability" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '</select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '设计规范化程度';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '7';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				switch (evaluate_review.evaluate_review_grade_normalization) {
				case 0: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0" selected="selected">0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 1: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0">0</option>'
							+ '<option value="1"  selected="selected">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 2: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2" selected="selected">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 3: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3" selected="selected">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 4: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4" selected="selected">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 5: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5" selected="selected">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 6: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6" selected="selected">6</option>'
							+ '<option value="7">7</option>' + '</select>';
					break;
				}
				case 7: {
					new_td.innerHTML = '<select onchange="change_evaluate_review_grade_total()"  class="form-control" id="evaluate_review_grade_normalization" >'
							+ '<option value="0" >0</option>'
							+ '<option value="1">1</option>'
							+ '<option value="2">2</option>'
							+ '<option value="3">3</option>'
							+ '<option value="4">4</option>'
							+ '<option value="5">5</option>'
							+ '<option value="6">6</option>'
							+ '<option value="7" selected="selected">7</option>'
							+ '<	/select>';
					break;
				}
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '总分';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '100';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '<input id="evaluate_review_grade_total" disabled="disabled" value="'
						+ evaluate_review.evaluate_review_grade_total
						+ '" class="form-control" style="text-align: center;"/>';
				new_tr.appendChild(new_td);
				/*
				 * 
				 * 
				 * 
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '评阅教师评语：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = "evaluate_review_teacher_comment";
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:400px;"
				if (evaluate_review.evaluate_review_teacher_comment != null) {
					textarea_1.innerHTML = evaluate_review.evaluate_review_teacher_comment;
				} else {
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				/*
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 */
				// change_evaluate_review_grade_total();
				/*
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 让不是现在进行的流程的不可编辑
				 * 
				 * 
				 * 
				 * 
				 * 
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("评阅老师填写评阅审查表" != current_processDefinitionName
						|| userStudentDTO != null) {
					document
							.getElementById("evaluate_review_grade_training_objective").disabled = "disabled";
					document.getElementById("evaluate_review_grade_difficulty").disabled = "disabled";
					document.getElementById("evaluate_review_grade_workload").disabled = "disabled";
					document.getElementById("evaluate_review_grade_bind").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_comprehensive").disabled = "disabled";
					document.getElementById("evaluate_review_grade_reference").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_experimental_design").disabled = "disabled";
					document.getElementById("evaluate_review_grade_computing").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_foreign_language").disabled = "disabled";
					document.getElementById("evaluate_review_grade_computer").disabled = "disabled";
					document.getElementById("evaluate_review_grade_innovate").disabled = "disabled";
					document.getElementById("evaluate_review_grade_analysis").disabled = "disabled";
					document.getElementById("evaluate_review_grade_chart").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_instructions").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_practicability").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_normalization").disabled = "disabled";
					textarea_1.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				} else if (userTeacherDTO != null) {
					if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						document
								.getElementById("evaluate_review_grade_training_objective").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_difficulty").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_workload").disabled = "disabled";
						document.getElementById("evaluate_review_grade_bind").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_comprehensive").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_reference").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_experimental_design").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_computing").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_foreign_language").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_computer").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_innovate").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_analysis").disabled = "disabled";
						document.getElementById("evaluate_review_grade_chart").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_instructions").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_practicability").disabled = "disabled";
						document
								.getElementById("evaluate_review_grade_normalization").disabled = "disabled";
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				} else {
					document
							.getElementById("evaluate_review_grade_training_objective").disabled = "disabled";
					document.getElementById("evaluate_review_grade_difficulty").disabled = "disabled";
					document.getElementById("evaluate_review_grade_workload").disabled = "disabled";
					document.getElementById("evaluate_review_grade_bind").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_comprehensive").disabled = "disabled";
					document.getElementById("evaluate_review_grade_reference").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_experimental_design").disabled = "disabled";
					document.getElementById("evaluate_review_grade_computing").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_foreign_language").disabled = "disabled";
					document.getElementById("evaluate_review_grade_computer").disabled = "disabled";
					document.getElementById("evaluate_review_grade_innovate").disabled = "disabled";
					document.getElementById("evaluate_review_grade_analysis").disabled = "disabled";
					document.getElementById("evaluate_review_grade_chart").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_instructions").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_practicability").disabled = "disabled";
					document
							.getElementById("evaluate_review_grade_normalization").disabled = "disabled";
					textarea_1.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_EvaluateReview");
	xhr.send(formData);
}

function change_evaluate_review_grade_total() {
	var evaluate_review_grade_total = document
			.getElementById("evaluate_review_grade_total");
	var total = parseInt(document
			.getElementById("evaluate_review_grade_training_objective").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_difficulty").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_workload").value)
			+ parseInt(document.getElementById("evaluate_review_grade_bind").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_comprehensive").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_reference").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_experimental_design").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_computing").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_foreign_language").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_computer").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_innovate").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_analysis").value)
			+ parseInt(document.getElementById("evaluate_review_grade_chart").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_instructions").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_practicability").value)
			+ parseInt(document
					.getElementById("evaluate_review_grade_normalization").value);
	evaluate_review_grade_total.value = total;
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var evaluate_review_teacher_comment = document
						.getElementById("evaluate_review_teacher_comment");
				evaluate_review_teacher_comment.value = xhr.responseText;
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	formData.append("updateEvaluateReview.evaluate_review_id", document
			.getElementById("evaluate_review_id").value);
	formData.append("updateEvaluateReview.evaluate_review_student", document
			.getElementById("evaluate_review_student").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_training_objective",
					document
							.getElementById("evaluate_review_grade_training_objective").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_difficulty",
			document.getElementById("evaluate_review_grade_difficulty").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_workload",
			document.getElementById("evaluate_review_grade_workload").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_bind", document
			.getElementById("evaluate_review_grade_bind").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_comprehensive",
					document
							.getElementById("evaluate_review_grade_comprehensive").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_reference",
			document.getElementById("evaluate_review_grade_reference").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_experimental_design",
					document
							.getElementById("evaluate_review_grade_experimental_design").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_computing",
			document.getElementById("evaluate_review_grade_computing").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_foreign_language",
					document
							.getElementById("evaluate_review_grade_foreign_language").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_computer",
			document.getElementById("evaluate_review_grade_computer").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_innovate",
			document.getElementById("evaluate_review_grade_innovate").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_analysis",
			document.getElementById("evaluate_review_grade_analysis").value);

	formData.append("updateEvaluateReview.evaluate_review_grade_chart",
			document.getElementById("evaluate_review_grade_chart").value);

	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_instructions",
					document
							.getElementById("evaluate_review_grade_instructions").value);

	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_practicability",
					document
							.getElementById("evaluate_review_grade_practicability").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_normalization",
					document
							.getElementById("evaluate_review_grade_normalization").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_total",
			document.getElementById("evaluate_review_grade_total").value);
	formData.append("updateEvaluateReview.evaluate_review_teacher_comment",
			document.getElementById("evaluate_review_teacher_comment").value);
	xhr
			.open(
					"POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_generateReviewGraduationComment");

	xhr.send(formData);

}