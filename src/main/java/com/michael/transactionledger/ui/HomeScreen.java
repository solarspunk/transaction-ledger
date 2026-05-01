package com.michael.transactionledger.ui;

import com.michael.transactionledger.model.Transaction;
import com.michael.transactionledger.service.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HomeScreen {
    private Scanner scanner = new Scanner(System.in);
    private Service service;

    //constructor
    public HomeScreen(Service service) {
        this.service = service;
    }

    public void start() {

boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n|--------* Home Screen *--------|");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Input: ");

            String userInput = scanner.nextLine().toUpperCase();

            switch (userInput) {
                case "D" -> addTransaction(true);
                case "P" -> addTransaction(false);
                case "L" -> new LedgerScreen(service, scanner).start();
                case "X" -> isRunning = false;
                default  -> System.out.println("Invalid option, try again.");
            }
        }
    }


    private void addTransaction(boolean isDeposit) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Amount: ");
        double amount;
        while (true) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number:");
            }
        }
        if (!isDeposit) {
            amount = -amount;
        }

        System.out.println("Press enter to confirm\nOr press \"O\" to overwrite date/time");
        String overwriteDateTime = scanner.nextLine();
        if (overwriteDateTime.equalsIgnoreCase("o")) {

            while (true) {
                try {
                    System.out.println("Enter date (MM-DD-YYYY): ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                    date = LocalDate.parse(scanner.nextLine(), formatter);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please try again using MM-DD-YYYY (e.g. 05-23-1995).");//easter egg
                }
            }

            while (true) {
                try {
                    System.out.println("Enter time (hour:min AM/PM): ");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                    time = LocalTime.parse(scanner.nextLine(), timeFormatter);
                    break;
                } catch (DateTimeParseException ee) {
                    System.out.println("Invalid time format. Please try again using hour:min AM/PM (e.g. 4:04 AM).");
                }
            }
        } else System.out.println("Please either press enter or \"O\" as in orangutan...");

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        service.save(transaction);

        System.out.println("Transaction recorded");

    }

}
