package com.scheduler.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class AddNewTerm
 */
@WebServlet( "/pages/addNewMail" )
public class AddNewMail
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewMail()
    {
        super();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
    	String senderId = "";
        if ( request.getSession().getAttribute( "userLogin" ) != null )
        {
        	senderId = (String) request.getSession().getAttribute( "userLogin" );
        }
        else
        {
        	senderId = new String( request.getParameter( "senderId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        }
    	String receiverId = new String( request.getParameter( "receiverId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
    	String message = new String( request.getParameter( "message" ).getBytes( "ISO-8859-1" ), "UTF-8" );
    	Date d = new Date();
    	String date=d.toString();
    	
        System.out.println( senderId + " => " + receiverId + " | "+date.toString() +" \n"+ message );

        DBManager dbm = new DBManager();

        dbm.addNewMail(senderId, receiverId, message, date);


        response.sendRedirect( "getSendersMails" );
    }
}

