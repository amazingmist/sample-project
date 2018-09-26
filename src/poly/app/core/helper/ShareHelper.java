package poly.app.core.helper;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import poly.app.core.entities.NhanVien;

public class ShareHelper {
    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     */
    public static final Image APP_ICON;
    static{
        // Tải biểu tượng ứng dụng
        URL urlIconApp = ShareHelper.class.getResource("../../view/icon/icon-app.png");
        APP_ICON = new ImageIcon(urlIconApp).getImage();
    }
 
    /**
     * Sao chép file logo chuyên đề vào thư mục logo
     * @param file là đối tượng file ảnh
     * @return chép được hay không
     */   
    public static boolean saveFile(File file){
        File dir = new File("logos");
        // Tạo thư mục nếu chưa tồn tại
        if(!dir.exists()){
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } 
        catch (Exception ex) {
            return false;
        }
    }
    /**
     * Đọc hình ảnh logo chuyên đề
     * @param fileName  là tên file logo
     * @return ảnh đọc được
     */   
    public static ImageIcon readFile(String fileName){
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static NhanVien USER = null;
    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void logOut() {
        ShareHelper.USER = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     * @return đăng nhập hay chưa
     */
    public static boolean authenticated() {
        return ShareHelper.USER != null;
    }
}