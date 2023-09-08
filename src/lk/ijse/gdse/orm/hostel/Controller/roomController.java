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
import lk.ijse.gdse.orm.hostel.bo.custom.RoomBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dto.RoomDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class roomController implements Initializable {
    public Button btnClear;
    public Button btnDelete;
    public Button btnSave;
    public Button btnUpdate;
    public AnchorPane root;
    public TableColumn colmoney;
    public TableColumn  colqty;
    public TableColumn  colrooid;
    public TableColumn coltype;
    public TableView tblroom;
    public TextField txtId;
    public TextField txtmany;
    public TextField txtqty;
    public TextField txttype;

    private RoomBo roomBo = (RoomBo) BOFactory.getBO (BOFactory.BOTypes.ROOM_BO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableRoom ();
        loadAllRoom ();
        setRoomID ();
    }

    private void setRoomID() {
        String roomID=nextRoomId ();
        txtId.setText (roomID);
    }

    private void loadAllRoom() {
        try {
            List allRoom = roomBo.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allRoom);
            tblroom.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private void setTableRoom() {
        colrooid.setCellValueFactory (new PropertyValueFactory<>("roomID"));
        coltype.setCellValueFactory (new PropertyValueFactory<> ("type"));
        colmoney.setCellValueFactory (new PropertyValueFactory<> ("keyMoney"));
        colqty.setCellValueFactory (new PropertyValueFactory<> ("qty"));
    }

    public void OnActionMouseClicked(MouseEvent event) {
        int index = tblroom.getSelectionModel ().getSelectedIndex ();
        String roomId = colrooid.getCellData (index).toString ();//select Column value

        try {
            RoomDTO dto = roomBo.getRoom (roomId);
            txtId.setText (dto.getRoom_type ());
            txttype.setText (dto.getRoom_type ());
            txtmany.setText (dto.getMoney ());
            txtqty.setText (String.valueOf (dto.getQTY ()));
        } catch (Exception e) {
            System.out.println (e);
        }
    }

    public void btnBackOnAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/MainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Controller");
        stage.centerOnScreen();
    }


    public void btnClearOnAction(ActionEvent event) {

    }


    public void btnDeleteOnAction(ActionEvent event) {
        int qty = Integer.parseInt (txtqty.getText ());
        RoomDTO roomDTO = new RoomDTO (
                txtId.getText (),
                txttype.getText (),
                txtmany.getText (),
                qty
        );

        boolean isDeleted = roomBo.deleteRoom (roomDTO);

        if (isDeleted) {
            new Alert (Alert.AlertType.INFORMATION, "Room Delete Succuss").show ();
            tblroom.getItems ().clear ();
            clearData ();
            loadAllRoom ();
            setRoomID ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    public void btnSaveOnAction(ActionEvent event) {
        if(checkValidation ()){
            int qty = Integer.parseInt (txtqty.getText ());
            String roomId = txtId.getText ();
            String roomType = txttype.getText ();
            String keyMoney = txtmany.getText ();

            RoomDTO roomDTO = new RoomDTO (
                    roomId,
                    roomType,
                    keyMoney,
                    qty
            );

            try {

                List<RoomDTO> allRooms = roomBo.loadAll ();

                if (checkduplicate ()) {
                    if (checkValidation ()){
                        roomBo.saveRoom (roomDTO);
                        loadAllRoom ();
                        setRoomID ();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        int qty = Integer.parseInt (txtqty.getText ());
        RoomDTO roomDTO = new RoomDTO (
                txtId.getText (),
                txttype.getText (),
                txtmany.getText (),
                qty
        );

        boolean isUpdate = roomBo.updateRoom (roomDTO);

        if (isUpdate) {
            new Alert (Alert.AlertType.INFORMATION, "Student Update Succuss").show ();
            tblroom.getItems ().clear ();
            clearData ();
            loadAllRoom ();
            setRoomID ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    private boolean checkValidation() {
        String typeText = txttype.getText ();
        String moneyText = txtmany.getText ();
        String qtyText = txtqty.getText ();


        if (!moneyText.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "invalid key money").show ();
            txtmany.requestFocus ();
            return false;
        } else if (!qtyText.matches("^\\d+$")) {
            //} else if (!qtyText.matches("[0-9]")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtqty.requestFocus();
            return false;
        }else {
            return true;
        }
    }
    public boolean checkduplicate() {
        String roomId = txtId.getText ();
        List<RoomDTO> allRooms = roomBo.loadAll ();
        for (RoomDTO r : allRooms) {
            if (roomId.equals (r.getQTY ())) {
                new Alert (Alert.AlertType.ERROR, "This ID Already Have").show ();
                return false;
            }
        }
        return true;
    }
    public String nextRoomId() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select Room_id from Room order by Room_id desc");

        String nextId = "R001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("R00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "R00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }
    public void clearData() {
        txtId.clear ();
        txttype.clear ();
        txtmany.clear ();
        txtqty.clear ();
    }

}


