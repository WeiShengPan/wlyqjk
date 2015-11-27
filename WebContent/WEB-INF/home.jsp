<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="servletaction" method="get">
		关键字:<input type="text" name="key">
		页数:<input type="text" name="pages">
		选择媒体:
		<select name="option">
			<option value="baidu">百度搜索</option>
			<option value="baidunews">百度新闻</option>
			<option value="tianya">天涯社区</option>
		</select>
		<input type="submit" value="submit">
	</form>
</body>
</html>