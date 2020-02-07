package ar.com.ada.maven.root.model.dto;

public class Account {
    private int id;
    private String currency;
    private int number;
    private double balance;
    private Client client;
    private Account_type account_type;
    private Branch branch;

    public Account() { }

    public Account(int id, String currency, int number, double balance, Client client, Account_type account_type, Branch branch) {
        this.id = id;
        this.currency = currency;
        this.number = number;
        this.balance = balance;
        this.client = client;
        this.account_type = account_type;
        this.branch = branch;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public Account_type getAccount_type() {
        return account_type;
    }

    public void setAccount_type(Account_type account_type) {
        this.account_type = account_type;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
