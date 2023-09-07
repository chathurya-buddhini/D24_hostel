package lk.ijse.gdse.orm.hostel.dto.tm;

import javax.persistence.Column;

public class RoomTM {
    private String id;
    private String room_type;
    private Double money;
    private Integer QTY;

    public RoomTM() {
    }

    public RoomTM(String id, String room_type, Double money, Integer QTY) {
        this.id = id;
        this.room_type = room_type;
        this.money = money;
        this.QTY = QTY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getQTY() {
        return QTY;
    }

    public void setQTY(Integer QTY) {
        this.QTY = QTY;
    }

    @Override
    public String toString() {
        return "RoomTM{" +
                "id='" + id + '\'' +
                ", room_type='" + room_type + '\'' +
                ", money=" + money +
                ", QTY=" + QTY +
                '}';
    }
}
