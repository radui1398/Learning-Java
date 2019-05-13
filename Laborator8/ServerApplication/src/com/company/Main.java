package com.company;

import network.SocialNetworkServer;

public class Main {



    public static void main(String[] args)  {
        SocialNetworkServer server = new SocialNetworkServer(8100,"fb");
        server.run();


    }
}
