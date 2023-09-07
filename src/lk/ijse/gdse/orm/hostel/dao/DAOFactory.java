package lk.ijse.gdse.orm.hostel.dao;

import lk.ijse.gdse.orm.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.gdse.orm.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.gdse.orm.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse.orm.hostel.dao.custom.impl.UserDAOimpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory ();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        STUDENTDAO,ROOMDAO,USERDAO,RESERVATIONDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENTDAO:
                return new StudentDAOImpl ();
            case ROOMDAO:
                return new RoomDAOImpl ();
            case RESERVATIONDAO:
                return new ReservationDAOImpl ();
            case USERDAO:
                return new UserDAOimpl();
            default:
                return null;
        }
    }
}
