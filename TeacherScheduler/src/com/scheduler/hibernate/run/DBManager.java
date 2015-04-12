/**
 * 
 */
package com.scheduler.hibernate.run;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.scheduler.hibernate.dto.Mail;
import com.scheduler.hibernate.dto.Term;
import com.scheduler.hibernate.dto.User;

/**
 * G��wna klasa zarz�dzaj�ca operacjami na bazie danych
 */
public class DBManager
{

    /* konstruktor */

    public DBManager()
    {
    }

    // pobieranie obiektu u�ytkownika
    public User getUser( String userName )
    {
        Session session = HibernateManager.getFactory().openSession();
        User returnedUser = new User();

        try
        {
            session.beginTransaction();
            returnedUser = (User) session.get( User.class, userName );
            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
        return returnedUser;
    }

    // weryfikacja czy obiekt u�ytkownika jest ju� w BD
    public boolean checkIfUserExists( String userLogin )
    {
        boolean exists = false;
        User returnedUser = new User();

        Session session = HibernateManager.getFactory().openSession();

        try
        {
            session.beginTransaction();
            returnedUser = (User) session.get( User.class, userLogin );
            session.getTransaction().commit();

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {

            try
            {
                session.close();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }

        }

        if ( returnedUser != null )
        {
            exists = true;
        }

        System.out.println( "Hibernate: Check if user exists for userName{" + userLogin + "} - RESULT: " + exists );

        return exists;
    }

    // wstawienie nowego obiektu u�ytkownika
    public void insertUser( String userId, String password, String name, String surname, boolean isTeacher )
    {
        Session session = HibernateManager.getFactory().openSession();

        try
        {
            User user = new User();

            user.setUserId( userId );
            user.setPassword( password );
            user.setUserName( name );
            user.setUserSurname( surname );
            user.setTeacher( isTeacher );

            session.beginTransaction();
            session.save( user );

            session.getTransaction().commit();

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                session.close();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    // dodanie nowego terminu w kalendarzu
    public void addNewTerm( String date, String hour, String userId )
    {
        Session session = HibernateManager.getFactory().openSession();

        try
        {
            Term term = new Term();

            term.setTermDate( date );
            term.setTermHour( hour );
            term.setAssignation( "BRAK" );
            term.setTeacher( userId );

            session.beginTransaction();
            session.save( term );

            session.getTransaction().commit();

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                session.close();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    // pobranie termin�w z kalendarza wyk�adowcy po podanym loginie
    public List<Term> getTermsForTeacher( String userId, boolean notAssignedOnly )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<Term> listOfTerms = new ArrayList<Term>();

        try
        {
            session.beginTransaction();
            if ( !notAssignedOnly )
            {
                String hqlQuery = "FROM Term t WHERE t.teacher = :userId order by t.termDate asc, t.termHour asc";
                Query query = session.createQuery( hqlQuery );
                query.setParameter( "userId", userId );
                listOfTerms = query.list();
            }
            else
            {
                String hqlQuery = "FROM Term t WHERE t.teacher = :userId AND t.assignation = :assignation order by t.termDate asc, t.termHour asc";
                Query query = session.createQuery( hqlQuery );
                query.setParameter( "userId", userId );
                query.setParameter( "assignation", "BRAK" );
                listOfTerms = query.list();
            }

            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
        return listOfTerms;
    }

    // pobieranie listy wszystkich wyk�adowc�w
    public List<User> getListOfTeachers()
    {
        Session session = HibernateManager.getFactory().openSession();
        List<User> listOfTeachers = new ArrayList<User>();

        try
        {
            session.beginTransaction();
            String hqlQuery = "FROM User u WHERE u.isTeacher = :isTeacher";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "isTeacher", true );
            listOfTeachers = query.list();
            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
        return listOfTeachers;
    }

    // usuni�cie terminu z BD - po ID
    public void removeTerm( String termId )
    {
        Session session = HibernateManager.getFactory().openSession();

        try
        {
            session.beginTransaction();
            String hqlQuery = "delete Term t WHERE t.termId = :termId";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "termId", Integer.parseInt( termId ) );
            query.executeUpdate();
            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    // przypisanie studenta do terminu z kalendarza
    public void assignStudentToTerm( String termId, String userId )
    {
        Session session = HibernateManager.getFactory().openSession();
        Term term = new Term();

        try
        {
            session.beginTransaction();
            term = (Term) session.get( Term.class, Integer.parseInt( termId ) );
            term.setAssignation( userId );
            session.update( term );
            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    // ////////

    public Mail getMail( String mailId )
    {
        Session session = HibernateManager.getFactory().openSession();
        Mail returnedMail = new Mail();

        try
        {
            session.beginTransaction();
            returnedMail = (Mail) session.get( Mail.class, mailId );
            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
        return returnedMail;
    }

    public void addNewMail( String senderId, String receiverId, String message, String date )
    {
        Session session = HibernateManager.getFactory().openSession();

        try
        {
            Mail mail = new Mail();

            mail.setSenderId( senderId );
            mail.setReceiverId( receiverId );
            mail.setMessage( message );
            mail.setDate( date );

            session.beginTransaction();
            session.save( mail );

            session.getTransaction().commit();

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                session.close();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    public List<Mail> getMailsForSender( String senderId )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<Mail> listOfMails = new ArrayList<Mail>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM Mail m WHERE m.senderId = :senderId order by m.date desc";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "senderId", senderId );
            listOfMails = query.list();

            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
        return listOfMails;
    }

    public List<Mail> getMailsForReceiver( String receiverId )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<Mail> listOfMails = new ArrayList<Mail>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM Mail m WHERE m.receiverId = :receiverId order by m.date desc";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "receiverId", receiverId );
            listOfMails = query.list();

            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( session != null )
                {
                    session.close();
                }
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
        return listOfMails;
    }
}