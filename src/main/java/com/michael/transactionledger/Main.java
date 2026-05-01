package com.michael.transactionledger;

import com.michael.transactionledger.repository.Repository;
import com.michael.transactionledger.service.Service;
import com.michael.transactionledger.ui.HomeScreen;


public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service       = new Service(repository);
        HomeScreen homeScreen = new HomeScreen(service);
        homeScreen.start();


        String filePath = "data/transactions.csv";
    }
}
