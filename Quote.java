// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.net.URL;
import java.io.*;

public class Quote {
    private String symbol;
    private float price;
    private float change;
    private boolean commodity;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_RED = "\u001B[31m";

    public Quote(String s) {
        symbol = s;
        price = 0;
        change = 0;

        if (s.equalsIgnoreCase("XAG") || s.equalsIgnoreCase("XAU") || s.equalsIgnoreCase("XPT")) {
            commodity = true;
        }
        else {
            commodity = false;
        }
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
        String line, address;
        
        if (commodity) {
            
            address = "http://download.finance.yahoo.com/d/quotes.csv?s=" + symbol + "USD=X&f=l1c1d1t1";
        }
        else {
            address = "http://finance.google.com/finance/info?client=ig&q=" + symbol;
        }

        try {
            url = new URL(address);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            //while ((line = br.readLine()) != null) {
            //    System.out.println(line);
            //}

            int lstart, lend, cstart, cend;

            if (commodity) {
                line = br.readLine();

                lstart = 0;
                lend = line.substring(lstart).indexOf(',') + lstart;

                cstart = lend + 1;
                cend = line.substring(cstart).indexOf(',') + cstart;

                price = Float.parseFloat(line.substring(lstart,lend));
                change = Float.parseFloat(line.substring(cstart,cend));
            }
            else {
                for (int i = 0; i < 7; i++) {
                    line = br.readLine();
                }

                line = br.readLine();

                lstart = 12;
                lend = line.substring(lstart).indexOf('\"') + lstart;
                
                price = Float.parseFloat(line.substring(lstart,lend));
                
                for (int i = 0; i < 5; i++) {
                    line = br.readLine();
                }

                line = br.readLine();
            
                cstart = 8;
                cend = line.substring(cstart).indexOf('\"') + cstart;    
                
                change = Float.parseFloat(line.substring(cstart,cend));
            }


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
