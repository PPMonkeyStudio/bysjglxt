function saveGraduationProject() {
	switch (current_processDefinitionName) {
	case "指导老师完成任务书": {
		Save_Taskbook_Teacher();
		break;
	}
	case "教研室主任填写任务书审核意见": {
		Save_Taskbook_Section();
		break;
	}
	case "学生完成开题报告": {
		Save_report_opening();
		break;
	}
	case "学生完成进展情况记录（前期准备阶段）": {
		Save_RecordProgress_1_Student();
		break;
	}
	case "指导老师填写进展情况意见（前期准备阶段）": {
		Save_RecordProgress_1_Teacher();
		break;
	}
	case "学生完成进展情况记录（撰写阶段）": {
		Save_RecordProgress_2_Student();
		break;
	}
	case "指导老师填写进展情况意见（撰写阶段）": {
		Save_RecordProgress_2_Teacher();
		break;
	}
	case "学生完成进展情况记录（中期自查阶段）": {
		Save_RecordProgress_3_Student();
		break;
	}
	case "指导老师填写进展情况意见（中期自查阶段）": {
		Save_RecordProgress_3_Teacher();
		break;
	}
	case "学生完成进展情况记录（完善阶段）": {
		Save_RecordProgress_4_Student();
		break;
	}
	case "指导老师填写进展情况意见（完善阶段）": {
		Save_RecordProgress_4_Teacher();
		break;
	}
	case "学生完成个人学习工作总结": {
		Save_summary_student();
		break;
	}
	case "指导老师填写个人学习工作总结意见": {
		Save_summary_teacher();
		break;
	}
	case "学生提交答辩论文": {
		Save_dissertation();
		break;
	}
	case "指导老师填写形式审查表": {
		Save_examination_formal_teacher();
		break;
	}
	case "领导小组长填写形式审查表(核查)": {
		Save_examination_formal_leader();
		break;
	}
	case "指导老师填写评价审阅表": {
		Save_evaluate_tutor();
		break;
	}
	case "评阅老师填写评阅审查表": {
		Save_evaluate_review();
		break;
	}

	}
}