package com.scheduler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.dto.Chat;
import com.scheduler.hibernate.run.DBManager;


/**
 * Servlet implementation class GetSendersMails
 */
@WebServlet( "/pages/ChatConnect" )
public class ChatConnect
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatConnect()
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
    	 DBManager dbm = new DBManager();
    	 List<Chat> listOfChats = new ArrayList<Chat>();

    	 listOfChats = dbm.getListOfChats();
         request.getSession().setAttribute( "listOfChatsSize", listOfChats.size() );

         for ( int j = 0; j < listOfChats.size(); j++ )
         {
             request.getSession().setAttribute( "chatId" + j, listOfChats.get( j ).getChatId() );
             request.getSession().setAttribute( "chatName" + j, listOfChats.get( j ).getChatName() );
         }
    	
    	//filtrowanie czatu po grupach
       response.sendRedirect( "chatConnect.jsp" );
    }
}
