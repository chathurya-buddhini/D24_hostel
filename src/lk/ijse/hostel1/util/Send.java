package lk.ijse.hostel1.util;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Send {
    public static final String ACCOUNT_SID = System.getenv("AC8850f892acb3902d9e931c05f308453f");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String TWILIO_PHONE_NUMBER = "+94775642853";
    public static void main(String[] args) {




                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                Message message = Message.creator(
                                new PhoneNumber("+94788249177"), // To number
                                new PhoneNumber(TWILIO_PHONE_NUMBER), // From number
                                "Hello from Twilio!")
                        .create();

                System.out.println(message.getSid());
    }
}
