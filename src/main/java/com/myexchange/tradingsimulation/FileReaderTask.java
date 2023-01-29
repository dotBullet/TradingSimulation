package com.myexchange.tradingsimulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTask implements Runnable {
    private final String symbol;
    private final String phase;
    private final OrderBook orderBook;

    public FileReaderTask(String symbol, String phase, OrderBook orderBook) {
        this.symbol = symbol;
        this.phase = phase;
        this.orderBook = orderBook;
    }

    @Override
    public void run() {
        try {
            // code to read and parse the file for the given symbol and phase
            // and populate the corresponding OrderBook
            File file = new File("path/to/file/"+symbol+"-"+phase+".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                //parse the line and update the orderBook
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

