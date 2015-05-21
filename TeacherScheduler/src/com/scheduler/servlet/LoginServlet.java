package com.scheduler.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.dto.User;
import com.scheduler.hibernate.run.DBManager;
import com.scheduler.login.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet( "/pages/login" )
public class LoginServlet
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        doPost( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String userId, password;

        userId = new String( request.getParameter( "userName" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        password = new String( request.getParameter( "password" ).getBytes( "ISO-8859-1" ), "UTF-8" );

        LoginService loginService = new LoginService( userId, password );

        boolean loginResult = loginService.authenticate();

        if ( loginResult == true )
        {

            // pobieram dane u�ytkownika na późniejsze potrzeby
            DBManager dbm = new DBManager();
            User user = dbm.getUser( userId );

            // ustawienie w sesji zalogowanego użytkownika
            request.getSession().setAttribute( "userLogin", userId );
            // ustawiam dane użytkownika w sesji
            request.getSession().setAttribute( "userName", user.getUserName() );
            request.getSession().setAttribute( "userSurname", user.getUserSurname() );
            request.getSession().setAttribute( "isTeacher", user.isTeacher() );
            request.getSession().setAttribute( "email", user.getEmail() );

            // jeśli student pobieram listę wykładowców
            if ( !user.isTeacher() )
            {
                List<User> listOfTeachers = dbm.getListOfUsers( true );
                request.getSession().setAttribute( "listOfTeachersSize", listOfTeachers.size() );

                User teacher = null;
                for ( int i = 0; i < listOfTeachers.size(); i++ )
                {
                    teacher = listOfTeachers.get( i );
                    request.getSession().setAttribute( "teacherLogin" + i, teacher.getUserId() );
                    request.getSession().setAttribute( "teacherFullName" + i, teacher.getUserName() + " " + teacher.getUserSurname() );
                }
            }

            response.sendRedirect( "mainPage.jsp" );
        }
        else
        {
            System.out.println( "Błędne dane lub nieaktywne konto." );
            response.sendRedirect( "loginPage.jsp" );
        }

    }
}
