<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Wybór czatu</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body class="mailsBody">
		<div>
			<form action="chat" method="post" id="mailForm">
	            <br>Czat:
	            <select name="chatId">
			      		<%int chatsSize = Integer.parseInt( session.getAttribute("listOfChatsSize").toString());
		       	  		for(int j = 0; j < chatsSize; j++){
		          		%>
				  			<option value="<%=session.getAttribute("chatId"+j)%>"><%=session.getAttribute("chatName"+j)%></option>
				  		<%} %>
				</select>
	          <!--  <input name="chatId" type="text"/>-->
	            <br>
	            <div>
		            <input type="submit" name="submit" class="submit" value="Dołącz" />
	            </div>
	       	</form>
       	</div>
</body>
</html>