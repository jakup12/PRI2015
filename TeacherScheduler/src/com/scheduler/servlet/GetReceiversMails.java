package com.scheduler.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.dto.Term;
import com.scheduler.hibernate.dto.User;
import com.scheduler.hibernate.dto.Mail;
import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class GetReceiversMails
 */
@WebServlet( "/pages/getReceiversMails" )
public class GetReceiversMails
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReceiversMails()
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
        String receiverId = "";
        if ( request.getSession().getAttribute( "userLogin" ) != null )
        {
        	receiverId = (String) request.getSession().getAttribute( "userLogin" );
        }
        else
        {
        	receiverId = new String( request.getParameter( "userLogin" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        }

        DBManager dbm = new DBManager();
        List<Mail> listOfMails = dbm.getMailsForReceiver( receiverId);

        Mail mail = null;
        request.getSession().setAttribute( "listOfMailsSize", listOfMails.size() );

        for ( int i = 0; i < listOfMails.size(); i++ )
        {
            mail = listOfMails.get( i );
            request.getSession().setAttribute( "senderId" + i , mail.getSenderId());
            request.getSession().setAttribute( "message" + i , mail.getMessage());
            request.getSession().setAttribute( "date" + i , mail.getDate());

        }

        response.sendRedirect( "receiversMails.jsp" );
    }
}
