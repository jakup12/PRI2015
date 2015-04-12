package com.scheduler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class SignIn
 */
@WebServlet( "/pages/signIn" )
public class SignIn
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn()
    {
        super();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String termId = new String( request.getParameter( "termId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        String userId = (String) request.getSession().getAttribute( "userLogin" );

        DBManager dbm = new DBManager();
        dbm.assignStudentToTerm( termId, userId );

        response.sendRedirect( "mainPage.jsp" );
    }
}
