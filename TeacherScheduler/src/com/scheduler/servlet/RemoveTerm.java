package com.scheduler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class RemoveTerm
 */
@WebServlet( "/pages/removeTerm" )
public class RemoveTerm
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveTerm()
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

        DBManager dbm = new DBManager();
        dbm.removeTerm( termId );

        response.sendRedirect( "getTeacherCalendar" );
    }

}
