package com.company;

import network.SocialNetworkClient;

public class Timekeeper implements Runnable {
    static private int TIME_SECONDS = 30;

    private SocialNetworkClient main;
    private TimeChanger timeLeft;

    public void setTheTime(TimeChanger theTime){
        this.timeLeft = theTime;
    }

    public void setMain(SocialNetworkClient main) {

        this.main = main;
    }

    @Override
    public void run() {
        timeLeft.setValue(TIME_SECONDS);

            while (timeLeft.getValue() > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeLeft.setValue(timeLeft.getValue()-1);
            }
        main.exit();
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft.setValue(timeLeft);
    }

}
