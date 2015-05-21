package com.scheduler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.login.RegisterService;

/**
 * Servlet implementation class Register
 */
@WebServlet( "/pages/register" )
public class Register
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register()
    {
        super();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String userId, password, name, surname, sIsTeacher, email;
        boolean isTeacher;

        userId = new String( request.getParameter( "reg_userId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        password = new String( request.getParameter( "reg_password" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        name = new String( request.getParameter( "reg_name" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        surname = new String( request.getParameter( "reg_surname" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        sIsTeacher = new String( request.getParameter( "reg_isTeacher" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        email = new String( request.getParameter( "reg_email" ).getBytes( "ISO-8859-1" ), "UTF-8" );

        if ( sIsTeacher.compareTo( "TRUE" ) == 0 )
        {
            isTeacher = true;
        }
        else
        {
            isTeacher = false;
        }

        RegisterService registerService = new RegisterService( userId, password, name, surname, isTeacher, email );

        boolean registerResult = registerService.registerUser();

        System.out.println( registerResult );

        response.sendRedirect( "loginPage.jsp" );

    }
}
