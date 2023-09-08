package lk.ijse.hostel1.dao.custom;

import lk.ijse.hostel1.dao.CrudDAO;
import lk.ijse.hostel1.entity.Reservation;
import lk.ijse.hostel1.entity.Room;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
    List<String> roomIds();
}
