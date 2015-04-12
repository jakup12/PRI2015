/**
 */
package com.scheduler.hibernate.run;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Klasa obsługująca hibernate
 * 
 * 
 */
public class HibernateManager
{
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getFactory()
    {
        if ( sessionFactory == null )
        {
            try
            {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
