package network;

import com.company.TimeChanger;
import com.company.Timekeeper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocialNetworkClient {
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;

    private Socket socket = null;
    private DataOutputStream out;
    private DataInputStream in;

    public SocialNetworkClient() throws IOException {
        socket = new Socket(SERVER_ADDRESS, PORT);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    public String readFromKeyboard() throws RuntimeException {
        Timekeeper keeper = new Timekeeper();
        keeper.setMain(this);
        keeper.setTheTime(new TimeChanger());

        Thread thread = new Thread(keeper);
        thread.setDaemon(true);
        thread.start();


        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();

        keeper.setTimeLeft(3000);


        return msg;
    }

    public void exit(){
        System.out.println("Ai fost scos de pe server.");
        System.exit(0);
    }

    public void sendRequestToServer(String request) throws IOException {
            out.writeUTF(request);

        out.flush();
    }


    public String readFromServer() throws IOException{
        String readFromSrv = in.readUTF();
        if(readFromSrv.equals("Nu te mai poti conecta la server.")) {
            System.out.println("Serverul nu mai accepta conexiuni.");
            exit();
        }
        return readFromSrv;
    }
}
