package lk.ijse.hostel1.bo.custom;

import lk.ijse.hostel1.bo.SuperBO;
import lk.ijse.hostel1.dto.ReservationDTO;
import lk.ijse.hostel1.dto.RoomDTO;
import lk.ijse.hostel1.dto.StudentDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    List<String> getStudentIds();
    List<String> getRoomIds();
     StudentDTO getStudent(String id);
    RoomDTO getRoom(String id);
    ReservationDTO getRes( String resID);
    boolean updateRoom(RoomDTO dto);
    List<ReservationDTO> loadAllRes();
    boolean saveReservation(ReservationDTO dto);
    boolean updateReservation(ReservationDTO dto);
    boolean deleteReservation(ReservationDTO dto);
    List<ReservationDTO> loadAll();

}
