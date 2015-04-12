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
import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class GetTeacherCalendar
 */
@WebServlet( "/pages/getTeacherCalendar" )
public class GetTeacherCalendar
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeacherCalendar()
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
        List<Term> listOfTerms = dbm.getTermsForTeacher( userId, false );

        Term term = null;
        request.getSession().setAttribute( "listOfTermsSize", listOfTerms.size() );

        for ( int i = 0; i < listOfTerms.size(); i++ )
        {
            term = listOfTerms.get( i );
            request.getSession().setAttribute( "termId" + i, term.getTermId() );
            request.getSession().setAttribute( "termDate" + i, term.getTermDate() );
            request.getSession().setAttribute( "termHour" + i, term.getTermHour() );

            // dopisuję imię i nazwisko studenta
            if ( term.getAssignation().compareTo( "BRAK" ) != 0 )
            {
                User studentObj = dbm.getUser( term.getAssignation() );
                String studentFullName = studentObj.getUserName() + " " + studentObj.getUserSurname();
                request.getSession().setAttribute( "assignation" + i, studentFullName );
            }
            else
            {
                request.getSession().setAttribute( "assignation" + i, term.getAssignation() );
            }
        }

        response.sendRedirect( "teacherCalendar.jsp" );
    }
}
