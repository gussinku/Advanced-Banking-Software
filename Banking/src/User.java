/**
 * This class represents the data for a user of the bank
 */

public class User {
	// private instance variables
	private String username;
	private String password;
	private Account account;

	// Constructor with parameters
	// Create a new account and record the "transaction"
	public User(String name, String password, int accountId) {
		this.username=name;
		this.password=password;
		this.account= new Account(0,accountId);
	}

	/**
	 * Returns the account id
	 * @return id
	 */

	public int getId() {
		return this.account.getId();
	}

	/**
	 * Returns the username
	 * @return name
	 */

	public String getUsername() {
		return this.username;
	}

	/**
	 * Returns the password
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns the user Account
	 * @return account
	 */

	public Account getAccount() {

		return this.account;
	}
}
