package lk.ijse.gdse.orm.hostel.bo.custom.impl;

import lk.ijse.gdse.orm.hostel.bo.custom.StudentBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dao.DAOFactory;
import lk.ijse.gdse.orm.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse.orm.hostel.dto.StudentDTO;
import lk.ijse.gdse.orm.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    private Session session;
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.STUDENTDAO);
    @Override
    public List<StudentDTO> loadAll() {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();
        studentDAO.setSession (session);

        List<Student> stList=studentDAO.loadAll ();
        List<StudentDTO> list=new ArrayList<>();
        for (Student student:stList) {
            list.add(
                    new StudentDTO(
                            student.getId (),
                            student.getStudent_name (),
                            student.getStudent_address(),
                            student.getDOB(),
                            student.getGender(),
                            student.getContact ()
                    )
            );
        }

        return list;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            studentDAO.setSession (session);
            String id=studentDAO.save (new Student (
                    dto.getId (),
                    dto.getStudent_name (),
                    dto.getStudent_address (),
                    dto.getDOB (),
                    dto.getGender (),
                    dto.getContact ()));
            transaction.commit ();
            session.close ();
            if (id!=null){
                return true;
            }
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            studentDAO.setSession (session);
            studentDAO.update (new Student (
                    dto.getId (),
                    dto.getStudent_name (),
                    dto.getStudent_address (),
                    dto.getDOB (),
                    dto.getGender (),
                    dto.getContact ()));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            studentDAO.setSession (session);
            studentDAO.delete (new Student (
                    dto.getId (),
                    dto.getStudent_name (),
                    dto.getStudent_address (),
                    dto.getDOB (),
                    dto.getGender (),
                    dto.getContact ()));
            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        studentDAO.setSession (session);
        Student st=studentDAO.getObject (id);
        session.close ();
        return new StudentDTO (
                st.getId (),
                st.getStudent_name (),
                st.getStudent_address (),
                st.getGender (),
                st.getDOB (),
                st.getContact ()
        );
    }

    @Override
    public int getStudnetCount() {
        return 0;
    }
}

