<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Nowa Wiadomość</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body class="mailsBody">
		<div>
			<form action="addNewMail" method="post" id="mailForm">
				<br>Odbiorca:
	            <input name="receiverId" type="text"/>
	            <br>Wiadomość:
	            <br>
	            <input name="message" type="text"/>
	            <br>
	            <input name="senderId" type="hidden" value="<%=session.getAttribute("userLogin")%>"/>
	            <br>
	            <div>
		            <input type="submit" name="submit" class="submit" value="Wyślij" />
	            </div>
	       	</form>
       	</div>
</body>
</html>