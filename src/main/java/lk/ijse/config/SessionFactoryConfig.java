package lk.ijse.config;



import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig(){

        Properties properties = new Properties();
        try  {
            properties.load(SessionFactoryConfig.class.getResourceAsStream("/hibernate.properties"));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("error encountered while loading the property file");
        }

        sessionFactory = new Configuration().setProperties(properties)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Library.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserRegistration.class)
                .addAnnotatedClass(IssueBook.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance(){
        return (null==factoryConfig) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();

    }

}
