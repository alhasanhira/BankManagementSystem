import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int customerCounter = 100;
    private final String customerId;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final List<BankAccount> accounts = new ArrayList<>();

    public Customer(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        customerCounter++;
        this.customerId = "CUST" + customerCounter;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public List<BankAccount> getAccounts() { return accounts; }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        return """
        Customer ID: %s
        Name: %s
        Email: %s
        Phone: %s
        Address: %s
        """.formatted(customerId, name, email, phone, address);
    }
}