package DiffiHellman;

import java.io.*;
import java.net.Socket;

public class Alice {
    private int p;
    private int g;

    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    
    public Alice(int p, int g){
        try {
            socket = new Socket("localhost", 4004);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.p = p;
            sendMessage(p);
            System.out.println("P: " + p);
            this.g = g;
            sendMessage(g);
            System.out.println("G: " + g);
            int A = DiffiHellman.secretValue(p);
            System.out.println("A: " + A);
            sendMessage(A);
            int B = in.read();
            System.out.println("B: " + B);
            int key = DiffiHellman.quickExponentiation(B, A, p);
            System.out.println(B + " " + A + " " + p);
            System.out.println("Alice's key: " + key);

            socket.close();
            in.close();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
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
