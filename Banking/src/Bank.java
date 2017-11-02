import static dit948.SimpleIO.*;

import java.util.ArrayList;

/**
 * This class implements the data structures of the bank. An array of users and
 * array of transactions are used to keep track of users and transactions
 * Note: you are allowed to use ArrayLists, but you don't need to
 */

public class Bank {

    /**
     * No strict limitation to a certain number of users and/or
     * transactions due to the use of ArrayLists.
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static int id;
    private ArrayList<User> users;
    public static ArrayList<Transaction> transactions;

	public Bank() {
        this.id = 0;
        this.users = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

	/**
	 * Checks whether a user is present in the User array, given the username
	 * and the password
	 * 
	 * @param u  username
	 * @param p  password
	 * @return true or false, accordingly
     *
     * Additionally, this method will set this.id to the latest User.Account.Id
     * for any username/password combination that has been found within the current
     * Bank object - promoting it to some variation of a (backend) login function.
	 */
	
	public boolean findUserByUsernamePwd(String u, String p) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(u) && this.users.get(i).getPassword().equals(p)) {
                this.id = i;
				return true;
            }
        }
        return false;
	}

	/**
	 * Returns a String representation of this Bank object
     *
     *  @return a list of users registered with this Bank
	 */

	public String toString() {
        if (this.users.size() == 0) return "This is a new bank without any users (so far).";
		String list = "";
        for (int i = 0; i < this.users.size(); i++) {
            list += this.users.get(i).getUsername();
            if (i < this.users.size() -1) list += "\n";
        }
        return list;
	}

	/**
	 * Returns the User associated with a username and a password
	 * 
	 * @param u  username
	 * @param p  password
	 * @return a User object or null
	 */
	
	public User getUserFromUsrPwd(String u, String p) {
		if (findUserByUsernamePwd(u, p)) return this.users.get(id);
        else return null;
	}

	/**
	 * Returns the User object associated with a given username
	 * 
	 * @param u username
	 * @return the User with username u, if present in the array of 
	 * Users; null otherwise
	 */
	
	public User getUserByUsr(String u) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(u)) return this.users.get(i);
        }
        return null;
    }

	/**
	 * Prompt the user to enter a username and a password from the console. Then
	 * use the chosen username and password to create a new user and add it to
	 * the array of users. Hint: Each user must be assigned a new id.
	 */

	public void addUser() {
        String name, password;
        boolean valid;

        do {
            println("Please enter a username:");
            name = readString();
            valid = name.length() > 0 && getUserByUsr(name) == null;
            if (IOError || !valid) println("\nYour username has to be unique and should not be empty.\n");
        }
        while (IOError || !valid);
        do {
            println("Please enter a password:");
            password = readString();
            valid = password.length() > 0;
            if (IOError || !valid) println("\nYour password should not be empty.\n");
        }
        while (IOError || !valid);

        this.users.add(new User(name, password, this.users.size()));
        println("New user created.");
	}
}
