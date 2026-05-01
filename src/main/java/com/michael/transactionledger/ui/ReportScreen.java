package com.michael.transactionledger.ui;

import com.michael.transactionledger.service.Service;
import com.michael.transactionledger.model.Transaction;

import java.util.Scanner;

public class ReportScreen {

    private Service service;
    private Scanner scanner;

    public ReportScreen(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public boolean start() {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n|--------* Report Screen *--------|");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "1" -> show(service.getMonthToDate());
                case "2" -> show(service.getPreviousMonth());
                case "3" -> show(service.getYearToDate());
                case "4" -> show(service.getPreviousYear());
                case "5" -> {
                    System.out.println("Enter vendor:");
                    String v = scanner.nextLine();
                    show(service.searchByVendor(v));
                }
                case "0" -> isRunning = false;
                case "X" -> {
                    return false;
                }
            }
        }
        return true;//default
    }

    private void show(java.util.List<Transaction> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No entries found");
            return;
        }

        for (Transaction t: list) {
            System.out.println(t);
        }
    }
}