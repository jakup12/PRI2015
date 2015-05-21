package com.scheduler.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.scheduler.hibernate.run.DBManager;

/**
 * Servlet implementation class UploadFileForGroup
 */
@WebServlet( "/pages/uploadFileForGroup" )
public class UploadFileForGroup
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private int maxFileSize = 50 * 1024;

    private int maxMemSize = 4 * 1024;

    private File file;

    private String uploadFileName = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileForGroup()
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
        String filePath = "D:\\TMP_INPUT\\";
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent( request );
        response.setContentType( "text/html" );
        java.io.PrintWriter out = response.getWriter();
        if ( isMultipart )
        {
            System.out.println( "MULTIPART TRUE. LAUNCHING UPLOAD." );
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold( maxMemSize );
        // Location to save data that is larger than maxMemSize.
        factory.setRepository( new File( "D:\\TMP_INPUT\\" ) );

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload( factory );
        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try
        {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest( request );

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            // szukam elementu zawieraj¹cego plik
            while ( i.hasNext() )
            {
                FileItem fi = (FileItem) i.next();
                if ( !fi.isFormField() )
                {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    uploadFileName = fileName;
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    // Write the file
                    if ( fileName.lastIndexOf( "\\" ) >= 0 )
                    {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf( "\\" ) ) );
                    }
                    else
                    {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf( "\\" ) + 1 ) );
                    }
                    fi.write( file );

                    System.out.println( "FILE UPLOADED." );
                }
            }

            // przeszukujê pozosta³e elementy formularza
            Iterator it = fileItems.iterator();
            while ( it.hasNext() )
            {
                FileItem fi = (FileItem) it.next();
                if ( fi.isFormField() )
                {
                    // dodawanie wpisu do bazy
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldname = fi.getFieldName();
                    String fieldvalue = fi.getString();
                    if ( fieldname.compareTo( "groupId" ) == 0 )
                    {
                        DBManager dbm = new DBManager();
                        dbm.addFileForGroup( uploadFileName, fieldvalue );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            System.out.println( ex.getMessage() );
        }

        response.sendRedirect( "getAllGroupsForFile" );
    }
}
