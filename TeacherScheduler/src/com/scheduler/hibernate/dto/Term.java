package com.scheduler.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "TERMSTABLE" )
public class Term
{
    @Id
    @Column( name = "TERM_ID" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int termId;

    @Column( name = "TERM_DATE" )
    private String termDate;

    @Column( name = "TERM_HOUR" )
    private String termHour;

    @Column( name = "TEACHER" )
    String teacher;

    @Column( name = "ASSIGNATION" )
    private String assignation;

    public int getTermId()
    {
        return termId;
    }

    public void setTermId( int termId )
    {
        this.termId = termId;
    }

    public String getTermDate()
    {
        return termDate;
    }

    public void setTermDate( String termDate )
    {
        this.termDate = termDate;
    }

    public String getTermHour()
    {
        return termHour;
    }

    public void setTermHour( String termHour )
    {
        this.termHour = termHour;
    }

    public String getAssignation()
    {
        return assignation;
    }

    public void setAssignation( String assignation )
    {
        this.assignation = assignation;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public void setTeacher( String teacher )
    {
        this.teacher = teacher;
    }

}
