package lk.ijse.gdse.orm.hostel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id",length = 25)
    String Room_id;
    @Column(name = "type")
    String room_type;
    @Column(name = "key_money")
    String money;
    @Column(name = "qty")
    int QTY;

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "room")
//    List<Reservation> reservationList;
@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
private List<Reservation> resList = new ArrayList<>();


    public Room(String room_id, String room_type, String money, Integer qty) { }

    public Room(String room_id, String room_type, String money, int QTY, List<Reservation> resList) {
        Room_id = room_id;
        this.room_type = room_type;
        this.money = money;
        this.QTY = QTY;
        this.resList = resList;
    }

    public String getRoom_id() {
        return Room_id;
    }

    public void setRoom_id(String room_id) {
        Room_id = room_id;
    }

    public String getRoom_type() {
        return room_type;
    }


    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    @Override
    public String toString() {
        return "Room{" +
                "Room_id='" + Room_id + '\'' +
                ", room_type='" + room_type + '\'' +
                ", money='" + money + '\'' +
                ", QTY=" + QTY +
                ", resList=" + resList +
                '}';
    }
}
