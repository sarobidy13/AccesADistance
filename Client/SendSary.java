package sendsary;

import java.awt.Robot;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.AWTException;

public class SendSary extends Thread {
    DataOutputStream outputStream;
    Robot robot;
    Rectangle r = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

    public SendSary(DataOutputStream dos) {
        outputStream = dos;
        start();
    }

    @Override
    public void run() {
        Image image = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                image = robot.createScreenCapture(r);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage) image, "png", byteArrayOutputStream);

                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                outputStream.write(size);
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.flush();
            } catch (IOException e) {

            }
        }

    }

}
