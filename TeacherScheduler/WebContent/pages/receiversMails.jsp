<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Skrzynka odbiorcza</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body class="mailsBody">
		Zalogowany jako: <%=session.getAttribute("userLogin")%>
       	<br>
      	<br> Odebrane wiadomości
      		<form action="filterTags" method="post">
		            Tagi:<input type="text" name="filterTags" value="<%=session.getAttribute("filterTags")%>"/>
		            <input type="hidden" name="userId" value="<%=session.getAttribute("userLogin")%>"/>
		            <input type="submit" value="Uaktualnij" />
    		   	</form>
    		   	
    		   	
	       <table class="mailsTable" border="1" name="lista">
			   <%int size = Integer.parseInt( session.getAttribute("listOfMailsSize").toString());
			   String a = session.getAttribute("filterTags").toString();
		       for(int i = 0; i < size; i++){
		    	   String t = session.getAttribute("tags"+i).toString();
		    	   if( (a=="") || t.contains(a) ){
		       %>
		       	   <tr>
			       <td class="mailsCell">Nadawca: <%=session.getAttribute("senderId"+i)%>
					       <form action="setTags" method="post">
					            Tagi:<input type="text" name="tags" value="<%=session.getAttribute("tags"+i)%>"/>
					            <input type="hidden" name="userId" value="<%=session.getAttribute("userLogin")%>"/>
					            <input type="hidden" name="mailId" value="<%=session.getAttribute("mailId"+i)%>"/>
					            <input type="submit" value="Uaktualnij" />
			    		   	</form>
			    		   Data: <%=session.getAttribute("date"+i)%>
			    		   <br><%=session.getAttribute("message"+i)%>
			       	   <br>
			       </td>
 
		       	   </tr>
		       <%}}%>
	       </table>
	       
</body>
</html>