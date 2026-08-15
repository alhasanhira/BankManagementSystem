import java.util.Scanner;

public class DepositAction implements AccountAction {
    @Override
    public void execute(BankAccount account, Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Add a note? (y/n): ");
        String addNote = scanner.nextLine();

        if (addNote.equalsIgnoreCase("y")) {
            System.out.print("Enter a note: ");
            String note = scanner.nextLine();
            account.deposit(amount, note);
        } else {
            account.deposit(amount);
        }
    }
}