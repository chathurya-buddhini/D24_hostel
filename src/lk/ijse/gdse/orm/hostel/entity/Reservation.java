package lk.ijse.gdse.orm.hostel.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    String id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private Room room;
    @Column(name = "status")
    String status;



    public Reservation() { }

    public Reservation(String id, Date date, Student student, Room room, String status) {
        this.id = id;
        this.date = date;
        this.student = student;
        this.room = room;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", student=" + student +
                ", room=" + room +
                ", status='" + status + '\'' +
                '}';
    }
}
