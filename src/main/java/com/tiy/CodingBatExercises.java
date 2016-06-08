package com.tiy;

/**
 * Created by localdom on 5/25/2016.
 */
public class CodingBatExercises {

    public boolean evenlySpaced(int a, int b, int c) {
        int diff1 = Math.abs(a - b);
        int diff2 = Math.abs(a - c);
        int diff3 = Math.abs(b - c);

        System.out.println("diff1 = " + diff1);
        System.out.println("diff2 = " + diff2);
        System.out.println("diff3 = " + diff3);

        if ( ((diff1 == diff2) && (diff3 != 0)) || ((diff1 == diff3) && diff2 != 0)) {
            return true;
        }

        if (diff1 == diff2 && diff1 == diff3) {
            return true;
        }

        return false;
    }

    /**
     *
     * Given a string and a non-empty word string, return a version of the original String
     * where all chars have been replaced by pluses ("+"), except for appearances of the word string which are preserved unchanged.
     *
     * plusOut("12xy34", "xy") → "++xy++"
     * plusOut("12xy34", "1") → "1+++++"
     * plusOut("12xy34xyabcxy", "xy") → "++xy++xy+++xy"
     *
     *
     * @param str
     * @param word
     * @return
     */
    public String plusOut(String str, String word) {
        int wordIndex = str.indexOf(word);
        String processedString = "";
        String workingString = str;
        while (wordIndex != -1) {
            processedString += workingString.substring(0, wordIndex).replaceAll(".", "+") +
                                word;
            workingString = workingString.substring(wordIndex + word.length());
            wordIndex = workingString.indexOf(word);
        }
        processedString += workingString.replaceAll(".", "+");

        return processedString;
    }

    public ChocolateSolution makeChocolate(int small, int big, int goal) {

//        try {
//            Thread.sleep(2000);
//        } catch (Exception exception) {
//        }

        ChocolateSolution solution = new ChocolateSolution();
        int maxGoal = small + (big * 5);
//        System.out.println("maxGoal is " + maxGoal);
//        System.out.println("goal is " + goal);

        if (goal > maxGoal) {
            System.out.println("Not enough chocolate");
            solution.setNoSolution();
            return solution;
        }

        int smallsRequired;
        int bigsRequired = 0;
        if (big == 0) {
            smallsRequired = goal;
        } else {
            smallsRequired = goal % 5;
            bigsRequired = goal / 5;
        }

        if (smallsRequired > small) {
            System.out.println("Not enough smalls");
            solution.setNoSolution();
            return solution;
        }

        if (bigsRequired > big) {
            smallsRequired += 5 * (bigsRequired - big);
            bigsRequired = big;
            if (smallsRequired > small) {
                System.out.println("Not enough bigs");
                solution.setNoSolution();
                return solution;
            }
        }

        solution.setSolution(smallsRequired, bigsRequired);

        return solution;
    }

    public static void main(String[] args) {
        new CodingBatExercises().recursiveExample("Here is a sample string", 0);
    }

    public int recursiveExample(String sample, int iteration) {
        System.out.println(sample);
        if (iteration < 10) {
            iteration = recursiveExample(sample + "*", iteration + 1);
            return iteration;
        } else {
            System.out.println("Done recursing");
            return 0;
        }
    }
}

class ChocolateSolution {
    public int smalls;
    public int bigs;
    public boolean hasSolution = false;

    public void setNoSolution() {
        smalls = -1;
        bigs = -1;
        hasSolution = false;
    }

    public void setSolution(int smalls, int bigs) {
        hasSolution = true;
        this.smalls = smalls;
        this.bigs = bigs;
    }
}
