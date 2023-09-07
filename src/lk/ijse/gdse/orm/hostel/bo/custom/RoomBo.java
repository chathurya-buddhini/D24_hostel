package lk.ijse.gdse.orm.hostel.bo.custom;

import lk.ijse.gdse.orm.hostel.bo.SuperBO;
import lk.ijse.gdse.orm.hostel.dto.RoomDTO;

import java.util.List;

public interface RoomBo extends SuperBO {
    List<RoomDTO> loadAll();
    boolean saveRoom(RoomDTO dto);
    boolean updateRoom(RoomDTO dto);
    boolean deleteRoom(RoomDTO dto);
    RoomDTO getRoom(String id) throws Exception;
}
