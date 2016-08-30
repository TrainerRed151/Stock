// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.util.LinkedList;

public class Stock {
    public static void main(String args[]) throws InterruptedException {
        int time;

        if (args.length == 0) {
            time = 10;
        }
        else {
            time = Integer.parseInt(args[0]);
        }
        
        BrianReader reader = new BrianReader("stocks.txt");
        
        LinkedList<Quote> stocks = new LinkedList<Quote>();

        String temp = reader.readFileLine();
        while (temp != "") {
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
        System.out.print("\033[H\033[2J");
        
        //for (int i = 0; i < 30; i++) {
        //    System.out.println();
        //}
    }
}
