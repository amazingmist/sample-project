package poly.app.core.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

    public static boolean saveImage(File file) {
        File dir = new File("images");
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static ImageIcon readImage(String fileName) {
        File file = new File("images", fileName);
        return new ImageIcon(file.getAbsolutePath());
    }

    public static boolean deleteImage(String fileName) {
        try {
            File file = new File("images", fileName);
            return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
        return dest;
    }

    public static BufferedImage cropImageFromXY(BufferedImage src, int startX, int startY, int endX, int endY) {
        BufferedImage img = src.getSubimage(startX, startY, endX, endY); //fill in the corners of the desired crop location here
        BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        return copyOfImage;
    }

    public static ImageIcon resizeImage(File file, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        ImageIcon imageIcon = new ImageIcon(dimg);
//        imageIcon.getImage().flush();
//        return imageIcon;
dimg.flush();
return  new ImageIcon(dimg);
    }
}
