package DiffiHellman;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Bob {

    private static Socket socket;
    private static ServerSocket serverSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(4004);
            socket = serverSocket.accept();
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                int p = in.read();
                System.out.println("P: " + p);
                int g = in.read();
                System.out.println("G: " + g);
                int b = DiffiHellman.secretValue(p); //Bob's value
                int B = DiffiHellman.quickExponentiation(g, b, p);
                System.out.println("B: " + B);
                sendMessage(B);
                int A = in.read();
                System.out.println("A: " + A);
                int key = DiffiHellman.quickExponentiation(B, A, p);
                System.out.println(B + " " + A + " " + p);
                System.out.println("Bob's key: " + key);
            } finally {
                serverSocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
            System.err.println();
        }
    }

    public static void sendMessage(int value){
        try {
            out.write(value);
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
