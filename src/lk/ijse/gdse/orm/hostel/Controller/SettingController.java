package lk.ijse.gdse.orm.hostel.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingController {
    public void onActionManageUser(ActionEvent actionEvent) throws IOException {
        Stage stage1=new Stage();
        stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/ChangePassForm.fxml"))));
        stage1.show();
    }

    public void onActionAddUser(ActionEvent actionEvent) throws IOException {
        Stage stage1=new Stage();
        stage1.setScene(new Scene (FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/orm/hostel/View/UserAcc.fxml"))));
        stage1.show();
    }
}
