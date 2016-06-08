package com.tiy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by localdom on 6/3/2016.
 */
public class ThreadTestRunner {

    public static final int RACER_LANE_LENGTH = 20;
    public static final int NUM_RACERS = 5;
    ArrayList<Racer> racers = new ArrayList<Racer>();
    ArrayList<Thread> racerThreads = new ArrayList<Thread>();
    String[] rankingLabels = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth"};

    public static void main(String[] args) throws Exception {
        ThreadTestRunner runner = new ThreadTestRunner();
        runner.testThreading();
    }

    public void testThreading() throws Exception {
        for (int racerIndex = 0; racerIndex < NUM_RACERS; racerIndex++) {
            Racer racer = new Racer(this, String.valueOf(racerIndex));
            racers.add(racer);
            Thread racerThread = new Thread(racer);
            racerThreads.add(racerThread);
            racerThread.start();
        }

        // wait for all the racers to finish
        for (Thread racerThread : racerThreads) {
            racerThread.join();
        }

        // sort by which racer finished first
        Collections.sort(racers);

        StringBuilder rankingSB = new StringBuilder();
        for (int counter = 0; counter <= RACER_LANE_LENGTH * NUM_RACERS + (3 * (NUM_RACERS + 1)); counter++) {
            rankingSB.append(" ");
        }

        int racerPosition = 0;
        int prefixPadding = RACER_LANE_LENGTH / 2 - 2;
        for (Racer racer: racers) {
            int startIndex = Integer.valueOf(racer.racerToken) * (RACER_LANE_LENGTH + 3) + prefixPadding;
            String rankingLabel = rankingLabels[racerPosition];
            char[] rankingLabelChars = rankingLabel.toCharArray();
            for (char labelChar : rankingLabelChars) {
                rankingSB.setCharAt(startIndex, labelChar);
                startIndex++;
            }
            racerPosition++;
        }

        System.out.println();
        System.out.println(rankingSB);
    }

    public void printRacers() {
        System.out.print("\r");

        for (Racer racer : racers) {
            int bufferLength = RACER_LANE_LENGTH - racer.racerString.length();
            System.out.print(racer.racerString);
            for (int counter = 0; counter <= bufferLength; counter++) {
                System.out.print(" ");
            }
            System.out.print(" O ");
        }
    }
}

class Racer implements Runnable, Comparable<Racer> {

    public String racerString = "";
    public String racerToken = "";
    public String token = "-";
    private ThreadTestRunner runner;
    public long timeTaken = 0;

    public Racer(ThreadTestRunner runner, String racerToken) {
        this.runner = runner;
        this.racerToken = racerToken;
    }

    public int compareTo(Racer otherRacer) {
        long diff = timeTaken - otherRacer.timeTaken;
        return (int)diff;
    }

    public void run() {
        Double sleepDouble = Math.random() * 400;
        int sleepInt = sleepDouble.intValue();

        long timeStarted = System.currentTimeMillis();
        while (true) {
            racerString += token;

            try {
                Thread.sleep(sleepInt);
                runner.printRacers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (racerString.length() > ThreadTestRunner.RACER_LANE_LENGTH) {
                racerString = "";
                for (int counter = 0; counter < ThreadTestRunner.RACER_LANE_LENGTH; counter++) {
                    racerString += "*";
                }
                break;
            }
        }
        long timeEnded = System.currentTimeMillis();
        timeTaken = timeEnded - timeStarted;
    }
}
