package banking.exception;

public class WrongCardNumberOrPinException extends Exception {

    private static final long serialVersionUID = 1L;

    public WrongCardNumberOrPinException() {
	super("Wrong card number or PIN!");
    }

}