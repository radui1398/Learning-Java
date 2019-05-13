package network;

import app.SocialNetwork;
import exceptions.AlreadyRegisteredException;
import exceptions.InvalidArgumentsException;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SocialNetworkServer implements Runnable {
    private int port;
    private int currClients = 0;
    private ServerSocket serverSocket;
    private SocialNetwork network;
    private boolean acceptClients = true;
    private boolean running;

    public SocialNetworkServer(int port,String networkName){
        this.port = port;
        network = new SocialNetwork(networkName);
    }

    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(port);
            running = true;
            while (true){
                    Socket clientSocket = serverSocket.accept();
                    currClients++;
                    new ClientThread(clientSocket, this).start();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            running = false;
        }
    }

    public String runCommand(ClientThread personThread, String inputData) throws InvalidArgumentsException {

        System.out.println("received " + inputData);

        String[] args = splitArguments(inputData);

        if (args.length < 2 && !args[0].equals("read") && !args[0].equals("stop")){
            throw new InvalidArgumentsException("At least 2 arguments pls...");
        }

        if (args[0].equals("register")){
            return registerPerson(personThread, args[1]);
        }
        if (args[0].equals("login")){
            return loginPerson(personThread, args[1]);
        }
        if (args[0].equals("friend")){
            return friend(personThread, args);
        }
        if (args[0].equals("send")){
            return sendMessage(personThread, args);
        }
        if (args[0].equals("read")) {
            return readMessage(personThread);
        }
        if (args[0].equals("stop")){
            acceptClients = false;
            return "Am oprit conectarea la server";
        }
        throw new InvalidArgumentsException("First argument must be \"register\", \"login\", \"friend\", \"read\" or \"send\" ...");
    }

    private String readMessage(ClientThread personThread) {
        String msg = personThread.getMessage();
        personThread.setMsgToSend("");
        return msg;
    }

    private String sendMessage(ClientThread personThread, String[] message) {
        String[] newM = Arrays.copyOfRange(message, 1, message.length);
        return network.sendMSG(personThread,newM);
    }

    private String friend(ClientThread personThread, String[] args) {
        String[] names = Arrays.copyOfRange(args, 1, args.length);
        return network.addFriend(personThread,names);
    }

    private String loginPerson(ClientThread personThread, String name) {
        return network.login(personThread,name);
    }

    private String registerPerson(ClientThread personThread, String name) {
         return network.register(personThread,name);
    }

    private String[] splitArguments(String line) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> args = new ArrayList<>();

        boolean inQuotes = false;
        for (int i = 0; i < line.length(); ++i){
            char c = line.charAt(i);
            if (c == '\"'){
                inQuotes = !inQuotes;
            }
            else if (c == ' '){
                if (inQuotes){
                    builder.append(c);
                }
                else{
                    if (builder.length() > 0){
                        args.add(builder.toString());
                    }
                    builder.setLength(0);
                }
            }
            else{
                builder.append(c);
            }
        }
        if (builder.length() > 0){
            args.add(builder.toString());
        }
        String[] strings = new String[args.size()];
        strings = args.toArray(strings);
        return strings;
    }

    public void minusOneClient(){
        currClients--;
    }

    public boolean isAcceptClients() {
        return acceptClients;
    }


}

