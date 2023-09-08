package lk.ijse.gdse.orm.hostel.dto;

public class StudentDTO {
    private String St_id;
    private String student_name;
    private String student_address;
    private String DOB;
    private String Gender;
    private String Contact;

    public StudentDTO() {
    }

    public StudentDTO(String st_id, String student_name, String student_address, String DOB, String gender, String contact) {
        St_id = st_id;
        this.student_name = student_name;
        this.student_address = student_address;
        this.DOB = DOB;
        Gender = gender;
        Contact = contact;
    }

    public String getSt_id() {
        return St_id;
    }

    public void setSt_id(String st_id) {
        St_id = st_id;
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

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "St_id='" + St_id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", student_address='" + student_address + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}
