import java.util.Scanner;

public class ViewAccountInformationAction implements AccountAction {
    @Override
    public void execute(BankAccount account, Scanner scanner) {
        System.out.println("1. View balance & details");
        System.out.println("2. View transaction history");
        System.out.println("3. View both");
        System.out.println("4. View totals (deposits/withdrawals)");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1 || choice == 3) {
            System.out.println(account);
        }
        if (choice == 2 || choice == 3) {
            for (Transaction t : account.getTransactionHistory()) {
                System.out.println(t);
            }
        }
        if (choice == 4) {
            System.out.println("Total deposits: " + account.getTotalDeposits());
            System.out.println("Total withdrawals: " + account.getTotalWithdrawals());
        }
    }
}