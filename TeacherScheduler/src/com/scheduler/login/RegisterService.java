package com.scheduler.login;

import com.scheduler.hibernate.run.DBManager;

/**
 *
 */
public class RegisterService
{
    private String userId, password, name, surname, email;

    private boolean isTeacher;

    public RegisterService( String userId, String password, String name, String surname, boolean isTeacher ,String email)

    {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isTeacher = isTeacher;
        this.email = email;
    }

    public boolean registerUser()
    {
        DBManager dbm = new DBManager();

        // sprawdzenie czy u≈ºytkownik jest w bazie
        boolean exists = dbm.checkIfUserExists( userId );

        if ( exists == true )
        {
            return false;
        }
        else
        {
            // wstawienie uøytkownika
            dbm.insertUser( userId, password, name, surname, isTeacher, email);
            return true;
        }
    }
}
