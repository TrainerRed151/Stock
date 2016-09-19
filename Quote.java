// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.net.URL;
import java.io.*;

public class Quote {
    private String symbol;
    private float price;
    private float change;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_RED = "\u001B[31m";

    public Quote(String s) {
        symbol = s;
        price = 0;
        change = 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public float getPrice() {
        return price;
    }

    public float getChange() {
        return change;
    }

    public boolean update() {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        String address = "http://finance.google.com/finance/info?client=ig&q=" + symbol;

        try {
            url = new URL(address);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            //while ((line = br.readLine()) != null) {
            //    System.out.println(line);
            //}

            for (int i = 0; i < 7; i++) {
                line = br.readLine();
            }

            line = br.readLine();

            int lstart = 12;
            int lend = line.substring(lstart).indexOf('\"') + lstart;

            price = Float.parseFloat(line.substring(lstart,lend));
            
            for (int i = 0; i < 5; i++) {
                line = br.readLine();
            }

            line = br.readLine();
            
            int cstart = 8;
            int cend = line.substring(cstart).indexOf('\"') + cstart;
            
            change = Float.parseFloat(line.substring(cstart,cend));

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public String toString() {
        String s;
        String color;

        if (change < 0) {
            color = ANSI_RED;
        }
        else {
            color = ANSI_GREEN;
        }

        if (change < 0) {
            s = color + symbol + ":\t" + price + "\t" + change;
        }
        else {
            s = color + symbol + ":\t" + price + "\t+" + change;
        }
        
        return s + ANSI_RESET;
    }
}
