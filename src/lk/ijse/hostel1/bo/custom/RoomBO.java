package lk.ijse.hostel1.bo.custom;

import lk.ijse.hostel1.bo.SuperBO;
import lk.ijse.hostel1.dto.RoomDTO;
import lk.ijse.hostel1.dto.StudentDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> loadAll();
    boolean saveRoom(RoomDTO dto);
    boolean updateRoom(RoomDTO dto);
    boolean deleteRoom(RoomDTO dto);
    RoomDTO getRoom(String id) throws Exception;


}
