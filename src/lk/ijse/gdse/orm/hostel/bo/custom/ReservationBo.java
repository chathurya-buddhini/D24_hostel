package lk.ijse.gdse.orm.hostel.bo.custom;

import lk.ijse.gdse.orm.hostel.bo.SuperBO;
import lk.ijse.gdse.orm.hostel.dto.ReservationDTO;
import lk.ijse.gdse.orm.hostel.dto.RoomDTO;
import lk.ijse.gdse.orm.hostel.dto.StudentDTO;

import java.util.List;

public interface ReservationBo extends SuperBO {
    List<String> getStudentIds();
    List<String> getRoomIds();
    StudentDTO getStudent(String id);
    RoomDTO getRoom(String id);
    ReservationDTO getRes(String resID);
    boolean updateRoom(RoomDTO dto);
    List<ReservationDTO> loadAllRes();
    boolean saveReservation(ReservationDTO dto);
    boolean updateReservation(ReservationDTO dto);
    boolean deleteReservation(ReservationDTO dto);
    List<ReservationDTO> loadAll();
}
