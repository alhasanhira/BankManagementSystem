import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        CustomerOnboarding onboarding = new CustomerOnboarding(scanner, bank);
        AccountFactory accountFactory = new AccountFactory(scanner, bank);

        Customer currentCustomer = null;
        boolean running = true;

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register customer");
            System.out.println("2. Customer menu");
            System.out.println("3. Find customer (by account number or ID)");
            System.out.println("4. Exit");
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
                    currentCustomer = onboarding.registerCustomer();

                    if (currentCustomer != null) {
                        accountFactory.openAccountFor(currentCustomer);
                    }
                }

                case 2 -> {
                    if (currentCustomer == null) {
                        System.out.println("No customer selected. Register one first.");
                    } else {
                        new CustomerMenu(currentCustomer, scanner, accountFactory).run();
                    }
                }

                case 3 -> {
                    System.out.print("Enter account number or customer ID: ");
                    String identifier = scanner.nextLine().toUpperCase();
                    Customer foundCustomer = bank.findCustomerByAccountOrId(identifier);
                    if (foundCustomer == null) {
                        System.out.println("No matching account or customer found.");
                    } else {
                        currentCustomer = foundCustomer;
                        new CustomerMenu(currentCustomer, scanner, accountFactory).run();
                    }
                }

                case 4 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }

                default -> {
                    System.out.println("Invalid option, try again.");
                }
            }
        }

        scanner.close();
    }
}