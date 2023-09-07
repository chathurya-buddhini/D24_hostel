package lk.ijse.gdse.orm.hostel.bo;


import lk.ijse.gdse.orm.hostel.bo.custom.impl.ReservationBoImpl;
import lk.ijse.gdse.orm.hostel.bo.custom.impl.RoomBoImpl;
import lk.ijse.gdse.orm.hostel.bo.custom.impl.StudentBoImpl;
import lk.ijse.gdse.orm.hostel.bo.custom.impl.UserBoImpl;

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
        STUDENT_BO,ROOM_BO,USER_BO,RESERVATION_BO
    }

    public static SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT_BO:
                return new StudentBoImpl();
            case ROOM_BO:
                return new RoomBoImpl();
            case RESERVATION_BO:
                return new ReservationBoImpl();
            case USER_BO:
                return new UserBoImpl();
            default:
                return null;
        }
    }
}
