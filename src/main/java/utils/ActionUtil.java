package utils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Base64;

import static drivers.AndroidDriverInstance.*;

public class ActionUtil {

    public static boolean waitElementWithText(By targetElement, String text, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(androidDriver, timeOutInSecond);
        AndroidElement element = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(targetElement));
        return element.getText().toLowerCase().contains(text.toLowerCase());
    }

    public static boolean waitImageElement(By refElement, String imageElement, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(androidDriver, timeOutInSecond);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(refElement)).isDisplayed();
        } catch (Exception e) {
            System.out.print("Cannot find targetElementOne!");
            return false;
        }

        try {
            AndroidElement element = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(getElementByImage(imageElement)));
            if (IMECaptureStatus) {
                doElementCapture(element, true);
            }
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.print("Cannot find targetElementTwo!");
            return false;
        }
    }

    public static void tapElement(By targetElement) {
        AndroidElement element = androidDriver.findElement(targetElement);
        element.click();
    }

    public static void inputElementByKeyboard(By targetElement, String input) {
        // Null changer
        input = strNullHandler(input);

        tapElement(targetElement);
        Actions action = new Actions(androidDriver);
        action.sendKeys(input).perform();
    }

    public static void doElementCapture(AndroidElement element, boolean usingIM) throws IOException {
        System.out.println("Capturing the snapshot of the element on the page");
        System.out.printf("Element position = [%s, %s] %n", element.getRect().x, element.getRect().y);

        File srcFile = androidDriver.getScreenshotAs(OutputType.FILE);
        String filePath = IMECaptureDir + File.separator;

        int imgNumber = 0;
        File file = new File(filePath + "Element-0.jpg");
        while (file.exists()) {
            imgNumber++;
            file = new File(filePath + String.format("Element-%s.jpg", imgNumber));
        }

        FileUtils.copyFile(srcFile, file);

        int elH = element.getRect().height;
        int elW = element.getRect().width;
        int elX = element.getRect().x;
        int elY = element.getRect().y;

        BufferedImage img = ImageIO.read(file);
        BufferedImage rgbImage = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        ColorConvertOp op = new ColorConvertOp(null);
        Graphics2D g2d = rgbImage.createGraphics();
        op.filter(img, rgbImage);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(10));
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g2d.drawRect(elX, elY, elW, elH);

        if (usingIM) {
            String imgScore = element.getAttribute("score");
            System.out.printf("Image Match Score = %s %n", element.getAttribute("score"));
            g2d.drawString(imgScore.substring(0, 5), elX, elY - 10);
        }
        g2d.dispose();

        try {
            ImageIO.write(rgbImage, "jpg", file);

        } catch (Exception e) {
            System.out.println("[ERROR] Could not save image.");
        }
    }

    public static By getElementByImage(String targetImage) throws IOException {
        File refImgFile = new File(System.getProperty("user.dir") + "/src/main/resources/imageElements/" + targetImage);
        String imageB64 = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
        return MobileBy.image(imageB64);
    }

    public static String strNullHandler(String input) {
        if (input == null) {
            return "";
        } else {
            return input;
        }
    }

    public static void scrollDown() {
        AndroidElement screen = androidDriver
                .findElement(By.id("action_bar_root"));
        Point center = screen.getCenter();
        int width = screen.getSize().width;
        int height = screen.getSize().height;
        int xc = center.getX() - (width / 2) + 20;
        int yi = center.getY() + (height / 3);
        int yf = center.getY() - (height / 2);
//        System.out.println("xi, yi = " + xc + " " + yi);
//        System.out.println("xf, yf = " + xc + " " + yf);
        @SuppressWarnings("rawtypes")
        TouchAction scroll = new TouchAction(androidDriver);
        scroll.press(PointOption.point(xc, yi))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(xc, yf)).release().perform();
    }

    public static void scrollThenTap(By targetElement) {
        boolean isFound = false;
        int counter = 0;

        do {
            try {
                tapElement(targetElement);
                isFound = true;
            } catch (Exception e) {
                scrollDown();
                counter++;
            }

        } while (!isFound && counter < 5);
    }

}
