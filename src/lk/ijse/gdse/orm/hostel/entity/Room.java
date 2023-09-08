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
    String id;
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


    public Room() { }
    public Room(String id, String room_type, String money, int QTY) {
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
                "id='" + id + '\'' +
                ", room_type='" + room_type + '\'' +
                ", money='" + money + '\'' +
                ", QTY=" + QTY +
                ", resList=" + resList +
                '}';
    }
}
