package com.scheduler.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadFile
 */
@WebServlet( "/pages/downloadFile" )
public class DownloadFile
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFile()
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
        String fileName = new String( request.getParameter( "fileName" ).getBytes( "ISO-8859-1" ), "UTF-8" );

        response.setContentType( "application/octet-stream" );
        response.setHeader( "Content-Disposition", "attachment;filename=" + fileName );

        // ŒCIE¯KA DO PLIKU KTÓR¥ NALE¯Y MODYFIKOWAÆ DLA KA¯DEJ INSTALACJI
        File file = new File( "C:\\TMP_INPUT\\" + fileName );
        FileInputStream fileIn = new FileInputStream( file );
        ServletOutputStream out = response.getOutputStream();

        byte[] outputByte = new byte[4096];
        // copy binary contect to output stream
        while ( fileIn.read( outputByte, 0, 4096 ) != -1 )
        {
            out.write( outputByte, 0, 4096 );
        }
        fileIn.close();
        out.flush();
        out.close();
    }

}
