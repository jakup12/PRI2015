package com.scheduler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class AddStudentToGroup
 */
@WebServlet( "/pages/addStudentToGroup" )
public class AddStudentToGroup
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentToGroup()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
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
        String studentUserId = new String( request.getParameter( "studentUserId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        String groupId = new String( request.getParameter( "groupId" ).getBytes( "ISO-8859-1" ), "UTF-8" );

        DBManager dbm = new DBManager();

        dbm.addStudentToGroup( groupId, studentUserId );

        response.sendRedirect( "getTeacherGroups" );
    }

}
