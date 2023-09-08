package lk.ijse.gdse.orm.hostel.bo.custom.impl;

import lk.ijse.gdse.orm.hostel.bo.custom.RoomBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dao.DAOFactory;
import lk.ijse.gdse.orm.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse.orm.hostel.dto.RoomDTO;
import lk.ijse.gdse.orm.hostel.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomBoImpl implements RoomBo {
    private Session session;
    RoomDAO roomDAO=(RoomDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.ROOMDAO);

    @Override
    public List<RoomDTO> loadAll() {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        roomDAO.setSession (session);
        List<Room>list= roomDAO.loadAll ();
        List<RoomDTO>roomList= new ArrayList<>();

        for (Room room:list) {
            roomList.add(
                    new RoomDTO (
                            room.getRoom_id (),
                            room.getRoom_type (),
                            room.getMoney (),
                            room.getQTY ()
                    )
            );
        }

        return roomList;

    }

    @Override
    public boolean saveRoom(RoomDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            roomDAO.setSession (session);
            roomDAO.save (new Room (
                    dto.getRoom_id (),
                    dto.getRoom_type (),
                    dto.getMoney (),
                    dto.getQTY ()
            ));
            transaction.commit ();
            session.close ();
            return true;

        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
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
    public boolean deleteRoom(RoomDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            roomDAO.setSession (session);
            roomDAO.delete (new Room (
                    dto.getRoom_id (),
                    dto.getRoom_type (),
                    dto.getMoney (),
                    dto.getQTY ()
            ));
            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public RoomDTO getRoom(String id) throws Exception {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        roomDAO.setSession (session);
        Room r=roomDAO.getObject (id);
        session.close ();
        return new RoomDTO (
                r.getRoom_id (),
                r.getRoom_type (),
                r.getMoney (),
                r.getQTY ()
        );
    }
}
