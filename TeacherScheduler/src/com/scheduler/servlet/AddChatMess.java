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
@WebServlet( "/pages/addChatMess" )
public class AddChatMess
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChatMess()
    {
        super();

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
        	userId = new String( request.getParameter( "userLogin" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        }
        
    	String chatId = new String( request.getParameter( "chatId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
    	String message = new String( request.getParameter( "message" ).getBytes( "ISO-8859-1" ), "UTF-8" );
    	
       // System.out.println( senderId + " => " + receiverId + " | "+date.toString() +" \n"+ message );

        DBManager dbm = new DBManager();

        dbm.addChatMess(userId, chatId, message);

        //request.getSession().setAttribute( "chatId", chatId);
        response.sendRedirect( "chat.jsp" );
    }
}

