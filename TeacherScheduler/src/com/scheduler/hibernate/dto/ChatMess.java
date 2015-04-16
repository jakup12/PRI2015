package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "CHATMESSTABLE" )
public class ChatMess {

	@Id
    @Column( name = "MESSID" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
    private int messId;
	
    @Column( name = "CHATID" )
    private int chatId;
	
    @Column( name = "USERID" )
    private String userId;
    
    @Column( name = "MESSAGE" )
    private String message;

    public int getMessId()
    {
        return messId;
    }

    public void setMessId( int messId )
    {
        this.messId = messId;
    }
    
    public int getChatId()
    {
        return chatId;
    }

    public void setChatId( int chatId )
    {
        this.chatId = chatId;
    }
    
	public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }
    
    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }
}
