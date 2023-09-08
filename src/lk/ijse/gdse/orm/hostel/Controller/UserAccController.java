package lk.ijse.gdse.orm.hostel.Controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.gdse.orm.hostel.bo.BOFactory;
import lk.ijse.gdse.orm.hostel.bo.custom.UserBo;
import lk.ijse.gdse.orm.hostel.configaration.SessionFactoryConfig;
import lk.ijse.gdse.orm.hostel.dto.UserDTO;
import lk.ijse.gdse.orm.hostel.util.SendMail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserAccController implements Initializable {

    public TextField txtEmail;
    public PasswordField txtPass;
    public PasswordField txtRePass;
    public TextField txtUserId;
    public TextField txtUserName;
    private UserBo userBo = (UserBo) BOFactory.getBO (BOFactory.BOTypes.USER_BO);

    public void onActionCreateAccount(ActionEvent event) throws MessagingException, javax.mail.MessagingException {
        String pass=txtPass.getText ();
        String rePass=txtRePass.getText ();
        String userId = txtUserId.getText ();
        String userName = txtUserName.getText ();
        String email=txtEmail.getText ();


        if (checkDuplidate ()){
            if (checkValidation ()){
                if(pass.equals (rePass)){
                    userBo.saveUser (new UserDTO(
                            userId,
                            userName,
                            pass
                    ));
                    new Alert (Alert.AlertType.CONFIRMATION, "USER ACCOUNT CREATED SUCCUSS").show ();
                    SendMail.outMail ("YOU ARE USER IN D24HOSTEL SYSTEM",email,"D24HOSTEL");
                    clearFeilds ();
                    setUserId ();
                }else {
                    new Alert (Alert.AlertType.ERROR, "Check your Password and Try Again").show ();
                }
            }
        }else{
            new Alert (Alert.AlertType.ERROR, "THIS USER ID ALREADY GET").show ();
            clearFeilds ();
        }

    }
    public boolean checkDuplidate(){
        String userId=txtUserId.getText ();
        List<UserDTO> allRoom = userBo.loadAll ();
        for (UserDTO u : allRoom) {
            if (userId.equals (u.getUserId ())){
                return false;
            }
        }
        return  true;
    }

    public void clearFeilds(){
        txtRePass.clear ();
        txtEmail.clear ();
        txtPass.clear ();
        txtUserId.clear ();
        txtUserName.clear ();
    }

    public String nextUserID() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select userId from User order by userId desc");

        String nextId = "U001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("U00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "U00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }

    public void setUserId(){
        String userID=nextUserID ();
        txtUserId.setText (userID);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserId ();
    }
    private boolean checkValidation() {
        String email=txtEmail.getText ();

        if (!email.matches("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$")) {
            new Alert(Alert.AlertType.ERROR, "INVALID EMAIL ADDRESS").show ();
            txtEmail.requestFocus ();
            return false;
        } else {
            return true;
        }



    }
}

