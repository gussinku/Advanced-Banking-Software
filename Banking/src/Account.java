/**
 * This class implements a user account, represented by a user id 
 * (unique for each account) and an amount (or balance)
 */

public class Account {
	// private instance variables
	private int id;
	private int amount;

	// constructor with parameters
	public Account(int amount, int id) {
		this.id=id;
		this.amount=amount;
	}
	
	/**
	 * Withdraw a given amount of money from the account and record the 
	 * transaction
	 * @param deductAmount the amount to withdraw
	 * @return true if the withdraw succeeds, false otherwise
	 */
	
	public boolean withdraw(int deductAmount){
		
		if (deductAmount <= this.amount) {
			this.amount-=deductAmount;
			Bank.transactions.add(new Transaction(this, "Withdrawal of " + deductAmount));
		
			return true;
		}
		
		else return false;
	}
	
	/**
	 * Deposit a given amount to the account and 
	 * record the transaction
	 * @param addAmount amount to be deposited
	 * @return true
	 */

	public boolean deposit(int addAmount) {
        return deposit(addAmount, true);
	}

    public boolean deposit(int addAmount,boolean log){
        this.amount+=addAmount;
        if (log) Bank.transactions.add(new Transaction(this, "Deposit of " + addAmount));
        return true;
    }
	
	/**
	 * Transfer money from this account to user B, and
	 * record the transaction
	 * @param B user to transfer money to
	 * @param amountToTransfer the amount to transfer
	 * @return true if the transfer is possible, false otherwise
	 */
	
	public boolean transferMoney(User B,int amountToTransfer) {
        if (amountToTransfer <= this.amount) {
            this.amount -= amountToTransfer;
            Bank.transactions.add(new Transaction(this, "Transfer of " + amountToTransfer + " to " + B.getUsername()));
            B.getAccount().deposit(amountToTransfer, false);
            Bank.transactions.add(new Transaction(B.getAccount(), "Received " + amountToTransfer + " from another user"));
            return true;
        }
        return false;
	}
	
	/**
	 * Returns the balance (amount)
	 * @return amount
	 */

	public int getAmount() {
		Bank.transactions.add(new Transaction(this, "Balance check at " + this.amount));
		return this.amount;
	}
	
	/**
	 * Returns the account id
	 * @return id
	 */

	public int getId() {
		return this.id;
	}

}
