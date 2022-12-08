package receive;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import panel.PanelImage;
import javax.imageio.ImageIO;
import java.awt.*;

public class Receive extends Thread {

    DataInputStream lectureImage;
    PanelImage panelI;

    public Receive(DataInputStream lectureI,PanelImage panel) {
        lectureImage = lectureI;
        panelI = panel;

        start();
    }

    @Override
    public void run() {
        Image image = null;
        while (true) {

            try {
                byte[] sizeAr = new byte[4];
                lectureImage.readFully(sizeAr);

                int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
                byte[] imageAr = new byte[size];
                int totalRead = 0;
                int currentRead;
                while (totalRead < size
                        && (currentRead = lectureImage.read(imageAr, totalRead, size - totalRead)) > 0) {
                    totalRead += currentRead;
                }

                image = ImageIO.read(new ByteArrayInputStream(imageAr));


                Graphics g = panelI.getGraphics();

                g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}