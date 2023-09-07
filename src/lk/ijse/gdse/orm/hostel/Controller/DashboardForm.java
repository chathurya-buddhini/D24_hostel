package lk.ijse.gdse.orm.hostel.Controller;

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lk.ijse.gdse.orm.hostel.bo.BOFactory;
import lk.ijse.gdse.orm.hostel.bo.custom.ReservationBo;
import lk.ijse.gdse.orm.hostel.bo.custom.RoomBo;
import lk.ijse.gdse.orm.hostel.bo.custom.StudentBo;


public class DashboardForm {
    public Label lblStudent;
    public Label lblRooms;
    public Text txtStudents;
    public Text txtRooms;
    public Text txtReservation;

    RoomBo roomBO = (RoomBo) BOFactory.getBO (BOFactory.BOTypes.ROOM_BO);
    StudentBo studentBO = (StudentBo) BOFactory.getBO (BOFactory.BOTypes.STUDENT_BO);
    ReservationBo reservationBO = (ReservationBo) BOFactory.getBO (BOFactory.BOTypes.RESERVATION_BO);

    public void initialize(){
        txtStudents.setText (String.valueOf (studentBO.loadAll ().size ()));
        txtReservation.setText (String.valueOf (reservationBO.loadAll ().size ()));
        txtRooms.setText (String.valueOf (roomBO.loadAll ().size ()));
    }
}
