package lk.ijse.gdse.orm.hostel.bo.custom;

import lk.ijse.gdse.orm.hostel.bo.SuperBO;
import lk.ijse.gdse.orm.hostel.dto.UserDTO;

import java.util.List;

public interface UserBo extends SuperBO {
    boolean saveUser(UserDTO dto);
    UserDTO getUser(String id) throws Exception;
    boolean updateUser(UserDTO dto);
    List<UserDTO> loadAll();
}
