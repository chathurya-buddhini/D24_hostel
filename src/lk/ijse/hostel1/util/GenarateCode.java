package lk.ijse.hostel1.util;

import java.util.Random;

public class GenarateCode {
    public static int verifyCode(){
        Random rand = new Random();
        int verify = rand.nextInt(10000);
        return verify;
    }
}
