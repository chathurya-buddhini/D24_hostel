package lk.ijse.gdse.orm.hostel.dto.tm;

public class StudentTM {
    private String id;
    private String student_name;
    private String student_address;
    private String DOB;
    private String Gender;
    private String Contact;

    public StudentTM() {
    }

    public StudentTM(String student_name, String student_address, String dob, String gender, String contact, String id) {
        this.student_name = student_name;
        this.student_address = student_address;
        this.DOB = DOB;
        Gender = gender;
        Contact = contact;
        this.id = id;
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
        return "StudentTM{" +
                "id='" + id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", student_address='" + student_address + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
