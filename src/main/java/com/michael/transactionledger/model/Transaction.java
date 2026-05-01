package com.michael.transactionledger.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//holds the data fields
public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    //constructor
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //writer
    public String writeToCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }

    //reader
    public static Transaction readFromCSV(String line) {
        String[] pieces = line.split("\\|");
        LocalDate date = LocalDate.parse(pieces[0]);
        LocalTime time = LocalTime.parse(pieces[1]);
        String description = (pieces[2]);
        String vendor = (pieces[3]);
        double amount = Double.parseDouble(pieces[4]);
        return new Transaction(date, time, description, vendor, amount);
    }





    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return String.format("%s %s %-15s %-15s %10.2f",
                date, time.format(timeFormatter), description + ",", vendor + ",", amount);
    }

    //check if deposit happens
    public boolean depositTrue() {
        return amount > 0;
    }
}
