package lk.ijse.gdse.orm.hostel.bo.custom.impl;

import lk.ijse.gdse.orm.hostel.bo.custom.UserBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dao.DAOFactory;
import lk.ijse.gdse.orm.hostel.dao.custom.UserDAO;
import lk.ijse.gdse.orm.hostel.dto.UserDTO;
import lk.ijse.gdse.orm.hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    private Session session;
    UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.USERDAO);
    @Override
    public boolean saveUser(UserDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            userDAO.setSession (session);
            String id=userDAO.save (new User(
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
        session= SessionFactoryConfig.getInstance ().getSession ();
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
        session= SessionFactoryConfig.getInstance ().getSession ();
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