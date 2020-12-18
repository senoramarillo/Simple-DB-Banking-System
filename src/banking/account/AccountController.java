package banking.account;

import java.util.concurrent.ThreadLocalRandom;

import banking.exception.WrongCardNumberOrPinException;

public class AccountController {

    private static final AccountController INSTANCE = new AccountController(AccountRepository.getInstance());
    private static final int MII = 4;
    private static final int IIN = MII * 1000_00;

    private final AccountRepository repo;

    public static AccountController getInstance() {
	return INSTANCE;
    }

    private AccountController(AccountRepository repo) {
	this.repo = repo;
    }

    public Account createNewAccount() {
	var random = ThreadLocalRandom.current();

	// digits 7 to 16 inclusively
	long customerAccountNumber = random.nextLong(0, 9_9999_9999L);
	long id = IIN * 1000_0000_000L + customerAccountNumber;

	int pin = random.nextInt(1000, 9999);

	var account = new Account(id, pin);
	repo.add(account);
	return account;
    }

    public Account logIntoAccount(long id, int pin) throws WrongCardNumberOrPinException {
	return repo.getById(id).filter(account -> account.getPin() == pin)
		.orElseThrow(WrongCardNumberOrPinException::new);
    }

}