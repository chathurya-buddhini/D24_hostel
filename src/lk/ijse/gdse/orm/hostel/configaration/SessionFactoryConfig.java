package lk.ijse.gdse.orm.hostel.configaration;

import lk.ijse.gdse.orm.hostel.entity.Reservation;
import lk.ijse.gdse.orm.hostel.entity.Room;
import lk.ijse.gdse.orm.hostel.entity.Student;
import lk.ijse.gdse.orm.hostel.entity.User;
import lk.ijse.gdse.orm.hostel.util.Utility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        sessionFactory = new Configuration()

                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }


    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }

    public Session getSession() throws HibernateException {
        // Opens a new Session through the Session Factory & returns the Session created
        return sessionFactory.openSession();
    }
}