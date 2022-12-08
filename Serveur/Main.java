package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import generer.Generate;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(3204);
        System.out.println("En attente de connexion");
        Socket socket = server.accept();
        System.out.println("Connexion reussi");
        new Generate(socket);
    }
}
