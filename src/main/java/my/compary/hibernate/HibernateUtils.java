package my.compary.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtils {

    public static SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();

    }



    public static void inEnd(){
        sessionFactory.close();
    }
}
