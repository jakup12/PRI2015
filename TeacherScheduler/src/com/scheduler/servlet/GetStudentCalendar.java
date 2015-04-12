package com.scheduler.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scheduler.hibernate.dto.Term;
import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class GetStudentCalendar
 */
@WebServlet( "/pages/selectTeacher" )
public class GetStudentCalendar
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentCalendar()
    {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String teacherId = new String( request.getParameter( "teacherId" ).getBytes( "ISO-8859-1" ), "UTF-8" );
        boolean isAssigned = true;

        DBManager dbm = new DBManager();
        List<Term> listOfTerms = dbm.getTermsForTeacher( teacherId, isAssigned );

        Term term = null;
        request.getSession().setAttribute( "listOfTermsSize", listOfTerms.size() );

        for ( int i = 0; i < listOfTerms.size(); i++ )
        {
            term = listOfTerms.get( i );
            request.getSession().setAttribute( "termId" + i, term.getTermId() );
            request.getSession().setAttribute( "termDate" + i, term.getTermDate() );
            request.getSession().setAttribute( "termHour" + i, term.getTermHour() );
        }

        response.sendRedirect( "studentCalendar.jsp" );
    }
}
