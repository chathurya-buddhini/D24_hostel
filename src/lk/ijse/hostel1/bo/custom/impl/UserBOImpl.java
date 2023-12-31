package lk.ijse.hostel1.bo.custom.impl;

import lk.ijse.hostel1.bo.custom.UserBO;
import lk.ijse.hostel1.dao.DAOFactory;
import lk.ijse.hostel1.dao.custom.StudentDAO;
import lk.ijse.hostel1.dao.custom.UserDAO;
import lk.ijse.hostel1.dto.RoomDTO;
import lk.ijse.hostel1.dto.StudentDTO;
import lk.ijse.hostel1.dto.UserDTO;
import lk.ijse.hostel1.entity.Room;
import lk.ijse.hostel1.entity.Student;
import lk.ijse.hostel1.entity.User;
import lk.ijse.hostel1.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserBOImpl implements UserBO {

    private Session session;
    UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            userDAO.setSession (session);
            String id=userDAO.save (new User (
                    dto.getUserId (),
                    dto.getUserName (),
                    dto.getPassword ()));
            transaction.commit ();
            session.close ();
            if (id!=null){
                return true;
            }
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public UserDTO getUser(String id) throws Exception {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDAO.setSession (session);
        User u=userDAO.getObject (id);
        session.close ();
        return new UserDTO (
               u.getUserId (),
                u.getUserName (),
                u.getPassword ()
        );
    }

    @Override
    public boolean updateUser(UserDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            userDAO.setSession (session);
            userDAO.update (new User (
                    dto.getUserId (),
                    dto.getUserName (),
                    dto.getPassword ()
            ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public List<UserDTO> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDAO.setSession (session);
        List<User>list= userDAO.loadAll ();
        List<UserDTO>userList= new ArrayList<> ();

        for (User u:list) {
            userList.add(
                    new UserDTO (
                            u.getUserId (),
                            u.getUserName (),
                            u.getPassword ()
            ));
        }

        return userList;

    }
}
