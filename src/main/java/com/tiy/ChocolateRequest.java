package com.tiy;

/**
 * Created by localdom on 6/1/2016.
 */
public class ChocolateRequest {
    public int bigs = 0;
    public int smalls = 0;
    public int kilos = 0;

    @Override
    public String toString() {
        String returnString = new String();
        returnString += "Bigs::\t\t" + bigs + "\n";
        returnString += "Smalls::\t" + smalls + "\n";
        returnString += "Kilos::\t\t" + kilos + "\n";

        return returnString;
    }
}
