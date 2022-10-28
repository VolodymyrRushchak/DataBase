package com.rushchak.jdbc_template_lab.view;

import com.rushchak.jdbc_template_lab.controller.*;
import com.rushchak.jdbc_template_lab.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Scanner;

@Service
public class MainView {
    @Autowired
    private AccountController accountController;

    @Autowired
    private CardController cardController;

    @Autowired
    private CardInfoController cardInfoController;

    @Autowired
    private ClientController clientController;

    @Autowired
    private TransactionController transactionController;

    Scanner input = new Scanner(System.in);
    int chosenTable = 0;

    private void showMenu() {
        System.out.println("1 - Account");
        System.out.println("2 - Card");
        System.out.println("3 - CardInfo");
        System.out.println("4 - Client");
        System.out.println("5 - Transaction");
        System.out.println("Choose a table (or Q to exit):\n");
    }

    private void showActions() {
        System.out.println("1 - Print rows");
        System.out.println("2 - Add a new row");
        System.out.println("3 - Delete a row");
        System.out.println("4 - Update a row");
        System.out.println("Choose an action (Q to exit or B to go back):\n");
    }
    public void show() {
        String key;

        showMenu();
        do {
            key = input.next();
            if (!key.equals("Q") && chosenTable == 0){
                try {
                    int option = Integer.parseInt(key);
                    if (option <= 0 || option > 5) {
                        System.out.println("There is no such an option :(");
                        continue;
                    }
                    chosenTable = option;
                } catch (NumberFormatException exc) {
                    System.out.println("Please enter a number");
                }
            }
            if (!key.equals("Q")  && chosenTable != 0) {
                showActions();
                do {
                    key = input.next();
                    if (!key.equals("Q") && !key.equals("B")) {
                        try {
                            int action = Integer.parseInt(key);
                            if (action <= 0 || action > 4) {
                                System.out.println("There is no such an option :(");
                                continue;
                            }
                            switch (action) {
                                case 1 -> findAll();
                                case 2 -> create();
                                case 3 -> delete();
                                case 4 -> update();
                            }
                            key = "B";
                        } catch (NumberFormatException exc) {
                            System.out.println("Wrong number format");
                        }
                    }
                } while (!key.equals("Q") && !key.equals("B"));
            }
            if (key.equals("B")) {
                showMenu();
                chosenTable = 0;
            }
        } while (!key.equals("Q"));
    }

    private void findAll(){
        String key;
        System.out.println("1 - Get all");
        System.out.println("2 - Get by id");
        if (chosenTable == 1 || chosenTable == 3 || chosenTable == 4) {
            System.out.println("3 - Get by name");
        }
        if (chosenTable == 4)
            System.out.println("4 - Get by surname");
        System.out.println("What do you want? (B - back)");
        do {
            key = input.next();
            if (key.equals("B"))
                return;
            try {
                int option = Integer.parseInt(key);
                if (option == 1)
                    switch (chosenTable){
                        case 1 -> accountController.findAll().forEach(System.out::println);
                        case 2 -> cardController.findAll().forEach(System.out::println);
                        case 3 -> cardInfoController.findAll().forEach(System.out::println);
                        case 4 -> clientController.findAll().forEach(System.out::println);
                        case 5 -> transactionController.findAll().forEach(System.out::println);
                    }
                if (option == 2){
                    System.out.println("Enter id:");
                    int id = input.nextInt();
                    switch (chosenTable){
                        case 1 -> System.out.println(accountController.findById(id));
                        case 2 -> System.out.println(cardController.findById(id));
                        case 3 -> System.out.println(cardInfoController.findById(id));
                        case 4 -> System.out.println(clientController.findById(id));
                        case 5 -> System.out.println(transactionController.findById(id));
                    }
                }
                if (option == 3){
                    System.out.println("Enter name:");
                    String name = input.next();
                    switch (chosenTable){
                        case 1 -> System.out.println(accountController.findByName(name));
                        case 3 -> System.out.println(cardInfoController.findByName(name));
                        case 4 -> System.out.println(clientController.findByName(name));
                    }
                }
                if (option == 4){
                    System.out.println("Enter surname:");
                    String surname = input.next();
                    switch (chosenTable){
                        case 4 -> System.out.println(clientController.findBySurname(surname));
                    }
                }
            }
            catch (NumberFormatException exc) {
                System.out.println("Wrong number");
            }
        } while (true);
    }

    private void create(){
        switch (chosenTable) {
            case 1 -> {
                accountController.create(getNewAccount());
            }
            case 2 -> {

                cardController.create(getNewCard());
            }
            case 3 -> {

                cardInfoController.create(getNewCardInfo());
            }
            case 4 -> {
                clientController.create(getNewClient());
            }
            case 5 -> {
                transactionController.create(getNewTransaction());
            }
        }
    }

    private void delete(){
        System.out.println("Enter id of row to delete:");
        int id = input.nextInt();
        switch (chosenTable) {
            case 1 -> accountController.delete(id);
            case 2 -> cardController.delete(id);
            case 3 -> cardInfoController.delete(id);
            case 4 -> clientController.delete(id);
            case 5 -> transactionController.delete(id);
        }
    }

    private void update(){
        System.out.println("Enter id of row to update:");
        int id = input.nextInt();
        switch (chosenTable) {
            case 1 -> {
                accountController.update(id, getNewAccount());
            }
            case 2 -> {

                cardController.update(id, getNewCard());
            }
            case 3 -> {

                cardInfoController.update(id, getNewCardInfo());
            }
            case 4 -> {
                clientController.update(id, getNewClient());
            }
            case 5 -> {
                transactionController.update(id, getNewTransaction());
            }
        }
    }

    private Account getNewAccount(){
        System.out.println("Enter client id:");
        int clientId = input.nextInt();
        System.out.println("Enter account name:");
        String name = input.next();
        Date dateCreated = new Date();
        return new Account(null, clientId, name, new java.sql.Date(dateCreated.getTime()));
    }

    private Card getNewCard(){
        System.out.println("Enter account id:");
        int accountId = input.nextInt();
        System.out.println("Enter cardInfo id:");
        int cardInfoId = input.nextInt();
        return new Card(null, accountId, cardInfoId);
    }

    private CardInfo getNewCardInfo(){
        System.out.println("Enter card balance:");
        float balance = input.nextFloat();
        System.out.println("Enter card name:");
        String name = input.next();
        Date dateExpires = new Date();
        return new CardInfo(null, balance, name, null, new java.sql.Date(dateExpires.getTime()));
    }

    private Client getNewClient(){
        System.out.println("Enter client's name:");
        String name = input.next();
        System.out.println("Enter client's surname:");
        String surname = input.next();
        System.out.println("Enter client's phone number:");
        String phoneNumber = input.next();
        System.out.println("Enter client's email:");
        String email = input.next();
        return new Client(null, name, surname, phoneNumber, email);
    }

    private Transaction getNewTransaction(){
        Date date = new Date();
        System.out.println("Enter transaction amount:");
        float amount = input.nextFloat();
        System.out.println("Enter transaction purpose:");
        String purpose = input.next();
        System.out.println("Enter sender's id:");
        int senderId = input.nextInt();
        System.out.println("Enter receiver's id:");
        int receiverId = input.nextInt();
        return new Transaction(null, new java.sql.Date(date.getTime()), amount, purpose, senderId, receiverId);
    }

}
