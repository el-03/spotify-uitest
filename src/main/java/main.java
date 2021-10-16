import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

public class main {

    public static void main(String args[]) throws IOException {

        int elH = 139;
        int elW = 139;
        int elX = 0;
        int elY = 0;

        File file = new File(System.getProperty("user.dir") + File.separator + "IMECaptureDir" + File.separator + "test.jpg");

        BufferedImage img = ImageIO.read(file);
        BufferedImage rgbImage = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        ColorConvertOp op = new ColorConvertOp(null);
        Graphics2D g2d = rgbImage.createGraphics();
        op.filter(img, rgbImage);
        g2d.setColor(Color.RED);
        g2d.drawRect(elX, elY, elW, elH);
        g2d.dispose();

        try {
            ImageIO.write(rgbImage, "jpg", file);

        } catch (Exception e) {
            System.out.println("[ERROR] Could not save image.");
        }
    }

}
