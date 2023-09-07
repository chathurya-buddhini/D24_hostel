package lk.ijse.gdse.orm.hostel.dao.custom;

import lk.ijse.gdse.orm.hostel.dao.CrudDAO;
import lk.ijse.gdse.orm.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse.orm.hostel.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {

    List<String> getStIds();
}