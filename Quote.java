// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.net.URL;
import java.io.*;

public class Quote {
    private String symbol;
    private float price;
    private float change;

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

        String address = "http://download.finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=l1c1";

        try {
            url = new URL(address);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            //while ((line = br.readLine()) != null) {
            //    System.out.println(line);
            //}
            
            line = br.readLine();

            int comma = line.indexOf(',');

            price = Float.parseFloat(line.substring(0,comma));
            change = Float.parseFloat(line.substring(comma+1));

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public String toString() {
        String s;
        String color;

        /* Color not working
        if (change < 0) {
            color = "\u001B31;1m";
        }
        else {
            color = "\u002B31;1m";
        }
        */
        color = "";

        if (change < 0) {
            s = color + symbol + ":\t" + price + "\t" + change;
        }
        else {
            s = color + symbol + ":\t" + price + "\t+" +change;
        }
        
        return s;
    }
}
