package lk.ijse.gdse.orm.hostel.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.orm.hostel.bo.BOFactory;
import lk.ijse.gdse.orm.hostel.bo.custom.StudentBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dto.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class studentController implements Initializable {

    public Button btnClear;
    public Button btnDelete;
    public Button btnSave;
    public Button btnUpdate;
    public ComboBox cmbgender;
    public DatePicker textdob;
    public TextField txtaddress;
    public TextField txtid;
    public TextField txtname;
    public TextField txtnumber;
    public TableColumn coladdress;
    public TableColumn colcontact;
    public TableColumn coldob;
    public TableColumn colgender;
    public TableColumn colid;
    public TableColumn colname;
    public AnchorPane root;
    public TableView tblStudent;

    private Session session;
    private StudentBo studentBO = (StudentBo) BOFactory.getBO (BOFactory.BOTypes.STUDENT_BO);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGender ();
        setTableStudent ();
        loadAllStudent ();
        setStID ();
    }
    public void setStID(){
        String stID=nextStID ();
        txtid.setText (stID);
    }

    public String nextStID() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select id from Student order by id desc");

        String nextId = "S001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "S00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }


    private void loadAllStudent() {
        try {
            List allStudents = studentBO.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allStudents);
            tblStudent.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private void setTableStudent() {
        colid.setCellValueFactory (new PropertyValueFactory<>("stId"));
        colname.setCellValueFactory (new PropertyValueFactory<> ("stName"));
        coladdress.setCellValueFactory (new PropertyValueFactory<> ("address"));
        coldob.setCellValueFactory (new PropertyValueFactory<> ("dob"));
        colcontact.setCellValueFactory (new PropertyValueFactory<> ("contact"));
        colgender.setCellValueFactory (new PropertyValueFactory<> ("gender"));
    }

    private void setGender() {
        ObservableList<String> data = FXCollections.observableArrayList ("Male", "Female", "Other");
        cmbgender.setItems (data);
    }

    public void btnBackOnAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/MainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Controller");
        stage.centerOnScreen();
    }
    public  void btnClearOnAction(ActionEvent event) {

    }
    public void btnDeleteOnAction(ActionEvent event) {
        String dob = String.valueOf (textdob.getValue ());
        String gender = cmbgender.getValue ().toString ();
        StudentDTO studentDTO = new StudentDTO (txtid.getText (), txtname.getText (), txtaddress.getText (), txtnumber.getText (), dob, gender);

        boolean isDeleted=studentBO.deleteStudent (studentDTO);

        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION, "Student Delete Succuss").show ();
            tblStudent.getItems ().clear ();
            clearData ();
            loadAllStudent ();
            setStID ();
        }else{
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }

    }

    public void btnSaveOnAction(ActionEvent event) {
        String dob = String.valueOf (textdob.getValue ());
        String gender = cmbgender.getValue ().toString ();
        StudentDTO studentDTO = new StudentDTO (txtid.getText (), txtname.getText (), txtaddress.getText (), txtnumber.getText (), dob, gender);

        if(checkDuplicate ()){
            boolean isCheckValidate=checkValidation ();
            if(isCheckValidate){
                studentBO.saveStudent (studentDTO);
                new Alert (Alert.AlertType.CONFIRMATION, "Student saved").show ();
                tblStudent.getItems ().clear ();
                clearData ();
                loadAllStudent ();
                setStID ();
            }

        }
    }
    public void btnUpdateOnAction(ActionEvent event) {
        String dob = String.valueOf (textdob.getValue ());
        String gender = cmbgender.getValue ().toString ();
        StudentDTO studentDTO = new StudentDTO (txtid.getText (), txtname.getText (), txtaddress.getText (), txtnumber.getText (), dob, gender);

        boolean isUpdate=studentBO.updateStudent (studentDTO);

        if (isUpdate){
            new Alert (Alert.AlertType.INFORMATION, "Student Update Succuss").show ();
            tblStudent.getItems ().clear ();
            clearData ();
            loadAllStudent ();
            setStID ();
        }else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }
    public void cmbgenderOnAction(ActionEvent event) {
    }

    public void clearData() {
        txtid.clear ();
        txtnumber.clear ();
        txtaddress.clear ();
        txtnumber.clear ();
        textdob.setValue (null);
        cmbgender.setValue (null);
    }
    public boolean checkDuplicate() {
        List<StudentDTO> allStudents = studentBO.loadAll ();
        for (StudentDTO s : allStudents) {
            if (s.getId ().equals (txtid.getText ())) {
                new Alert (Alert.AlertType.ERROR, "This ID Already Have").show ();
                return false;
            }
        }
        return true;
    }
    private boolean checkValidation(){
        String nameText = txtname.getText();
        String addressText = txtaddress.getText();
        String contactText = txtnumber.getText();

        if (!addressText.matches(".{2,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtaddress.requestFocus();
            return false;
            //} else if (!contactText.matches(".*(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}")) {
        } else if (!contactText.matches("\\d{10}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtnumber.requestFocus();
            return false;
        }else {
            return true;
        }

    }

    public void OnActionMouseClicked(MouseEvent mouseEvent) {
        int index = tblStudent.getSelectionModel ().getSelectedIndex ();
        String stId = colid.getCellData (index).toString ();//select Column value

        try {
            StudentDTO dto = studentBO.getStudent (stId);
            txtid.setText (dto.getId ());
            txtname.setText (dto.getStudent_name ());
            txtaddress.setText (dto.getStudent_address ());
            txtnumber.setText (dto.getContact ());
            textdob.setValue (LocalDate.parse (dto.getDOB ()));
            cmbgender.setValue (dto.getGender ());
        } catch (Exception e) {
            System.out.println (e);
        }
    }

}