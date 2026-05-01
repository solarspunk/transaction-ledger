package com.michael.transactionledger.repository;


import com.michael.transactionledger.model.Transaction;

import java.io.*;
import java.util.ArrayList;


//reads/writes to CSV
public class Repository {

    //file path
private String filePath = "data/transactions.csv";

//reads lines and makes transaction obkects
public ArrayList<Transaction> readAllLines() {
    ArrayList<Transaction> transactions = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine())!=null) {
            if (!line.isBlank()) {
                transactions.add(Transaction.readFromCSV(line));
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file");
    }

    return transactions; // Bug 3: you were missing the return statement
}


    public void save(Transaction t) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.write(t.writeToCSV());
            bufferedWriter.newLine();
        }
        catch (IOException e) {
            System.out.println("Error saving transaction");
        }
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

