package lk.ijse.gdse.orm.hostel.dto.tm;

public class ReservationTM {
    private String id;
    private String Date;
    private String Status;
    private String roomId;
    private String roomName;
    private String status;

    public ReservationTM() {
    }

    public ReservationTM(String id, String date, String status, String roomId, String roomName, String status1) {
        this.id = id;
        Date = date;
        Status = status;
        this.roomId = roomId;
        this.roomName = roomName;
        this.status = status1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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
                "id='" + id + '\'' +
                ", Date='" + Date + '\'' +
                ", Status='" + Status + '\'' +
                ", roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
