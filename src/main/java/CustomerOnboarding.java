import java.util.Scanner;

public class CustomerOnboarding {
    private final Scanner scanner;
    private final Bank bank;

    public CustomerOnboarding(Scanner scanner, Bank bank) {
        this.scanner = scanner;
        this.bank = bank;
    }

    public Customer registerCustomer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, email, phone, address);
        bank.registerCustomer(customer);
        System.out.println("main.java.Customer registered! Your customer ID is: " + customer.getCustomerId());
        return customer;
    }
}