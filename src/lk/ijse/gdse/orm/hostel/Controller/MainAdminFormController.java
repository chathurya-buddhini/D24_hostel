package lk.ijse.gdse.orm.hostel.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAdminFormController {

   public AnchorPane contextMainAdmin;
   public PasswordField txtPassWord;
   public TextField txtUserName;

    private String name="chathu";
    private String pass="1234";

   public void onActionSubmit(ActionEvent event) throws IOException {
       String userPass=txtPassWord.getText ();
       String userName=txtUserName.getText ();
       if (userName.equals (name) && userPass.equals (pass)){
           Stage stage3 = new Stage();
           stage3.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/UserAcc.fxml"))));
           stage3.show();

           Stage stage4 = (Stage) contextMainAdmin.getScene().getWindow();
           stage4.close();
       }else{
           new Alert(Alert.AlertType.ERROR, "INVALID ADMIN ATHUENTICATION").show ();
           txtPassWord.clear ();
           txtUserName.clear ();
       }
    }

}