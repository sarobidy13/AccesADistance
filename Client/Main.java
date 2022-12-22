package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import sendsary.SendSary;
import execution.Execution;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException {

        String ip = JOptionPane.showInputDialog("Entrer l'adresse ip du serveur");
        Socket socket = new Socket(ip, 5000);
        System.out.println("Connection etablie au server " + ip);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        new SendSary(dos);
        new Execution(dis);

    }
}