/**
 * 
 */
package com.scheduler.hibernate.run;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.Query;
import org.hibernate.Session;

import com.scheduler.hibernate.dto.Chat;
import com.scheduler.hibernate.dto.ChatMess;
import com.scheduler.hibernate.dto.FileUpload;
import com.scheduler.hibernate.dto.Group;
import com.scheduler.hibernate.dto.GroupUser;
import com.scheduler.hibernate.dto.Mail;
import com.scheduler.hibernate.dto.Term;
import com.scheduler.hibernate.dto.User;
import com.scheduler.servlet.SendEmail;

/**
 * GÂłĂłwna klasa zarzÂądzajÂąca operacjami na bazie danych
 */
public class DBManager
{

    /* konstruktor */

    public DBManager()
    {
    }

    // pobieranie obiektu uÂżytkownika
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

    // weryfikacja czy obiekt uÂżytkownika jest juÂż w BD
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

    // wstawienie nowego obiektu uÂżytkownika
    public void insertUser( String userId, String password, String name, String surname, boolean isTeacher, String email )
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
            if ( email == null || email.isEmpty() )
            {
                user.setEmail( "BRAK" );
            }
            else
            {
                user.setEmail( email );
            }

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

    // pobranie terminĂłw z kalendarza wykÂładowcy po podanym loginie
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

    // pobieranie listy wszystkich wykÂładowcĂłw
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

    // usuniĂŞcie terminu z BD - po ID
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
            mail.setTags( " " );
            session.beginTransaction();
            session.save( mail );

            session.getTransaction().commit();

