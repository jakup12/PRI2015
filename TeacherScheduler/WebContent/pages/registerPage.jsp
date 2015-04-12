<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title> Rejestracja : TeacherScheduler</title>
<link rel="stylesheet" type="text/css" href="style/style.css">    
</head>

<body class="registerPage">
		<h3>Rejestracja</h3>
		<form action="register" method="post">
			<table>
			<tr>
				<td class="rTab">Login użytkownika:</td>
				<td><input name="reg_userId" type="text" id="reg_userName"/></td>
			</tr>
			<tr>
				<td class="rTab">Hasło:</td>
				<td><input type="password" name="reg_password" id="reg_password" /></td>
			</tr>
			<tr>
				<td class="rTab">Imię:</td>
				<td><input type="text" name="reg_name" id="reg_name"></td>
			</tr>
			<tr>
				<td class="rTab">Nazwisko:</td>
				<td><input type="text" name="reg_surname" id="reg_surname"></td>
			</tr>
            <tr>
				<td colspan="2">
				    <input type="radio" name="reg_isTeacher" value="TRUE"> Wykładowca 
					<input type="radio" name="reg_isTeacher" value="FALSE"> Student
				</td>
			</tr>
			<tr>
				<td colspan="2">
				    <div>
			            <input type="submit" name="submit" class="submit" value="Zapisz" />
			            <input type="reset" name="reset" value="Wyczyść" />
            		</div>
				</td>
			</tr>
            </table>
       </form>

</body>
</html>
        