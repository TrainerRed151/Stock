// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.net.URL;
import java.io.*;


public class Quote {
    private String symbol;
    private float price, change, ma50, ma200;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_RED = "\u001B[31m";

    public Quote(String s) {
        symbol = s;
        price = 0;
        change = 0;
        ma50 = 0;
        ma200 = 0;
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

    public float getMA50() {
        return ma50;
    }

    public float getMA200() {
        return ma200;
    }

    public boolean update() {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        String address = "http://download.finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=l1c1m3m4";

        try {
            url = new URL(address);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));
            
            line = br.readLine();

            /*
            float[] info = new float[2];
            int comma = -1;

            for (int i = 0; i < info.length; i++) {
                line = line.substring(comma+1);
                comma = line.indexOf(',');
                info[i] = Float.parseFloat(line.substring(0,comma));
                System.out.println(info[i]);
            }
            */

            int comma = line.indexOf(',');
            int comma2 = line.substring(comma+1).indexOf(',') + comma + 1;
            int comma3 = line.substring(comma2+1).indexOf(',') + comma2 + 1;

            price = Float.parseFloat(line.substring(0,comma));
            change = Float.parseFloat(line.substring(comma+1, comma2));
            ma50 = Float.parseFloat(line.substring(comma2+1, comma3));
            ma200 = Float.parseFloat(line.substring(comma3+1));
 
            /*
            System.out.println(info[0]);
            price = info[0];
            change = info[1];
            System.out.println(price);
            */

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
            s = color + symbol + ":\t" + price + "\t" + change + "\t" + ma50 + ":" + ma200;
        }
        else {
            s = color + symbol + ":\t" + price + "\t+" + change + "\t" + ma50 + ":" + ma200;
        }
        
        return s + ANSI_RESET;
    }
}
