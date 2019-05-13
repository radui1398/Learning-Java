package com.company;

import network.SocialNetworkClient;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        SocialNetworkClient client = new SocialNetworkClient();
        try {
            while (true) {
                String request = client.readFromKeyboard();
                if (request.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    client.sendRequestToServer(request);
                    System.out.println(client.readFromServer());
                }
            }
        }catch (RuntimeException e){
            System.out.println("Timpul a expirat!");
        }
    }
}
