package com.scheduler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class AddNewTerm
 */
@WebServlet( "/pages/addNewTerm" )
public class AddNewTerm
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewTerm()
    {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String date = new String( request.getParameter( "date" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        String hour = new String( request.getParameter( "hour" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        String userId = new String( request.getParameter( "userId" ).getBytes( "ISO-8859-1" ), "UTF-8" );

        System.out.println( date + " | " + hour + " for: " + userId );

        DBManager dbm = new DBManager();

        dbm.addNewTerm( date, hour, userId );

        System.out.println( "Dodano termin " + date + " " + hour + " dla wykladowcy o loginie: " + userId );

        // response.sendRedirect( "mainPage.jsp" );
        response.sendRedirect( "getTeacherCalendar" );
    }
}
