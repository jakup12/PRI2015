<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Zarządzanie plikami : TeacherScheduler</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body >
	    <%int size = Integer.parseInt( session.getAttribute("allFilesSize").toString());
        for(int i = 0; i < size; i++){
        %>
        <%=session.getAttribute("fileName"+i)%>
		<form action="downloadFile" method="post">
			<input type="hidden" name="fileId" value="<%=session.getAttribute("fileId"+i)%>"/>
			<input type="hidden" name="fileName" value="<%=session.getAttribute("fileName"+i)%>"/>
			<input type="submit" value="Pobierz" name=""/>
		</form>
		<br>
		<%}%>
</body>
</html>