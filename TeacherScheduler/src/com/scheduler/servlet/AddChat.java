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
@WebServlet( "/pages/addChat" )
public class AddChat
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChat()
    {
        super();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        int groupId = Integer.parseInt(new String( request.getParameter( "groupId" ).getBytes( "ISO-8859-1" ), "UTF-8" ));
        String teacherName = new String( request.getParameter( "teacherName" ).getBytes( "ISO-8859-1" ), "UTF-8" );

        DBManager dbm = new DBManager();

        dbm.addChat(groupId, teacherName);

        response.sendRedirect( "getTeacherGroups" );
    }

}
