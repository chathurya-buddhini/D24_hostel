package lk.ijse.hostel1.bo.custom;

import lk.ijse.hostel1.bo.SuperBO;
import lk.ijse.hostel1.dto.RoomDTO;
import lk.ijse.hostel1.dto.StudentDTO;
import lk.ijse.hostel1.dto.UserDTO;

import java.util.List;

public interface UserBO  extends SuperBO {
    boolean saveUser(UserDTO dto);
    UserDTO getUser(String id) throws Exception;
    boolean updateUser(UserDTO dto);
    List<UserDTO> loadAll();
}
