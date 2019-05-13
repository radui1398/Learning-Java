package network;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import app.Person;
import exceptions.InvalidArgumentsException;

public class ClientThread extends Thread {
    private Socket socket = null;
    private final SocialNetworkServer socialNetworkServer;
    private Person identity;

    private String msgToSend = "";

    private DataInputStream input = null;
    private DataOutputStream output = null;

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public ClientThread(Socket socket, SocialNetworkServer server) {
        this.socket = socket;
        this.socialNetworkServer = server;

        System.out.println("entered client thread constructor");

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            input = null;
            output = null;

            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }


    @Override
    public void run() {
        if (input == null || output == null) {
            System.out.println("Connection failed.");
            return;
        }

        if(!socialNetworkServer.isAcceptClients()){
            try {
                output.writeUTF("Nu te mai poti conecta la server.");
            }catch(IOException e){
                System.out.println("");
            }
        }
        else {

            try {
                while (true) {

                    String inputData = input.readUTF();

                    String outputData;
                    try {
                        outputData = socialNetworkServer.runCommand(this, inputData);
                    } catch (InvalidArgumentsException e) {
                        outputData = e.toString();
                    }
                    output.writeUTF(outputData);

                }
            } catch (IOException e) {
                socialNetworkServer.minusOneClient();
                identity.setLoggedIn(false);
                System.out.println("connection closed");
            }
        }
    }

    public void setIdentity(Person identity) {
        this.identity = identity;
    }

    public Person getIdentity() {
        return identity;
    }

    public Socket getSocket() {
        return socket;
    }

    public void addToMessage(String msg){
        msgToSend = msgToSend + msg;
    }

    public String getMessage(){
        if(msgToSend.equals(""))
            return "Nu exista nici un mesaj in inbox.";
        return msgToSend;
    }

    public void setMsgToSend(String msgToSend) {
        this.msgToSend = msgToSend;
    }
}
