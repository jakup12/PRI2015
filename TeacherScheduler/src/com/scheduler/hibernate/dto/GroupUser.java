package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "GROUPUSERTABLE" )
public class GroupUser
{
    @Id
    @Column( name = "GROUPUSER_ID" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int groupUserId;

    @Column( name = "GROUP_ID" )
    private int groupId;

    @Column( name = "STUDENT_ID" )
    private String studentId;

    public int getGroupUserId()
    {
        return groupUserId;
    }

    public void setGroupUserId( int groupUserId )
    {
        this.groupUserId = groupUserId;
    }

    public int getGroupId()
    {
        return groupId;
    }

    public void setGroupId( int groupId )
    {
        this.groupId = groupId;
    }

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId( String studentId )
    {
        this.studentId = studentId;
    }

}
