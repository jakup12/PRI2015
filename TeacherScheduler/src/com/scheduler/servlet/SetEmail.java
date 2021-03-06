package com.scheduler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class
 */
@WebServlet( "/pages/setEmail" )
public class SetEmail
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetEmail()
    {
        super();

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        doPost( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String userId = "";
        if ( request.getSession().getAttribute( "userLogin" ) != null )
        {
            userId = (String) request.getSession().getAttribute( "userLogin" );
        }
        else
        {
            userId = new String( request.getParameter( "userId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        }

        String email = new String( request.getParameter( "email" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        DBManager dbm = new DBManager();
        

        dbm.setEmailForUser(userId, email);
        request.getSession().setAttribute( "email", email );

        response.sendRedirect( "mainPage.jsp" );
    }
}
