package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "USERTABLE" )
public class User
{
    @Id
    @Column( name = "USERID" )
    private String userId;

    @Column( name = "USERNAME" )
    private String userName;

    @Column( name = "USERSURNAME" )
    private String userSurname;

    @Column( name = "USERPASSWORD" )
    private String password;

    @Column( name = "ISTEACHER" )
    boolean isTeacher;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getUserSurname()
    {
        return userSurname;
    }

    public void setUserSurname( String userSurname )
    {
        this.userSurname = userSurname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public boolean isTeacher()
    {
        return isTeacher;
    }

    public void setTeacher( boolean isTeacher )
    {
        this.isTeacher = isTeacher;
    }
}
