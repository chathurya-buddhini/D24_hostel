package lk.ijse.gdse.orm.hostel.dao.custom;

import lk.ijse.gdse.orm.hostel.dao.CrudDAO;
import lk.ijse.gdse.orm.hostel.entity.Room;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
        List<String> roomIds();
        }