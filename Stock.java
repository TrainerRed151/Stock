// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.util.LinkedList;

public class Stock {
    public static void main(String args[]) throws InterruptedException {
        int time;

        if (args.length == 0) {
            time = 60;
        }
        else {
            time = Integer.parseInt(args[0]);
        }
        
        BrianReader reader = new BrianReader("stocks.txt");
        
        LinkedList<Quote> stocks = new LinkedList<Quote>();

        String temp = reader.readFileLine();
        int c = 13;
        while (temp != "") {
            if (temp.indexOf((char) c) != -1) {
                temp = temp.substring(0,temp.indexOf((char) c));
            }

            stocks.add(new Quote(temp));
            temp = reader.readFileLine();
        }

        while (true) {
            //reader.cls();
            cls();
            
            for (int i = 0; i < stocks.size(); i++) {
                stocks.get(i).update();
                System.out.println(stocks.get(i));
            }

            Thread.sleep(time*1000);
        }
    }

    public static void cls() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
