package com.tiy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by localdom on 6/3/2016.
 */
public class SimpleThreadRunner {

    public static final int RACER_LANE_LENGTH = 20;
    public static final int NUM_RACERS = 7;
    ArrayList<SimpleRacer> racers = new ArrayList<SimpleRacer>();

    public static void main(String[] args) throws Exception {
        SimpleThreadRunner runner = new SimpleThreadRunner();
        runner.testThreading();
    }

    public void testThreading() throws Exception {
        for (int racerIndex = 0; racerIndex < NUM_RACERS; racerIndex++) {
            SimpleRacer racer = new SimpleRacer(this);
            racers.add(racer);
            Thread racerThread = new Thread(racer);
            racerThread.start();
        }
    }

    public void printSimpleRacers() {
        System.out.print("\r");

        for (SimpleRacer racer : racers) {
            int bufferLength = RACER_LANE_LENGTH - racer.racerString.length();
            System.out.print(racer.racerString);
            for (int counter = 0; counter <= bufferLength; counter++) {
                System.out.print(" ");
            }
            System.out.print(" O ");
        }
    }
}

class SimpleRacer implements Runnable {

    public String racerString = "";
    public String token = "-";
    private SimpleThreadRunner runner;

    public SimpleRacer(SimpleThreadRunner runner) {
        this.runner = runner;
    }

    public void run() {
        Double sleepDouble = Math.random() * 200;
        int sleepInt = sleepDouble.intValue();

        long timeStarted = System.currentTimeMillis();
        while (true) {
            racerString += token;

            try {
                Thread.sleep(sleepInt);
                runner.printSimpleRacers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (racerString.length() > ThreadTestRunner.RACER_LANE_LENGTH) {
                break;
            }
        }
    }
}
