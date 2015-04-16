package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "CHATTABLE" )
public class Chat {

	@Id
    @Column( name = "CHATID" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
    private int chatId;
	
    @Column( name = "TEACHERID" )
    private String teacherId;
	
    @Column( name = "GROUPID" )
    private int groupId;

    
    public int getChatId()
    {
        return chatId;
    }

    public void setChatId( int chatId )
    {
        this.chatId = chatId;
    }
    
	public String getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId( String teacherId )
    {
        this.teacherId = teacherId;
    }
    
    public int getGroupId()
    {
        return groupId;
    }

    public void setGroupId( int groupId )
    {
        this.groupId = groupId;
    }
}
