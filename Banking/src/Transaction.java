/**
 * This class implements a transaction performed by the bank users
 */

public class Transaction {
	// private instance variables
	private String type;
	private Account account;

	// constructor with parameters
	public Transaction(Account account, String type) {
		this.account=account;
		this.type=type;
	}

	/**
	 * Returns the Account object
	 * @return account
	 */
	public Account getAccount() {
		return this.account;
	}

	/**
	 * String representation of a transaction
	 */
	public String toString() {
		return this.type;
	}
}