            User user = new User();
            String email;
            try
            {
                session.beginTransaction();
                user = (User) session.get( User.class, receiverId );
                email = user.getEmail();
                session.getTransaction().commit();

                try
                {
                    new SendEmail().send( email, "[KOM] Nowa wiadomoĹ›Ä‡ od " + senderId, message );
                }
                catch ( MessagingException e )
                {
                    e.printStackTrace();
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }

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

    // wstawianie nowej grupy
    public void insertGroup( String groupName, String teacherName )
    {
        Session session = HibernateManager.getFactory().openSession();

        try
        {
            Group group = new Group();

            group.setGroupName( groupName );
            group.setGroupCount( 0 );
            group.setTeacherName( teacherName );

            session.beginTransaction();
            session.save( group );

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

    // pobieranie grup dla wykÂładowcy
    public List<Group> getGroupsForTeacher( String userId )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<Group> listOfGroups = new ArrayList<Group>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM Group g WHERE g.teacherName = :userId order by g.groupName asc";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "userId", userId );
            listOfGroups = query.list();

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
        return listOfGroups;
    }

    public void addStudentToGroup( String groupId, String studentUserId )
    {
        Session session = HibernateManager.getFactory().openSession();

        try
        {
            // sprawdzam czy student jest juÂż w podanej grupie
            List<GroupUser> checkGroupUser = new ArrayList<GroupUser>();
            String hqlQuery = "FROM GroupUser gu WHERE gu.groupId = :groupId AND gu.studentId = :studentUserId";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "groupId", Integer.parseInt( groupId ) );
            query.setParameter( "studentUserId", studentUserId );
            checkGroupUser = query.list();

            if ( checkGroupUser.size() == 0 )
            {
                GroupUser groupUser = new GroupUser();

                groupUser.setGroupId( Integer.parseInt( groupId ) );
                groupUser.setStudentId( studentUserId );

                session.beginTransaction();
                session.save( groupUser );
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
                session.close();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    public List<GroupUser> getStudentsForGroup( int groupId )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<GroupUser> listOfStudents = new ArrayList<GroupUser>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM GroupUser gu WHERE gu.groupId = :groupId";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "groupId", groupId );
            listOfStudents = query.list();

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
        return listOfStudents;
    }

    // pobieranie listy wszystkich wykĂ…â€šadowcĂ�Âłw
    public List<User> getListOfUsers( boolean isTeacher )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<User> listOfTeachers = new ArrayList<User>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM User u WHERE u.isTeacher = :isTeacher";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "isTeacher", isTeacher );
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

    // //
    public List<ChatMess> getChat( int chatId )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<ChatMess> listOfMess = new ArrayList<ChatMess>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM ChatMess m WHERE m.chatId = :chatId";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "chatId", chatId );
            listOfMess = query.list();

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
        return listOfMess;
    }

    public void addChatMess( String userId, String chatId, String message )
    {
        Session session = HibernateManager.getFactory().openSession();

        try
        {
            ChatMess mess = new ChatMess();

            mess.setUserId( userId );
            mess.setChatId( Integer.parseInt( chatId ) );
            mess.setMessage( message );
            session.beginTransaction();
            session.save( mess );
            // System.out.println("add mess");
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

    public void addChat( int groupId, String teacherName, String chatName )
    {
        Session session = HibernateManager.getFactory().openSession();
        /*
         * boolean free = true; Chat returnedChat = null; try { session.beginTransaction(); returnedChat = (Chat)
         * session.get( Chat.class, groupId ); session.getTransaction().commit(); } catch ( Exception e ) {
         * e.printStackTrace(); } finally { try { if ( session != null ) { session.close(); } } catch ( Exception ex ) {
         * ex.printStackTrace(); } } if (returnedChat!=null) { free = false; } if (free) {
         * System.out.println("Czat nie istnieje"); }else{ System.out.println("Czat istnieje"); } session =
         * HibernateManager.getFactory().openSession();
         */

        try
        {
            // if (free) {
            Chat chat = new Chat();

            chat.setGroupId( groupId );
            chat.setTeacherId( teacherName );
            chat.setChatName( chatName );

            session.beginTransaction();
            session.save( chat );

            session.getTransaction().commit();
            // }

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

    public List<Chat> getListOfChats()
    {
        Session session = HibernateManager.getFactory().openSession();
        List<Chat> listOfChats = new ArrayList<Chat>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM Chat u WHERE u.chatId > 0";
            Query query = session.createQuery( hqlQuery );
            // query.setParameter( "isTeacher", isTeacher );
            listOfChats = query.list();

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
        return listOfChats;
    }

    // pobieranie listy wszystkich grup
    public List<Group> getAllGroups()
    {
        Session session = HibernateManager.getFactory().openSession();
        List<Group> listOfGroups = new ArrayList<Group>();

        try
        {
            session.beginTransaction();

            String hqlQuery = "FROM Group";
            Query query = session.createQuery( hqlQuery );
            listOfGroups = query.list();

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
        return listOfGroups;
    }

    public void addFileForGroup( String fileName, String groupId )
    {

        Session session = HibernateManager.getFactory().openSession();

        try
        {
            FileUpload fileUpload = new FileUpload();
            fileUpload.setFileName( fileName );
            fileUpload.setGroupId( groupId );

            session.beginTransaction();
            session.save( fileUpload );

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

    public void setEmailForUser( String userId, String email )
    {
        Session session = HibernateManager.getFactory().openSession();
        User user = new User();

        try
        {
            session.beginTransaction();
            user = (User) session.get( User.class, userId );
            if ( email == null || email.isEmpty() )
            {
                user.setEmail( "BRAK" );
            }
            else
            {
                user.setEmail( email );
            }
            session.update( user );
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

    public void setTagsForMail( int mailId, String tags )
    {
        Session session = HibernateManager.getFactory().openSession();
        Mail mail = new Mail();

        try
        {
            session.beginTransaction();
            mail = (Mail) session.get( Mail.class, mailId );
            if ( tags == null || tags.isEmpty() )
            {
                mail.setTags( " " );
            }
            else
            {
                mail.setTags( tags );
            }
            session.update( mail );
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

    // pobieranie grupy studenta
    public List<GroupUser> getGroupForStudent( String userId )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<GroupUser> listOfGU = new ArrayList<GroupUser>();

        try
        {
            session.beginTransaction();
            String hqlQuery = "SELECT distinct g FROM GroupUser g WHERE studentId=:studentId";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "studentId", userId );
            listOfGU = query.list();

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
        return listOfGU;
    }

    // pobieranie plików dla grupy
    public List<FileUpload> getFilesForGroup( int groupId )
    {
        Session session = HibernateManager.getFactory().openSession();
        List<FileUpload> listOfFiles = new ArrayList<FileUpload>();

        try
        {
            session.beginTransaction();
            String hqlQuery = "FROM FileUpload WHERE groupId=:groupId";
            Query query = session.createQuery( hqlQuery );
            query.setParameter( "groupId", Integer.toString( groupId ) );
            listOfFiles = query.list();

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
        return listOfFiles;
    }
}
