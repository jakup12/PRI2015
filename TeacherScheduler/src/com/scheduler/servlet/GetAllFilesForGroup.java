package com.scheduler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.dto.FileUpload;
import com.scheduler.hibernate.dto.GroupUser;
import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class GetAllFilesForGroup
 */
@WebServlet( "/pages/getAllFilesForGroup" )
public class GetAllFilesForGroup
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllFilesForGroup()
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
        String userId = (String) request.getSession().getAttribute( "userLogin" );
        DBManager dbm = new DBManager();
        List<GroupUser> listOfGU = dbm.getGroupForStudent( userId );
        List<FileUpload> listOfFiles = new ArrayList<FileUpload>();

        for ( GroupUser gu : listOfGU )
        {
            listOfFiles.addAll( dbm.getFilesForGroup( gu.getGroupId() ) );
        }

        request.getSession().setAttribute( "allFilesSize", listOfFiles.size() );

        for ( int i = 0; i < listOfFiles.size(); i++ )
        {
            request.getSession().setAttribute( "fileId" + i, listOfFiles.get( i ).getFileId() );
            request.getSession().setAttribute( "fileName" + i, listOfFiles.get( i ).getFileName() );
        }

        response.sendRedirect( "downloadFile.jsp" );
    }

}
