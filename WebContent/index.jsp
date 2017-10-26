<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/test/emp_test">test</a>
	<form action="${pageContext.request.contextPath}/test/emp_add"
		method="post">
		ID:<input name="tt.id" type="text"><br> Content:<input
			name="tt.content" type="text"><br>
		<button>提交</button>
	</form>
	<br>
</body>
</html>