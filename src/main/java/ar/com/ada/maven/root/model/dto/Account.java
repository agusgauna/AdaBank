package ar.com.ada.maven.root.model.dto;

public class Account {
    private int id;
    private String currency;
    private int accountNumber;
    private double balance;
    private Client client;

    public Account() {
    }

    public Account(int id, String currency, int accountNumber, double balance, Client client) {
        this.id = id;
        this.currency = currency;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
