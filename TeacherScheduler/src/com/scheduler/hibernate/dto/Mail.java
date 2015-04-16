package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "MAILSTABLE" )
public class Mail {

	@Id
    @Column( name = "MAILID" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
    private int mailId;
	
    @Column( name = "SENDERID" )
    private String senderId;
	
    @Column( name = "RECEIVERID" )
    private String receiverId;

    @Column( name = "MESSAGE" )
    private String message;

    @Column( name = "DATE" )
    private String date;
    
    public int getMailId()
    {
        return mailId;
    }

    public void setMailId( int mailId )
    {
        this.mailId = mailId;
    }
    
	public String getSenderId()
    {
        return senderId;
    }

    public void setSenderId( String senderId )
    {
        this.senderId = senderId;
    }
    
    public String getReceiverId()
    {
        return receiverId;
    }

    public void setReceiverId( String receiverId )
    {
        this.receiverId = receiverId;
    }
    
    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }
    
    public String getDate()
    {
        return date;
    }

    public void setDate( String date )
    {
        this.date = date;
    }
}
