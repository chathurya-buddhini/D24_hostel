package lk.ijse.hostel1.dao.custom;

import lk.ijse.hostel1.dao.CrudDAO;
import lk.ijse.hostel1.entity.Reservation;
import lk.ijse.hostel1.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {

    List<String> getStIds();
}
