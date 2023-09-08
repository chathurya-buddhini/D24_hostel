package lk.ijse.gdse.orm.hostel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    String id;
    @Column(name = "name")
    String student_name;
    @Column(name = "address")
    String student_address;
    @Column(name = "dob")
    String DOB;
    @Column(name = "gender")
    String Gender;
    @Column(name = "contact_no")
    String Contact;

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "student")
//    private List<Reservation> reservationList;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> resList = new ArrayList<>();

    public Student() { }
    public Student(String id, String student_name, String student_address, String DOB, String gender, String contact) {
        this.id = id;
        this.student_name = student_name;
        this.student_address = student_address;
        this.DOB = DOB;
        Gender = gender;
        Contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_address() {
        return student_address;
    }

    public void setStudent_address(String student_address) {
        this.student_address = student_address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", student_address='" + student_address + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
