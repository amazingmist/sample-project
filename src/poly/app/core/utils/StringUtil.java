package poly.app.core.utils;

import java.util.Random;

public class StringUtil {
    public static String randomMaXacNhan(){
        String textSample = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        String maxacnhan = "";
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(textSample.length());
            maxacnhan += textSample.charAt(index);   
        }
        return maxacnhan;
    }
}