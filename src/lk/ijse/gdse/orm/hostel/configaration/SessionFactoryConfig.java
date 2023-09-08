package lk.ijse.gdse.orm.hostel.configaration;

import lk.ijse.gdse.orm.hostel.entity.Reservation;
import lk.ijse.gdse.orm.hostel.entity.Room;
import lk.ijse.gdse.orm.hostel.entity.Student;
import lk.ijse.gdse.orm.hostel.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {
//
//    private static SessionFactoryConfig factoryConfiguration;
//    private final SessionFactory sessionFactory;
//
//    private SessionFactoryConfig() {
//        sessionFactory = new Configuration()
//
//                .mergeProperties(Utility.getProperties())
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Room.class)
//                .addAnnotatedClass(User.class)
//                .buildSessionFactory();
//    }
//
//
//    public static SessionFactoryConfig getInstance() {
//        return (null == factoryConfiguration)
//                ? factoryConfiguration = new SessionFactoryConfig()
//                : factoryConfiguration;
//    }
//
//    public Session getSession() throws HibernateException {
//        // Opens a new Session through the Session Factory & returns the Session created
//        return sessionFactory.openSession();
//    }
private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.Properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configuration
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class);
        sessionFactory=configuration.setProperties(properties).buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfig) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}