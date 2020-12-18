package banking;

import banking.account.AccountController;

public class Main {

    public static void main(String[] args) {
	UserInterface userInterface = new UserInterface(AccountController.getInstance());
	userInterface.run();
    }

}