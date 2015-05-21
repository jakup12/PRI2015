package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "FILEUPLOAD" )
public class FileUpload
{
    @Id
    @Column( name = "FILE_ID" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int fileId;

    @Column( name = "FILE_NAME" )
    private String fileName;

    @Column( name = "GROUP_ID" )
    private String groupId;

    public int getFileId()
    {
        return fileId;
    }

    public void setFileId( int fileId )
    {
        this.fileId = fileId;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName( String fileName )
    {
        this.fileName = fileName;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId( String groupId )
    {
        this.groupId = groupId;
    }

}
