package lk.ijse.gdse.orm.hostel.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    String Rs_id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_type_id",
            referencedColumnName = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "room_id",
            referencedColumnName = "room_id")
    private Room room;

    @Column(name = "status")
    String status;


    public Reservation() { }


    public Reservation(String rs_id, Date date, Student student, Room room, String status) {
        Rs_id = rs_id;
        this.date = date;
        this.student = student;
        this.room = room;
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
                "Rs_id='" + Rs_id + '\'' +
                ", date=" + date +
                ", student=" + student +
                ", room=" + room +
                ", status='" + status + '\'' +
                '}';
    }
}
