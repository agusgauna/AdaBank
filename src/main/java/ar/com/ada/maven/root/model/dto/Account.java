package ar.com.ada.maven.root.model.dto;

public class Account {
    private Integer id;
    private String currency;
    private Integer number;
    private Double balance;
    private String controlNumber;
    private Client client;
    private Account_type account_type;
    private Branch branch;

    public Account() { }

    public Account(Integer id, String currency, Integer number, Double balance, String controlNumber, Client client, Account_type account_type, Branch branch) {
        this.id = id;
        this.currency = currency;
        this.number = number;
        this.balance = balance;
        this.controlNumber = controlNumber;
        this.client = client;
        this.account_type = account_type;
        this.branch = branch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
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

    @Override
    public String toString() {
        return "El id es: " + id +
                ". La moneda de la cuenta es: " + currency +
                ". El nro de cuenta es: " + number +
                ". El saldo es: " + balance +
                ". El número de control es: " +controlNumber+
                ". El cliente es: " + client +
                ". El tipo de cuenta es: " + account_type +
                ". La sucursal es: " + branch;
    }
}
