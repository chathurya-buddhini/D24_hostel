package lk.ijse.hostel1.bo;

import lk.ijse.hostel1.bo.custom.impl.RevervationBOImpl;
import lk.ijse.hostel1.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostel1.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostel1.bo.custom.impl.UserBOImpl;

public class BOFactory {

    public static BOFactory boFactory;
    public BOFactory() {
    }

    public BOFactory  getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory ();
        }
        return boFactory;
    }

    public enum BOTypes{
        STUDENT,ROOM,USER,RESERVATION
    }

    public static SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return new StudentBOImpl ();
            case ROOM:
                return new RoomBOImpl ();
            case RESERVATION:
                return new RevervationBOImpl ();
            case USER:
                return new UserBOImpl ();
            default:
                return null;
        }
    }

}
