package com.scheduler.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.scheduler.hibernate.dto.ChatMess;
import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class GetSendersMails
 */
@WebServlet( "/pages/chat" )
public class Chatt
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chatt()
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
               
        int chatId;
        if ( request.getSession().getAttribute( "chatId" ) != null )
        {
        	chatId = (int) request.getSession().getAttribute( "chatId" );
        }
        else
        {
        	chatId = Integer.parseInt(new String( request.getParameter( "chatId" ).getBytes( "ISO-8859-1" ), "UTF-8" ));
        }

        DBManager dbm = new DBManager();
        List<ChatMess> listOfMess = dbm.getChat( chatId);

        ChatMess mess = null;
        request.getSession().setAttribute( "listOfMessSize", listOfMess.size() );

        for ( int i = 0; i < listOfMess.size(); i++ )
        {
        	mess = listOfMess.get( i );
            request.getSession().setAttribute( "userId" + i , mess.getUserId());
            request.getSession().setAttribute( "message" + i , mess.getMessage());

        }

        response.sendRedirect( "chat.jsp" );
    }
}
