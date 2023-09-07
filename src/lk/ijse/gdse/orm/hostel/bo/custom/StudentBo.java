package lk.ijse.gdse.orm.hostel.bo.custom;

import lk.ijse.gdse.orm.hostel.bo.SuperBO;
import lk.ijse.gdse.orm.hostel.dto.StudentDTO;

import java.util.List;

public interface StudentBo extends SuperBO {
    List<StudentDTO> loadAll();
    boolean saveStudent(StudentDTO studentDTO);
    boolean updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(StudentDTO studentDTO);
    StudentDTO getStudent(String id) throws Exception;

    int getStudnetCount();

}
