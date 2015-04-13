package com.scheduler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class AddGroup
 */
@WebServlet( "/pages/addGroup" )
public class AddGroup
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGroup()
    {
        super();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String groupName = new String( request.getParameter( "groupName" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        String userId = new String( request.getParameter( "userId" ).getBytes( "ISO-8859-1" ), "UTF-8" );

        DBManager dbm = new DBManager();

        dbm.insertGroup( groupName, userId );

        response.sendRedirect( "getTeacherGroups" );
    }

}
