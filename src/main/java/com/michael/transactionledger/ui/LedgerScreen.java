package com.michael.transactionledger.ui;

import com.michael.transactionledger.model.Transaction;
import com.michael.transactionledger.service.Service;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerScreen {
    private Service service;
    private Scanner scanner;

    public LedgerScreen(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {


        boolean isRunning = true;

        while (isRunning) {

            System.out.println("\n|--------* Ledger Screen *--------|");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "A" -> show(service.returnAll());
                case "D" -> show(service.getDeposits());
                case "P" -> show(service.getPayments());
                case "R" -> new ReportScreen(service, scanner).start();
                case "H" -> isRunning = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void show(ArrayList<Transaction> list) {
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}