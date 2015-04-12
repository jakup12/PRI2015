package com.scheduler.login;

import com.scheduler.hibernate.run.DBManager;

/**
 * 
 */
public class LoginService
{
    private String userId, password;

    public LoginService( String userId, String password )
    {
        this.userId = userId;
        this.password = password;
    }

    public boolean authenticate()
    {
        DBManager dbm = new DBManager();
        String userPassword = null;

        try
        {
            userPassword = dbm.getUser( userId ).getPassword();
        }
        catch ( NullPointerException e )
        {
            return false;
        }

        // weryfikacja has³a
        if ( password.equals( userPassword ) )
        {

            return true;
        }
        else
        {
            return false;
        }

    }
}
