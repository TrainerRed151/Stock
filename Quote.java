// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.net.URL;
import java.io.*;

public class Quote {
    private String symbol;
    private double price;
    private double change;

    public Quote(String s) {
        symbol = s;
        price = 0;
        change = 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getChange() {
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

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
