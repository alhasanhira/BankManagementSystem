import java.util.Scanner;
import java.time.LocalDate;

public class AccountFactory {
    private final Scanner scanner;
    private final Bank bank;

    public AccountFactory(Scanner scanner, Bank bank) {
        this.scanner = scanner;
        this.bank = bank;
    }

    public BankAccount openAccountFor(Customer customer) {
        BankAccount account = createAccount(customer);
        if (account != null) {
            account.setOwner(customer);
            customer.addAccount(account);
            bank.registerAccount(account);
            System.out.println("Account created: " + account);
        }
        return account;
    }

    private BankAccount createAccount(Customer customer) {
        System.out.println("Choose account type:");
        System.out.println("1. Savings");
        System.out.println("2. Checking");
        System.out.println("3. Fixed Deposit");
        System.out.println("0. Skip for now");
        System.out.print("Choose an option: ");
        int accountTypeChoice = scanner.nextInt();
        scanner.nextLine();

        if (accountTypeChoice == 0) {
            System.out.println("Skipping account creation.");
            return null;
        }

        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        return switch (accountTypeChoice) {
            case 1 -> createSavingsAccount(customer, initialBalance);
            case 2 -> createCheckingAccount(customer, initialBalance);
            case 3 -> createFixedDepositAccount(customer, initialBalance);
            default -> {
                System.out.println("Invalid account type.");
                yield null;
            }
        };
    }

    private BankAccount createSavingsAccount(Customer customer, double initialBalance) {
        System.out.print("Enter interest rate (e.g. 0.015 for 1.5%): ");
        double interestRate = scanner.nextDouble();
        scanner.nextLine();
        return new SavingsAccount(customer.getName(), initialBalance, interestRate);
    }

    private BankAccount createCheckingAccount(Customer customer, double initialBalance) {
        System.out.print("Enter overdraft limit: ");
        double overdraftLimit = scanner.nextDouble();
        scanner.nextLine();
        return new CheckingAccount(customer.getName(), initialBalance, overdraftLimit);
    }

    private BankAccount createFixedDepositAccount(Customer customer, double initialBalance) {
        System.out.print("Enter interest rate: ");
        double interestRate = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter maturity date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate maturityDate = LocalDate.parse(dateInput);
        return new FixedDepositAccount(customer.getName(), initialBalance, interestRate, maturityDate);
    }
}