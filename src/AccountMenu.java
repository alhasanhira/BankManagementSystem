import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class AccountMenu {
    private final BankAccount account;
    private final Scanner scanner;
    private final Map<Integer, AccountAction> actions = new HashMap<>();

    public AccountMenu(BankAccount account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
        actions.put(1, new DepositAction());
        actions.put(2, new WithdrawAction());
        actions.put(3, new ViewAccountInformationAction());
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Account Menu (" + account.getAccountType() + ") ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Account info");
            System.out.println("4. Back");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option, try again.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            if (choice == 4) {
                running = false;
            } else if (actions.containsKey(choice)) {
                actions.get(choice).execute(account, scanner);
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }
}