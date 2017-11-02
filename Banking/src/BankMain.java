import static dit948.SimpleIO.*;

/**
 * Class representing a online banking application for the second assignment of
 * DIT948, 2016 edition. This is the main class for the application, interacting
 * with the user, creating accounts and performing various transactions More
 * information about the interface can be found in Testcases.txt
 */

public class BankMain {

    /**
     * Static class wide variables for int and/or String input, used
     * through handleIntInput() and handleStringInput(). Only "safe"
     * to read after the corresponding handle... method returned TRUE.
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static int intInput;
    private static String stringInput;

    /**
     * main() method for our online bank.
     *
     * As described in the Assignment text, the interface contains a
     * MainMenu, and a SubMenu. The program will return to the MainMenu
     * whenever the user exits the SubMenu, without terminating.
     *
     * @param args not used
     */

	public static void main(String[] args) {

		println("Welcome to the DIT948 online bank!");

		Bank bank = new Bank();
        User user;

        do {
            println("\nMain menu");
            println("1: Create user account");
            println("2: Login as user");
            println("3: List users");
            println("4: Exit\n");

            print("Enter a choice: ");
            if (handleIntInput(1, 4)) {

                switch (intInput) {
                    case 1: bank.addUser(); break;
                    case 2: {

                        if ((user = handleUserLogin(bank)) != null) {
                            println("Successful user login.");
                            boolean exitToMain = false;
                            do {
                                println("\nSubMenu");
                                println("1: Check balance");
                                println("2: Deposit");
                                println("3: Withdraw");
                                println("4: List of transactions");
                                println("5: Transfer to other account");
                                println("6: Close user session");

                                print("Enter a choice: ");
                                if (handleIntInput(1, 6)) {

                                    switch (intInput) {
                                        case 1: printBalance(user); break;
                                        case 2: handleDeposit(user); break;
                                        case 3: handleWithdraw(user); break;
                                        case 4: printListOfTransactions(bank, user); break;
                                        case 5: handleTransfer(bank, user); break;
                                        case 6: exitToMain = true;
                                    }
                                }
                                else println("Invalid input.");

                            } while (!exitToMain);
                        }
                        else println("Invalid login credentials.");
                        break;
                    }
                    case 3: println(bank.toString()); break;
                    case 4: println("Goodbye!"); System.exit(0);
                }
            }
            else println("Invalid input.");

        } while (true);
	}

    /**
     * Reads an integer input from the console and ensures that it's within
     * the limits specified by min and max.
     *
     * Use max as max == min for inputs without an upper limit.
     *
     * @param min
     * @param max
     * @return true or false, whether the given input was valid or not
     */

	private static boolean handleIntInput(int min, int max) {
        intInput = readInt();
        if (!IOError && intInput >= min && (min == max || intInput <= max)) return true;
        else return false;
    }

    /**
     * Reads a String input from the console and ensures that it's at least
     * minLen characters long.
     *
     * @param minLen
     * @return true or false, whether the given input was valid or not
     */

    private static boolean handleStringInput(int minLen) {
        stringInput = readString();
        if (!IOError && stringInput.length() >= minLen) return true;
        else return false;
    }

    /**
     * Prompts the current user to enter his/her login credentials in order
     * to proceed.
     *
     * @param bank the Bank to search for the given user credentials
     * @return a User object if the login was successful, null if not
     */

    private static User handleUserLogin(Bank bank) {
        println("Enter username:");
        if (handleStringInput(1)) {
            String u = stringInput;
            println("Enter password:");
            if (handleStringInput(1)) {
                String p = stringInput;
                return bank.getUserFromUsrPwd(u, p);
            }
        }
        return null;
    }

    /**
     * Gets the current account balance for a given User object and prints
     * it to the console.
     *
     * @param user
     */

    private static void printBalance(User user) {
        println(user.getAccount().getAmount());
    }

    /**
     * Prompts the current user to enter an amount to deposit and handles
     * his/her request if the given input was valid.
     *
     * @param user
     */

    private static void handleDeposit(User user) {
        println("Enter the amount to deposit:");
        if (handleIntInput(1,1)) {
            user.getAccount().deposit(intInput);
        }
        else println("Invalid amount.");
    }

    /**
     * Prompts the current user to enter an amount to withdraw and handles
     * his/her request if the given input was valid.
     *
     * @param user
     */

    private static void handleWithdraw(User user) {
        println("Enter the amount to withdraw:");
        if (handleIntInput(1,1)) {
            if (!user.getAccount().withdraw(intInput)) println("Not enough money!");
        }
        else println("Invalid amount.");
    }

    /**
     * Accesses the list of transactions of a given Bank and searches it for
     * entries matching the current User's Account.id - matches will be print
     * to the console.
     *
     * @param bank the Bank to search for matching transactions
     * @param user the User to watch out for
     */

    private static void printListOfTransactions(Bank bank, User user) {
        for (int i = 0; i < bank.transactions.size(); i++) {
            if (bank.transactions.get(i).getAccount().getId() == user.getId()) {
                println(bank.transactions.get(i).toString());
            }
        }
    }

    /**
     * Prompts the current user to enter the name of a second user (as target
     * for the upcoming transfer) and further to enter an amount to transfer
     * to this user, if the target has been found in the list of users for
     * the given Bank.
     *
     * Terminates with readable feedback (printed to the console) if something
     * in between goes wrong.
     *
     * @param bank the Bank to use for this transfer
     * @param user the targeted User for this transfer
     */

    private static void handleTransfer(Bank bank, User user) {
        User B;
        println("Enter the username of the recipient:");
        if (handleStringInput(1) && (B = bank.getUserByUsr(stringInput)) != null) {
            println("Enter the amount to transfer:");
            if (handleIntInput(1, 1)) {
                if(!user.getAccount().transferMoney(B, intInput)) {
                    println("Not enough money!");
                }
            }
            else println("Invalid amount.");
        }
        else println("Sorry, this user is unknown to our bank.");
    }
}
