function Initialization_examination_formal_teacher() {

	var banner_examination_formal_teacher = document
			.getElementById("banner_examination_formal_teacher");
	banner_examination_formal_teacher.click();
}

function examination_formal_teacher() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师填写形式审查表';
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

				var examination_formal = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab18");
				tab.innerHTML = '';
				
				// 创建input
				/*
				 * 
				 */
				var textarea_3 = document.createElement("textarea");
				textarea_3.id = 'student_id_lunwen_record_progress';
				textarea_3.style = "display:none;"
				textarea_3.innerHTML = examination_formal.examination_formal_student;
				tab.appendChild(textarea_3);
				/*
				 * 
				 */
				var textarea_4 = document.createElement("textarea");
				textarea_4.id = 'student_id_lunwen_record_progress_is_xiazai';
				textarea_4.style = "display:none;"
					textarea_4.innerHTML=1;
//				textarea_2.innerHTML = taskbook.taskbook_xia_file_xiazai;
				tab.appendChild(textarea_4);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '毕业论文上传(修改)：';
				tab.appendChild(h4);
				/*
				 * 
				 */
				var div = document.createElement("div");
				div.id = "div_lunwen_recordprogress_box";
				tab.appendChild(div);
				if (examination_formal.dissertation_teacher_file != null) {
					var div_2 = document.createElement("div");
					div_2.id = "upload_button_id";
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addLunWen()">上传</button>';
					tab.appendChild(div_2);
					if (examination_formal.dissertation_teacher_file != '') {
						//TODO 在这里将数量置为空
						num_taskbook = 0;
						lunwenFileChange(examination_formal.dissertation_teacher_file);
					}

				} else {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addLunWen()">上传</button>';
					tab.appendChild(div_2);
				}
				
				
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'examination_formal_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = examination_formal.examination_formal_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var table = document.createElement("table");
				table.className = 'table table-bordered table-hover';
				table.style = ""
				table.innerHTML = '<tbody></tbody>';
				tab.appendChild(table);
				var new_tr = null;
				var new_td = null;
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '封面填写完整正确（含外文题目）';
				new_tr.appendChild(new_td);
				// 
				new_td = document.createElement("td");
				new_td.style = "width:250px;text-align: center;";
				if (examination_formal.examination_formal_is_cover == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_cover" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_cover" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_cover" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_cover == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_cover" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_cover" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_cover" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_cover == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_cover" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_cover" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_cover"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}

				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '文稿为A4打印稿';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_a4 == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_a4" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_a4" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_a4" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_a4 == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_a4" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_a4" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_a4" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_a4 == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_a4" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_a4" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_a4"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '文字编排格式规范统一';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_format == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_format" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_format" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_format" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_format == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_format" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_format" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_format" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_format == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_format" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_format" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_format"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '有中文摘要（含中文关键词）';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_abstract_chinese == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_abstract_chinese == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_abstract_chinese == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_chinese"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '中文关键词为3-5个';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_chinese_keyword == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_chinese_keyword == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_chinese_keyword == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chinese_keyword"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */

				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '有目录(标题文字、页码与内文相符)';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_catalog == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_catalog" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_catalog" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_catalog" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_catalog == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_catalog" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_catalog" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_catalog" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_catalog == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_catalog" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_catalog" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_catalog"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);

				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '有外文摘要';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_abstract_foreign == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_abstract_foreign == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_abstract_foreign == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_abstract_foreign"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '正文有小标题';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_headline == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_headline" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_headline" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_headline" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_headline == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_headline" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_headline" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_headline" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_headline == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_headline" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_headline" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_headline"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '正文标点符号使用正确';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_punctuation == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_punctuation" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_punctuation" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_punctuation" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_punctuation == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_punctuation" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_punctuation" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_punctuation" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_punctuation == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_punctuation" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_punctuation" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_punctuation"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '正文无错别字或病句';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_typo == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_typo" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_typo" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_typo" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_typo == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_typo" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_typo" checked="checked" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_typo" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_typo == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_typo" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_typo" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_typo"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '参考文献有10篇以上';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_reference_ten == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_ten" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_ten" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_ten" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_ten == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_ten" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_ten" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_ten" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_ten == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_ten" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_ten" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chart"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '参考文献含有外文资料';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_reference_foreign == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_foreign == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_foreign == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_foreign" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_foreign"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '参考文献含有最新期刊资料';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_reference_new == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_new" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_new" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_new" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_new == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_new" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_new" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_new" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_new == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_new" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_new" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_new"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '参考文献序号在文中有标注';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_reference_num == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_num" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_num" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_num" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_num == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_num" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_num" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_num" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_num == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_num" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_num" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_num"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '参考文献格式规范';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_reference_format == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_format" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_format" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_format" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_format == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_format" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_format" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_format" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_reference_format == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_reference_format" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_format" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_reference_format"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '过程进展情况记录含中期自查';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_progress_metaphase == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_metaphase == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_metaphase == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_metaphase"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '过程进展中含有个人工作总结';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_progress_summary == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_summary" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_summary" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_summary" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_summary == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_summary" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_summary" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_summary" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_summary == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_summary" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_summary" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_summary"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '过程进展中反映了学生的实际工作';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_progress_actual == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_actual" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_actual" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_actual" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_actual == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_actual" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_actual" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_actual" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_actual == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_actual" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_actual" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_actual"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '过程进展情况记录填写完整';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_progress_complete == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_complete" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_complete" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_complete" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_complete == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_complete" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_complete" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_complete" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_complete == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_complete" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_complete" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_complete"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '过程进展情况记录内容无逻辑错误';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_progress_logic == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_logic" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_logic" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_logic" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_logic == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_logic" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_logic" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_logic" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_progress_logic == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_progress_logic" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_logic" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_progress_logic"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '图表设计与绘制规范';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_chart == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_chart" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chart" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chart" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_chart == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_chart" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chart" checked="checked"value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chart" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_chart == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_chart" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chart" value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_chart"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				new_tr = document.createElement("tr");
				table.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr";
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				new_td.innerHTML = '附带了光盘等程序载体或电子稿已复制至学院';
				new_tr.appendChild(new_td);
				//
				new_td = document.createElement("td");
				new_td.style = "text-align: center;";
				if (examination_formal.examination_formal_is_enclosure == 1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_enclosure" checked="checked" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_enclosure"  value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio"  name="examination_formal_is_enclosure" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_enclosure == 0) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input  type="radio" name="examination_formal_is_enclosure" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_enclosure" checked="checked"  value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio"  name="examination_formal_is_enclosure" value="-1">'
							+ '无' + '</label>';
				} else if (examination_formal.examination_formal_is_enclosure == -1) {
					new_td.innerHTML = '<label style="margin:0 10px;">'
							+ '<input type="radio" name="examination_formal_is_enclosure" value="1">'
							+ '是'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_enclosure"  value="0">'
							+ '否'
							+ '</label> '
							+ ' <label style="margin:0 10px;" >'
							+ '<input type="radio" name="examination_formal_is_enclosure"  checked="checked" value="-1">'
							+ '无' + '</label>';
				}
				new_tr.appendChild(new_td);
				/*
				 * 
				 */
				$('input').iCheck({
					checkboxClass : 'icheckbox_square-blue',
					radioClass : 'iradio_square-blue',
					increaseArea : '20%',
				});
				var ulSave = document.createElement("ul");
				ulSave.className='pager wizard';
				var liSave = document.createElement("li");
				liSave.id='button_SaveGraduationProject';
				var aSave = document.createElement('a');
				aSave.href='####';
				aSave.onclick = Save_examination_formal_teacher
				aSave.text='提交更新';
				liSave.appendChild(aSave);
				ulSave.appendChild(liSave);
				tab.appendChild(ulSave);
				/*
				 * 
				 * 让不是现在进行的流程的不可编辑
				 * 
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				var div_lunwen_recordprogress_del = document.getElementById("div_lunwen_recordprogress_del");
				var upload_button = document.getElementById("upload_button_id");
				if ('-1' == k) {
					if ("指导老师填写形式审查表" != current_processDefinitionName) {
						button_SaveGraduationProject.style.display = "none";
						$('input').iCheck('disable');
						upload_button.style.display = "none";
						div_lunwen_recordprogress_del.style.display = "none";
					} else if (userStudentDTO != null) {
						if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_lunwen_recordprogress_del.style.display = "block";
						} else {
							$('input').iCheck('disable');
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_lunwen_recordprogress_del.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_lunwen_recordprogress_del.style.display = "block";
						} else {
							$('input').iCheck('disable');
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_lunwen_recordprogress_del.style.display = "none";
						}
					} else {
						$('input').iCheck('disable');
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_lunwen_recordprogress_del.style.display = "none";
					}
				}else if('1' == k) {
					if (userStudentDTO != null) {
						if (_userId_Task == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_lunwen_recordprogress_del.style.display = "block";
						} else {
							$('input').iCheck('disable');
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_lunwen_recordprogress_del.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (_userId_Task == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_lunwen_recordprogress_del.style.display = "block";
						} else {
							$('input').iCheck('disable');
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_lunwen_recordprogress_del.style.display = "none";
						}
					} else {
						$('input').iCheck('disable');
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_lunwen_recordprogress_del.style.display = "none";
					}
				}
				
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_ExaminationFormal");

	xhr.send(formData);
}