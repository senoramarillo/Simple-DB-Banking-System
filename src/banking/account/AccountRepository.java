package banking.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {

    private static final AccountRepository INSTANCE = new AccountRepository();

    private final List<Account> accounts;

    public static AccountRepository getInstance() {
	return INSTANCE;
    }

    private AccountRepository() {
	accounts = new ArrayList<>();
    }

    public void add(Account account) {
	accounts.add(account);
    }

    public Optional<Account> getById(long id) {
	return accounts.stream().filter(account -> account.getId() == id).findFirst();
    }

}