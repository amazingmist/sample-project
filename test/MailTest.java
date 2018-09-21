
import poly.app.core.utils.EMailUtil;
import poly.app.core.utils.StringUtil;

public class MailTest {

    public static void main(String[] args) {
        String msgSubject = "Khôi phục mật khẩu";
        String msgBody = "<h2>Xin chào bạn! Bạn hãy dùng mã xác nhận dưới đây để khôi phục lại mất khẩu của mình nhé</h2>"
                + "<br>Tài khoản: " + "thanhtai17071997@gmail.com" + "<br>Mã xác nhận: " + StringUtil.randomMaXacNhan()
                + "<br>Hãy sử dụng mã xác nhận trên để tiến hình khôi phục mật khẩu.";
        new EMailUtil("thanhtai17071997@gmail.com", msgBody, msgSubject).sendMail();
    }
}
