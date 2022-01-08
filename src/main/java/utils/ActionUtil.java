package utils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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

    /**
     * Getting the presence status of an element that contains a certain text.
     * @param targetElement Element that wanted to be asserted
     * @param text Text that wanted to be asserted
     * @param timeOutInSecond Explicit timeout
     * @return <b>boolean</b>
     */
    public static boolean waitElementWithText(By targetElement, @NotNull String text, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(androidDriver, timeOutInSecond);
        AndroidElement element = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(targetElement));
        return element.getText().toLowerCase().contains(text.toLowerCase());
    }

    /**
     * Getting the presence status of an element that based on image.
     * @param refElement Element that has to be presented first before try to find the imageElement
     * @param imageElement Path of the image file that is used as reference
     * @param timeOutInSecond Explicit timeout
     * @return <b>boolean</b>
     */
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

    /**
     * Tapping an element.
     * @param targetElement Element that wants to be tapped
     */
    public static void tapElement(By targetElement) {
        AndroidElement element = androidDriver.findElement(targetElement);
        element.click();
    }

    /**
     * Inputting an element via on-screen keyboard.
     * @param targetElement Element that triggers the on-screen keyboard
     * @param input Text that wants to be inputted
     */
    public static void inputElementByKeyboard(By targetElement, String input) {
        // Null changer
        input = strNullHandler(input);

        tapElement(targetElement);
        Actions action = new Actions(androidDriver);
        action.sendKeys(input).perform();
    }

    /**
     * Capturing the position of the element on the screen by marking the element. If the element is image based element,
     * the score is shown above the element itself.
     * @param element Element that wanted to be marked
     * @param usingIM Whether the element is based on image or just a regular element
     * @throws IOException Throws I/O exception
     */
    public static void doElementCapture(@NotNull AndroidElement element, boolean usingIM) throws IOException {
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

    /**
     * Converting <i>Image</i> to <i>By</i> type that can be used universally.
     * @param targetImage ath of the image file that is used as reference
     * @return <b>By</b>
     * @throws IOException Throws I/O exception
     */
    public static @NotNull By getElementByImage(String targetImage) throws IOException {
        File refImgFile = new File(System.getProperty("user.dir") + "/src/main/resources/imageElements/" + targetImage);
        String imageB64 = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
        return MobileBy.image(imageB64);
    }

    /**
     * Converting String variable's value that has null to empty
     * @param input Target variable
     * @return <b>String</b>
     */
    @Contract(value = "!null -> param1", pure = true)
    public static @NotNull String strNullHandler(String input) {
        if (input == null) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Scrolling down the screen by touch action
     */
    public static void scrollDown() {
        AndroidElement screen = androidDriver
                .findElement(By.id("action_bar_root"));
        Point center = screen.getCenter();
        int width = screen.getSize().width;
        int height = screen.getSize().height;
        int xc = center.getX() - (width / 2) + 20;
        int yi = center.getY() + (height / 3);
        int yf = center.getY() - (height / 2);
        @SuppressWarnings("rawtypes")
        TouchAction scroll = new TouchAction(androidDriver);
        scroll.press(PointOption.point(xc, yi))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(xc, yf)).release().perform();
    }

    /**
     * Scrolling down the screen until a certain element is presence, then followed by tapping the element.
     * @param targetElement Element that wanted to be found and tapped
     */
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
