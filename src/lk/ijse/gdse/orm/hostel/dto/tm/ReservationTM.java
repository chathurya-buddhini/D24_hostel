package lk.ijse.gdse.orm.hostel.dto.tm;

public class ReservationTM {
    private String Rs_id;
    private String Date;
    private String Status;
    private String Room_id;
    private String roomName;
    private String status;

    public ReservationTM() {
    }

    public ReservationTM(String rs_id, String date, String status, String room_id, String roomName, String status1) {
        Rs_id = rs_id;
        Date = date;
        Status = status;
        Room_id = room_id;
        this.roomName = roomName;
        this.status = status1;
    }

    public String getRs_id() {
        return Rs_id;
    }

    public void setRs_id(String rs_id) {
        Rs_id = rs_id;
    }

    public String getRoom_id() {
        return Room_id;
    }

    public void setRoom_id(String room_id) {
        Room_id = room_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "ReservationTM{" +
                "Rs_id='" + Rs_id + '\'' +
                ", Date='" + Date + '\'' +
                ", Status='" + Status + '\'' +
                ", Room_id='" + Room_id + '\'' +
                ", roomName='" + roomName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
