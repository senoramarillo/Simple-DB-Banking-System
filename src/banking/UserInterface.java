package banking;

import java.util.Scanner;

import banking.account.Account;
import banking.account.AccountController;
import banking.exception.WrongCardNumberOrPinException;
import banking.menu.Menu;
import banking.menu.MenuBuilder;

public class UserInterface {

    private final Menu mainMenu;
    private final Menu accountMenu;
    private final AccountController accountController;
    private final Scanner scanner;

    private Account activeAccount;

    public UserInterface(AccountController accountController) {
	this.accountController = accountController;
	this.scanner = new Scanner(System.in);

	this.mainMenu = new MenuBuilder().setScanner(scanner).addItem(1, "Create an account", this::createAccount)
		.addItem(2, "Log into account", this::logIntoAccount).addItem(0, "Exit", this::mainMenuExit).build();

	this.accountMenu = new MenuBuilder().setScanner(scanner).addItem(1, "Balance", this::printBalance)
		.addItem(2, "Log out", this::logOut).addItem(0, "Exit", this::accountMenuExit).build();
    }

    private void mainMenuExit() {
	mainMenu.setExit();
    }

    private void accountMenuExit() {
	accountMenu.setExit();
	mainMenu.setExit();
    }

    public void run() {
	while (mainMenu.notExit()) {
	    mainMenu.selectItem().getAction().execute();
	}
	System.out.println("Bye!");
    }

    private void createAccount() {
	var account = accountController.createNewAccount();
	System.out.println("Your card has been created\n" + "Your card number:");
	System.out.println(account.getId());
	System.out.println("Your card PIN:");
	System.out.println(account.getPin());
	System.out.println();
    }

    private void logIntoAccount() {
	System.out.println("Enter your card number:");
	long id = Long.parseLong(scanner.nextLine());
	System.out.println("Enter your PIN:");
	int pin = Integer.parseInt(scanner.nextLine());
	System.out.println();

	try {
	    activeAccount = accountController.logIntoAccount(id, pin);
	    System.out.println("You have successfully logged in!");
	    System.out.println();

	    while (accountMenu.notExit()) {
		accountMenu.selectItem().getAction().execute();
	    }

	} catch (WrongCardNumberOrPinException e) {
	    System.out.println(e.getMessage());
	    System.out.println();
	}
    }

    private void printBalance() {
	System.out.println("Balance: " + activeAccount.getBalance());
	System.out.println();
    }

    private void logOut() {
	activeAccount = null;
	accountMenu.setExit();
	System.out.println("You have successfully logged out!");
	System.out.println();
    }

}