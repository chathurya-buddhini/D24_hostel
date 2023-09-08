package lk.ijse.gdse.orm.hostel.dto;

import java.util.Date;
public class ReservationDTO {

    private String Rs_id;
    private Date date;
    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
    private String status;


    public ReservationDTO(String rs_id, Date date, StudentDTO studentDTO, RoomDTO roomDTO, String status) {
        Rs_id = rs_id;
        this.date = date;
        this.studentDTO = studentDTO;
        this.roomDTO = roomDTO;
        this.status = status;
    }

    public String getRs_id() {
        return Rs_id;
    }

    public void setRs_id(String rs_id) {
        Rs_id = rs_id;
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
                "Rs_id='" + Rs_id + '\'' +
                ", date=" + date +
                ", studentDTO=" + studentDTO +
                ", roomDTO=" + roomDTO +
                ", status='" + status + '\'' +
                '}';
    }
}
