function callBack(instanseName){
	switch(instanseName){
	case "指导老师下发任务书":
		xia_taskbook();
		break;
	case "学生完善任务书":
		wan_taskbook();
		break;
	case "指导老师审核任务书":
		shen_taskbook();
		break;
	case "指导小组组长填写任务书审核意见":
		Taskbook_Section();
		break;
	case "学生上传开题报告":
		report_opening();
		break;
	case "学生完成进展情况记录（前期准备阶段）":
		RecordProgress_1_Student();
		break;
	case "指导老师填写进展情况意见（前期准备阶段）":
		RecordProgress_1_Teacher();
		break;
	case "学生完成进展情况记录（撰写阶段）":
		RecordProgress_2_Student();
		break;
	case "指导老师填写进展情况意见（撰写阶段）":
		RecordProgress_2_Teacher();
		break;
	case "学生完成进展情况记录（中期自查阶段）":
		RecordProgress_3_Student();
		break;
	case "指导老师填写进展情况意见（中期自查阶段）":
		RecordProgress_3_Teacher();
		break;
	case "学生完成进展情况记录（完善阶段）":
		RecordProgress_4_Student();
		break;
	case "指导老师填写进展情况意见（完善阶段）":
		RecordProgress_4_Teacher();
		break;
	case "学生完成个人学习工作总结":
		summary_student();
		break;
	case "指导老师填写个人学习工作总结意见":
		summary_teacher();
		break;
	case "学生提交答辩论文":
		dissertation();
		break;
	case "指导老师填写形式审查表":
		examination_formal_teacher();
		break;
	case "指导老师填写评价审阅表":
		evaluate_tutor();
		break;
		
	}
}