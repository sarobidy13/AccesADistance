package execution;

import java.awt.AWTException;
import java.io.DataInputStream;
import java.io.IOException;
import java.awt.Robot;

public class Execution extends Thread {
    DataInputStream inputStream;

    public Execution(DataInputStream dis) {
        inputStream = dis;
        start();
    }

    @Override
    public void run() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                int event = inputStream.readInt();
                if (event == 1)
                    robot.keyPress(inputStream.readInt());
                if (event == -1)
                    robot.keyRelease(inputStream.readInt());
                if (event == 2)
                    robot.mousePress(inputStream.readInt());
                if (event == -2)
                    robot.mousePress(inputStream.readInt());
                if (event == 3) {
                    String mouseInfo = inputStream.readUTF();
                    int x = Integer.parseInt(mouseInfo.split("//")[0]);
                    int y = Integer.parseInt(mouseInfo.split("//")[1]);
                    robot.mouseMove(x, y);
                }
            } catch (IOException e) {

            }
        }
    }
}
