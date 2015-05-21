<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Zarządzanie plikami : TeacherScheduler</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body >
		<form action="uploadFileForGroup" method="post" id="fileForm" enctype="multipart/form-data">
	        <select name="groupId" form="fileForm">
		      <%int size = Integer.parseInt( session.getAttribute("allGroupListSize").toString());
	       	  for(int i = 0; i < size; i++){
	          %>
			 	 <option value="<%=session.getAttribute("groupId"+i)%>"><%=session.getAttribute("groupName"+i)%></option>
			  <%} %>
			</select>
            <div>
	            <input type="file" name="file" size="60" />
	            <br>
	            <input type="submit" value="Dodaj plik" />
            </div>
       	</form>
</body>
</html>