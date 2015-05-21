package com.scheduler.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.dto.Group;
import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class GetAllGroupsForFile
 */
@WebServlet( "/pages/getAllGroupsForFile" )
public class GetAllGroupsForFile
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllGroupsForFile()
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
        DBManager dbm = new DBManager();
        List<Group> listOfGroups = dbm.getAllGroups();

        request.getSession().setAttribute( "allGroupListSize", listOfGroups.size() );

        for ( int i = 0; i < listOfGroups.size(); i++ )
        {
            request.getSession().setAttribute( "groupId" + i, listOfGroups.get( i ).getGroupId() );
            request.getSession().setAttribute( "groupName" + i, listOfGroups.get( i ).getGroupName() );
        }

        response.sendRedirect( "manageFiles.jsp" );
    }
}
