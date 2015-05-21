package com.scheduler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.dto.Group;
import com.scheduler.hibernate.dto.GroupUser;
import com.scheduler.hibernate.dto.User;
import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class GetTeacherGroups
 */
@WebServlet( "/pages/getTeacherGroups" )
public class GetTeacherGroups
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeacherGroups()
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

        DBManager dbm = new DBManager();

        // pobieram listê grup i dodajê do sesji
        List<Group> listOfGroups = new ArrayList<Group>();

        listOfGroups = dbm.getGroupsForTeacher( userId );

        request.getSession().setAttribute( "listOfGroupsSize", listOfGroups.size() );

        for ( int i = 0; i < listOfGroups.size(); i++ )
        {
            int groupId = listOfGroups.get( i ).getGroupId();
            request.getSession().setAttribute( "groupId" + i, groupId );
            request.getSession().setAttribute( "groupName" + i, listOfGroups.get( i ).getGroupName() );

            // dla danej grupy pobieram listê studentów
            List<GroupUser> listOfGroupAssignments = new ArrayList<GroupUser>();
            listOfGroupAssignments = dbm.getStudentsForGroup( groupId );

            String studentsHTML = "";
            for ( int x = 0; x < listOfGroupAssignments.size(); x++ )
            {
                User studentObj = dbm.getUser( listOfGroupAssignments.get( x ).getStudentId() );
                studentsHTML = studentsHTML.concat( studentObj.getFullName() ).concat( "<br>" );
            }
            request.getSession().setAttribute( "groupStudents" + i, studentsHTML );
        }

        // pobieram listê studentów i dodajê do sesji
        List<User> listOfStudents = new ArrayList<User>();

        listOfStudents = dbm.getListOfUsers( false );
        request.getSession().setAttribute( "listOfStudentsSize", listOfStudents.size() );

        for ( int j = 0; j < listOfStudents.size(); j++ )
        {
            request.getSession().setAttribute( "studentUserId" + j, listOfStudents.get( j ).getUserId() );
            request.getSession().setAttribute( "studentFullName" + j, listOfStudents.get( j ).getFullName() );
        }

        request.getSession().setAttribute( "userLogin", userId );
        response.sendRedirect( "manageGroups.jsp" );
    }
}
