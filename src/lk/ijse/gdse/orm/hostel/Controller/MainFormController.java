package lk.ijse.gdse.orm.hostel.Controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.orm.hostel.util.Navigation;
import lk.ijse.gdse.orm.hostel.util.Routes;

import java.io.IOException;

public class MainFormController {

   public Button btndash;
    public Button btnlog;
    public Button btnres;
    public Button btnroom;
    public Button btnsettings;
    public Button btnstudent;
    public AnchorPane contecxtPane;


    @FXML
    void btndashOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,contecxtPane);
        slideButton (btndash);
    }

    @FXML
    void btnlogOnAction(ActionEvent event) {

    }

    @FXML
    void btnresOnAction(ActionEvent event) throws IOException {
     Navigation.navigate(Routes.RESERVATION,contecxtPane);
     slideButton (btnres);
    }

    @FXML
    void btnroomOnAction(ActionEvent event) throws IOException {
     Navigation.navigate(Routes.ROOM,contecxtPane);
     slideButton (btnroom);
    }

    @FXML
    void btnsettingsOnAction(ActionEvent event) throws IOException {
     Navigation.navigate(Routes.SETTING,contecxtPane);
     slideButton (btnsettings);
    }

    @FXML
    void btnstudentOnAction(ActionEvent event) throws IOException {
     Navigation.navigate(Routes.STUDENT,contecxtPane);
     slideButton (btnstudent);
    }
 public void slideButton(Node node){
  TranslateTransition slider = new TranslateTransition();
  slider.setDuration(Duration.seconds(0.4));
  slider.setNode(node);

  slider.setToX(28);
  slider.play();
  slider.setToX (0);
  slider.play();
 }

}

