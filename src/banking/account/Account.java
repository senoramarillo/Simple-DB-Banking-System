package banking.account;

public class Account {

    private long id;
    private int pin;
    private int balance;

    public Account(long id, int pin) {
	this.id = id;
	this.pin = pin;
	this.balance = 0;
    }

    public long getId() {
	return id;
    }

    public int getPin() {
	return pin;
    }

    public int getBalance() {
	return balance;
    }

}
