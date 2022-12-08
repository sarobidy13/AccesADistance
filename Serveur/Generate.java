package generer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import listener.Listener;

import javax.sound.sampled.LineListener;
import javax.swing.JFrame;
import receive.Receive;
import panel.PanelImage;

public class Generate {
    PanelImage panelI = new PanelImage();
    JFrame frame;
    Listener li;
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public Generate(Socket s) throws IOException {
        socket = s;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());

        new Receive(dis, panelI);

        li = new Listener(dos);
        createFrame();

    }

    public void createFrame() {
        frame = new JFrame("Screen Of Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.add(panelI);
        panelI.setFocusable(true);
        frame.setFocusable(false);

        panelI.addKeyListener(li);
        panelI.addMouseListener(li);
        panelI.addMouseMotionListener(li);

        frame.setVisible(true);

    }
}
