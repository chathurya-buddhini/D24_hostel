package lk.ijse.gdse.orm.hostel.dto;

import java.util.Date;
public class ReservationDTO {

    private String id;
    private Date date;
    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
    private String status;

    public ReservationDTO(String id, Date date, StudentDTO studentDTO, RoomDTO roomDTO, String status) {
        this.id = id;
        this.date = date;
        this.studentDTO = studentDTO;
        this.roomDTO = roomDTO;
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", studentDTO=" + studentDTO +
                ", roomDTO=" + roomDTO +
                ", status='" + status + '\'' +
                '}';
    }
}
