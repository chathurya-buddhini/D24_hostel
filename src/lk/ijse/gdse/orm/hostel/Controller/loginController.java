package lk.ijse.gdse.orm.hostel.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.orm.hostel.bo.BOFactory;
import lk.ijse.gdse.orm.hostel.bo.custom.UserBo;
import lk.ijse.gdse.orm.hostel.dto.UserDTO;
import lk.ijse.gdse.orm.hostel.util.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class loginController  implements Initializable {

public AnchorPane LoginPane;
    public Button btnloging;
    public ImageView imgClosed;
    public ImageView imgOpen;
    public PasswordField txtPass;
    public TextField txtPassShow;
    public TextField txtUName;

    private UserBo userBo = (UserBo) BOFactory.getBO (BOFactory.BOTypes.USER_BO);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgClosed.setVisible (false);
        imgOpen.setVisible (true);
        txtPass.setVisible (true);
        txtPassShow.setVisible (false);
    }

    public void CreatteACCMouseClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/MainAdminForm.fxml"));
        Parent parent=fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }


    public void OnClickEyeOpen(MouseEvent event) {
        imgClosed.setVisible (true);
        txtPassShow.setVisible (true);
        txtPassShow.setText (txtPass.getText ());
    }


    public void btnlogingOnAction(ActionEvent event) throws IOException {
        if (checkUserDetail ()){

            Stage stage= (Stage) LoginPane.getScene ().getWindow ();
            stage.setScene(new Scene (FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/MainForm.fxml"))));

            Notification.notification ("Login Succussfully");
        }
    }


    public void eyeClosedOnAction(MouseEvent event) {
        imgClosed.setVisible (false);
        imgOpen.setVisible (true);
        txtPassShow.setVisible (false);
        txtPass.setVisible (true);
    }
    public boolean checkUserDetail(){
        String userName = txtUName.getText ();
        String pass=txtPass.getText ();

        List<UserDTO> userList = userBo.loadAll ();

        for (UserDTO dto : userList) {
            if(dto.getUserName ().equals (userName) && dto.getPassword ().equals (pass)){
                return true;
            }
        }
        return false;
    }

}
