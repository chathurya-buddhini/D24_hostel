package lk.ijse.gdse.orm.hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    private static AnchorPane anchorPane;

    public static void navigate(Routes routes, AnchorPane anchorPane) throws IOException {
        Navigation.anchorPane=anchorPane;
        Navigation.anchorPane.getChildren().clear();
        Stage window=(Stage)Navigation.anchorPane.getScene().getWindow();

        switch (routes) {
            case STUDENT:
                window.setTitle ("STUDENT");
                iniUi("StudentForm.fxml");
                break;
            case ROOM:
                window.setTitle ("ROOM");
                iniUi("RoomForm.fxml");
                break;
            case RESERVATION:
                window.setTitle ("RESERVATION");
                iniUi("ReservationForm.fxml");
                break;
            case SETTING:
                window.setTitle ("SETTING");
                iniUi("Setting.fxml");
                break;
            case DASHBOARD:
                window.setTitle ("DASBOARD");
                iniUi("DashboardForm.fxml");
                break;
            default:
                System.out.println ("Something Wrong");
        }

    }
    private static void iniUi(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/gdse/orm/hostel/View/"+location)));
    }
}
