import java.util.Scanner;
import java.util.InputMismatchException;

public class CustomerMenu {
    private final Customer customer;
    private final Scanner scanner;
    private final AccountFactory accountFactory;

    public CustomerMenu(Customer customer, Scanner scanner, AccountFactory accountFactory) {
        this.customer = customer;
        this.scanner = scanner;
        this.accountFactory = accountFactory;
    }

    public void run() {
        System.out.println("Welcome, " + customer.getName());
        boolean running = true;

        while (running) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View profile");
            System.out.println("2. List accounts");
            System.out.println("3. Open new account");
            System.out.println("4. Select an account");
            System.out.println("5. Back");
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

            switch (choice) {
                case 1 -> {
                    System.out.println(customer);
                }

                case 2 -> {
                    if (customer.getAccounts().isEmpty()) {
                        System.out.println("No accounts yet.");
                    } else {
                        for (BankAccount acc : customer.getAccounts()) {
                            System.out.println(acc);
                        }
                    }
                }

                case 3 -> {
                    accountFactory.openAccountFor(customer);
                }

                case 4 -> {
                    if (customer.getAccounts().isEmpty()) {
                        System.out.println("No accounts to select.");
                        break;
                    }
                    System.out.println("Select an account:");
                    for (int i = 0; i < customer.getAccounts().size(); i++) {
                        System.out.println((i + 1) + ". " + customer.getAccounts().get(i));
                    }
                    System.out.print("Choose an account: ");
                    int accountIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (accountIndex < 1 || accountIndex > customer.getAccounts().size()) {
                        System.out.println("Invalid selection.");
                        break;
                    }

                    BankAccount selectedAccount = customer.getAccounts().get(accountIndex - 1);
                    new AccountMenu(selectedAccount, scanner).run();
                }

                case 5 -> {
                    running = false;
                }

                default -> {
                    System.out.println("Invalid option, try again.");
                }
            }
        }
    }
}