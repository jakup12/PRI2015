package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "GROUPTABLE" )
public class Group
{
    @Id
    @Column( name = "GROUP_ID" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int groupId;

    @Column( name = "GROUP_NAME" )
    private String groupName;

    @Column( name = "GROUP_COUNT" )
    private int groupCount;

    @Column( name = "TEACHER_NAME" )
    private String teacherName;

    public String getTeacherName()
    {
        return teacherName;
    }

    public void setTeacherName( String teacherName )
    {
        this.teacherName = teacherName;
    }

    public int getGroupId()
    {
        return groupId;
    }

    public void setGroupId( int groupId )
    {
        this.groupId = groupId;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    public int getGroupCount()
    {
        return groupCount;
    }

    public void setGroupCount( int groupCount )
    {
        this.groupCount = groupCount;
    }
}
