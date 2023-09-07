package lk.ijse.gdse.orm.hostel.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StudentAlert extends Alert {
    public StudentAlert(AlertType alertType, String title, String header, String message, ButtonType... buttonTypes) {
        super(alertType, message, buttonTypes);
        setTitle(title);
        setHeaderText(header);

        String image = null;
        switch (alertType) {
            case ERROR:
                image = "/lk/ijse/gdse/orm/hostel/img/6478111.png";
                break;
            case INFORMATION:
                image = "/lk/ijse/gdse/orm/hostel/img/Symbol_confirmed.svg.png";
                break;
            case WARNING:
                image = "/lk/ijse/gdse/orm/hostel/img/6897039.png";
                break;
        }
        if (image !=null){
            ImageView imgView = new ImageView(new Image(image));
            imgView.setFitWidth(40);
            imgView.setFitHeight(40);
            setGraphic(imgView);
        }
    }

}
