package lk.ijse.gdse.orm.hostel.bo.custom.impl;

import lk.ijse.gdse.orm.hostel.bo.custom.ReservationBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dao.DAOFactory;
import lk.ijse.gdse.orm.hostel.dao.custom.ReservationDAO;
import lk.ijse.gdse.orm.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse.orm.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse.orm.hostel.dto.ReservationDTO;
import lk.ijse.gdse.orm.hostel.dto.RoomDTO;
import lk.ijse.gdse.orm.hostel.dto.StudentDTO;
import lk.ijse.gdse.orm.hostel.entity.Reservation;
import lk.ijse.gdse.orm.hostel.entity.Room;
import lk.ijse.gdse.orm.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ReservationBoImpl implements ReservationBo {
    private Session session;
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.STUDENTDAO);
    RoomDAO roomDAO=(RoomDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.ROOMDAO);
    ReservationDAO reservationDAO=(ReservationDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.RESERVATIONDAO);


    @Override
    public List<String> getStudentIds() {
        try{
            session= SessionFactoryConfig.getInstance ().getSession ();
            studentDAO.setSession (session);
            return studentDAO.getStIds ();

        }catch (Exception e){
            session.close ();
            return null;
        }
    }

    @Override
    public List<String> getRoomIds() {
        try{
            session= SessionFactoryConfig.getInstance ().getSession ();
            roomDAO.setSession (session);
            return roomDAO.roomIds ();
        }catch (Exception e){
            session.close ();
            return null;
        }
    }

    @Override
    public StudentDTO getStudent(String id) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        studentDAO.setSession (session);
        try {
            Student st = studentDAO.getObject (id);
            session.close ();
            return new StudentDTO (
                    st.getSt_id (),
                    st.getStudent_name (),
                    st.getStudent_address (),
                    st.getDOB (),
                    st.getGender (),
                    st.getContact ()
            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }
    }

    @Override
    public RoomDTO getRoom(String id) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        roomDAO.setSession (session);
        try {
            Room room=roomDAO.getObject (id);
            session.close ();
            return new RoomDTO (
                    room.getRoom_id (),
                    room.getRoom_type (),
                    room.getMoney (),
                    room.getQTY ()
            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }

    }

    @Override
    public ReservationDTO getRes(String resID) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        reservationDAO.setSession (session);
        try {
            Reservation res = reservationDAO.getObject (resID);
            session.close ();
            return new ReservationDTO (
                    res.getRs_id (),
                    res.getDate (),
                    new StudentDTO (
                            res.getStudent ().getSt_id (),
                            res.getStudent ().getStudent_name (),
                            res.getStudent ().getStudent_address (),
                            res.getStudent ().getDOB (),
                            res.getStudent ().getGender (),
                            res.getStudent ().getContact ()
                    ),
                    new RoomDTO (
                            res.getRoom ().getRoom_id (),
                            res.getRoom ().getRoom_type (),
                            res.getRoom ().getMoney (),
                            res.getRoom ().getQTY ()
                    ),
                    res.getStatus ()
            );


        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }
    }

    @Override
    public boolean updateRoom(RoomDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            roomDAO.setSession (session);
            roomDAO.update (new Room (
                    dto.getRoom_id (),
                    dto.getRoom_type (),
                    dto.getMoney (),
                    dto.getQTY ()
            ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public List<ReservationDTO> loadAllRes() {
        return null;
    }

    @Override
    public boolean saveReservation(ReservationDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            reservationDAO.setSession (session);
            reservationDAO.save (
                    new Reservation (
                            dto.getRs_id (),
                            dto.getDate (),
                            new Student (
                                    dto.getStudentDTO ().getSt_id (),
                                    dto.getStudentDTO ().getStudent_name (),
                                    dto.getStudentDTO ().getStudent_address (),
                                    dto.getStudentDTO ().getContact (),
                                    dto.getStudentDTO ().getGender (),
                                    dto.getStudentDTO ().getDOB ()
                            ),
                            new Room (
                                    dto.getRoomDTO ().getRoom_id (),
                                    dto.getRoomDTO ().getRoom_type (),
                                    dto.getRoomDTO ().getMoney (),
                                    dto.getRoomDTO ().getQTY ()
                            ),
                            dto.getStatus ()
                    ));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            reservationDAO.setSession (session);
            reservationDAO.update (
                    new Reservation (
                            dto.getRs_id (),
                            dto.getDate (),
                            new Student (
                                    dto.getStudentDTO ().getSt_id (),
                                    dto.getStudentDTO ().getStudent_name (),
                                    dto.getStudentDTO ().getStudent_address (),
                                    dto.getStudentDTO ().getDOB (),
                                    dto.getStudentDTO ().getGender(),
                                    dto.getStudentDTO ().getContact ()
                            ),
                            new Room (
                                    dto.getRoomDTO ().getRoom_id (),
                                    dto.getRoomDTO ().getRoom_type (),
                                    dto.getRoomDTO ().getMoney (),
                                    dto.getRoomDTO ().getQTY ()
                            ),
                            dto.getStatus ()
                    ));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean deleteReservation(ReservationDTO dto) {
        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            reservationDAO.setSession(session);
            reservationDAO.delete(
                    new Reservation(
                            dto.getRs_id(),
                            dto.getDate(),
                            new Student(
                                    dto.getStudentDTO ().getSt_id (),
                                    dto.getStudentDTO ().getStudent_name (),
                                    dto.getStudentDTO ().getStudent_address(),
                                    dto.getStudentDTO ().getDOB (),
                                    dto.getStudentDTO ().getGender (),
                                    dto.getStudentDTO ().getContact ()
                            ),
                            new Room(
                                    dto.getRoomDTO ().getRoom_id (),
                                    dto.getRoomDTO ().getRoom_type (),
                                    dto.getRoomDTO ().getMoney (),
                                    dto.getRoomDTO ().getQTY ()
                            ),
                            dto.getStatus ()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }

        return false;
    }

    @Override
    public List<ReservationDTO> loadAll() {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        reservationDAO.setSession (session);
        List<Reservation>list= reservationDAO. loadAll ();
        List<ReservationDTO>resList= new ArrayList<>();
        System.out.println ("Check1");

        for (Reservation res :list) {
            resList.add(new ReservationDTO (
                    res.getRs_id (),
                    res.getDate (),
                    new StudentDTO (
                            res.getStudent ().getSt_id (),
                            res.getStudent ().getStudent_name (),
                            res.getStudent ().getStudent_address (),
                            res.getStudent ().getDOB (),
                            res.getStudent ().getGender (),
                            res.getStudent ().getContact ()
                    ),
                    new RoomDTO (
                            res.getRoom ().getRoom_id (),
                            res.getRoom ().getRoom_type (),
                            res.getRoom ().getMoney (),
                            res.getRoom ().getQTY ()
                    ),
                    res.getStatus ()
            ));
        }

        System.out.println ("Check2");
        return resList;
    }
}
