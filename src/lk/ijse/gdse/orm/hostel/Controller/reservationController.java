package lk.ijse.gdse.orm.hostel.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import lk.ijse.gdse.orm.hostel.bo.custom.ReservationBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dto.ReservationDTO;
import lk.ijse.gdse.orm.hostel.dto.RoomDTO;
import lk.ijse.gdse.orm.hostel.dto.StudentDTO;
import lk.ijse.gdse.orm.hostel.dto.tm.ReservationTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;


public class reservationController implements Initializable {
    public TextField txtid;
    public TextField txtname;
    public TextField txtqty;
    public TextField txtroomtype;
    public ComboBox cmbroom;
    public ComboBox cmbstatus;
    public ComboBox cmbstuId;

    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ReservationTM> tblresertion;
    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colroomid;

    @FXML
    private TableColumn<?, ?> cols_id;

    @FXML
    private TableColumn<?, ?> colstatus;

    @FXML
    private TableColumn<?, ?> coltype;

    private ReservationBo reservationBo = (ReservationBo) BOFactory.getBO (BOFactory.BOTypes.RESERVATION_BO);
    // public static  ObservableList<CourseDTO> CourseIds=FXCollections.observableArrayList();
    public static ObservableList<StudentDTO> stIds = FXCollections.observableArrayList ();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setIds ();
        setData ();
        setStatus ();
        setCellValueFactory ();
        loadAllRes ();
        setResID ();


    }

    private void loadAllRes() {
        try {
            List<ReservationDTO> allRoom = reservationBo.loadAll ();

            ObservableList<ReservationTM> resList = FXCollections.observableArrayList ();

            for (ReservationDTO dto : allRoom) {
                resList.add (new ReservationTM (
                        dto.getRs_id (),
                        dto.getStudentDTO ().getStudent_name (),
                        dto.getStudentDTO ().getStudent_name (),
                        dto.getRoomDTO ().getRoom_id (),
                        dto.getRoomDTO ().getRoom_type (),
                        dto.getStatus ()
                ));
            }

            tblresertion.setItems (resList);

            System.out.println (resList);


        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private void setResID() {
        String resID=nextResId ();
        txtid.setText (resID);
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("resId"));
        cols_id.setCellValueFactory(new PropertyValueFactory<>("stId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("stName"));
        colroomid.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void setStatus() {
        ObservableList<String> data = FXCollections.observableArrayList ("PAID", "UNPAID");
        cmbstatus.setItems (data);
    }

    private void setData() {
        cmbstuId.setOnAction (event -> {
            String stId = cmbstuId.getValue ().toString ();
            StudentDTO dto = reservationBo.getStudent (stId);
            txtname.setText (dto.getStudent_name ());
        });

        cmbroom.setOnAction (event -> {
            String roomId = cmbroom.getValue ().toString ();
            RoomDTO dto = reservationBo.getRoom (roomId);
            txtroomtype.setText (dto.getRoom_type ());
            txtqty.setText (String.valueOf (dto.getQTY ()));
        });
    }

    private void setIds() {
        List<String> stIds = reservationBo.getStudentIds ();
        ObservableList student = FXCollections.observableArrayList (stIds);
        cmbstuId.setItems (student);

        List<String> roomIds = reservationBo.getRoomIds ();
        ObservableList room = FXCollections.observableArrayList (roomIds);
        cmbroom.setItems (room);
    }

    @FXML
     void OnActionMouseClicked(MouseEvent event) {
        int index = tblresertion.getSelectionModel ().getSelectedIndex ();
        String resID = colid.getCellData (index).toString ();//select Column value

        try {
            ReservationDTO dto = reservationBo.getRes (resID);
            System.out.println (dto.getRs_id ());
            txtid.setText (dto.getRs_id ());
            txtroomtype.setText (dto.getRoomDTO ().getRoom_type ());
            txtname.setText (dto.getStudentDTO ().getStudent_name ());
            String stID = dto.getStudentDTO ().getSt_id ();
            cmbstuId.setValue (stID);
            cmbroom.setValue (dto.getRoomDTO ().getRoom_id ());
            cmbstatus.setValue (dto.getStatus ());
            txtqty.setText (String.valueOf (dto.getRoomDTO ().getQTY ()));
        } catch (Exception e) {
            System.out.println (e);
        }
    }

    @FXML
    void btnBackOnAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/MainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Controller");
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String stId = cmbstuId.getValue ().toString ();
        String roomID = cmbroom.getValue ().toString ();
        String status = cmbstatus.getValue ().toString ();
        String id = txtid.getText ();
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        try {
            boolean isDelete = reservationBo.deleteReservation (
                    new ReservationDTO (
                            id,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            if (isDelete) {
                RoomDTO room = getRoomDetail ();
                room.setQTY (room.getQTY () + 1);
                reservationBo.updateRoom (room);
                loadAllRes ();

            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String stId = cmbstuId.getValue ().toString ();
        String roomID = cmbroom.getValue ().toString ();
        String status = cmbstatus.getValue ().toString ();
        String id = txtid.getText ();
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        if (checkDuplicate ()) {
            boolean isSaveReservation = reservationBo.saveReservation (
                    new ReservationDTO (
                            id,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            if (isSaveReservation) {
                RoomDTO room = getRoomDetail ();

                System.out.println (room.getQTY () - 1);
                room.setQTY (room.getQTY () - 1);
                reservationBo.updateRoom (room);
                // tblReservation.getItems ().clear ();
                loadAllRes ();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String stId = cmbstuId.getValue ().toString ();
        String roomID = cmbroom.getValue ().toString ();
        String status = cmbstatus.getValue ().toString ();
        String id = txtid.getText ();
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        try {
            boolean isUpdate = reservationBo.updateReservation (
                    new ReservationDTO (
                            id,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            loadAllRes ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public String nextResId() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select Rs_id from Reservation order by Rs_id desc");

        String nextId = "T001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("T00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.parseInt (id);

            id = "T00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }

    }
    public StudentDTO getStudnetDetail() {
        String stId = cmbstuId.getValue ().toString ();
        return reservationBo.getStudent (stId);
    }

    public RoomDTO getRoomDetail() {
        try {
            String roomId = cmbroom.getValue ().toString ();
            System.out.println ("This IS ROOM ID"+roomId);
            return reservationBo.getRoom (roomId);
        } catch (Exception e) {
            System.out.println (e);
        }
        return null;
    }

    public boolean checkDuplicate() {
        String resId = txtid.getText ();
        List<ReservationDTO> list = reservationBo.loadAll ();

        for (ReservationDTO dto : list) {
            if (resId.equals (dto.getRs_id ())) {
                new Alert (Alert.AlertType.CONFIRMATION, "RESERVATION ADDED SUCCUSS").show ();
                return false;
            }
        }
        return true;
    }
}