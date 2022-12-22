package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class Listener implements MouseListener, KeyListener, MouseMotionListener {

    DataOutputStream os;

    public Listener(DataOutputStream dos) {
        os = dos;

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            os.writeInt(1);
            os.writeInt(e.getKeyCode());
            os.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            os.writeInt(-1);
            os.writeInt(e.getKeyCode());
            os.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            int btn = 1024;
            if (e.getButton() == MouseEvent.BUTTON3) {
                btn = 4096;
            }
            os.writeInt(2);
            os.writeInt(btn);
            os.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            int btn = 1024;
            if (e.getButton() == MouseEvent.BUTTON3) {
                btn = 4096;
            }

            os.writeInt(-2);
            os.writeInt(btn);
            os.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        try {
            os.writeInt(3);
            os.writeUTF(e.getX() + "//" + e.getY());
            os.flush();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
