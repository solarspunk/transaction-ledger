package com.michael.transactionledger.service;

import com.michael.transactionledger.model.Transaction;
import com.michael.transactionledger.repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


//filtering (month, vendor, etc.)
public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void save(Transaction tt) {
        repository.save(tt);
    }

    //A all
    //return all transactions from CSV
    public ArrayList<Transaction> returnAll() {
        ArrayList<Transaction> list = repository.readAllLines();
        Collections.reverse(list); //newest first


        return list;
    }

    //D deposits
    public ArrayList<Transaction> getDeposits() {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t: getAll()) {
            if (t.depositTrue()) {
                result.add(t);
            }
        }

        return result;
    }

    private ArrayList<Transaction> getAll() {
        return repository.readAllLines();
    }

    //P payments
    public ArrayList<Transaction> getPayments() {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t: getAll()) {
            if (!t.depositTrue()) {
                result.add(t);
            }
        }

        return result;
    }

                //R reports screen
    //1 get month to date
    public ArrayList<Transaction> getMonthToDate() {
        ArrayList<Transaction> result = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Transaction t: getAll()) {
            if (t.getDate().getMonth() == now.getMonth() &&
                    t.getDate().getYear() == now.getYear()) {
                result.add(t);
            }
        }

        return result;
    }

    //2 get previous month
    public ArrayList<Transaction> getPreviousMonth() {
        ArrayList<Transaction> result = new ArrayList<>();
        LocalDate last = LocalDate.now().minusMonths(1);
        for (Transaction t: getAll()) {
            if (t.getDate().getMonth() == last.getMonth() &&
                    t.getDate().getYear() == last.getYear()) {
                result.add(t);
            }
        }

        return result;
    }

    //3 get year to date
    public ArrayList<Transaction> getYearToDate() {
        ArrayList<Transaction> result = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (Transaction t: getAll()) {
            if (t.getDate().getYear() == year) {
                result.add(t);
            }
        }

        return result;
    }

    //4 get previous year
    public ArrayList<Transaction> getPreviousYear() {
        ArrayList<Transaction> result = new ArrayList<>();
        int year = LocalDate.now().getYear() - 1;
        for (Transaction t: getAll()) {
            if (t.getDate().getYear() == year)  {
                result.add(t);
            }
        }

        return result;
    }

    //5 search by vendor
    public ArrayList<Transaction> searchByVendor(String vendor) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t: getAll()) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                result.add(t);
            }
        }

        return result;
    }

}
