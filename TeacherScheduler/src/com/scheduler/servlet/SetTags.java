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
@WebServlet( "/pages/setTags" )
public class SetTags
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetTags()
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

        int mailId = Integer.parseInt(new String( request.getParameter( "mailId" ).getBytes( "ISO-8859-1" ), "UTF-8" ));
        String tags = new String( request.getParameter( "tags" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        DBManager dbm = new DBManager();
        

        dbm.setTagsForMail(mailId, tags);
        request.getSession().setAttribute( "tags", tags );

        response.sendRedirect( "mainPage.jsp" );
    }
}
