package lk.ijse.hostel1.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.hostel1.bo.SuperBO;
import lk.ijse.hostel1.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> loadAll();
    boolean saveStudent(StudentDTO studentDTO);
    boolean updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(StudentDTO studentDTO);
    StudentDTO getStudent(String id) throws Exception;

    int getStudnetCount();


}
