public class Bank {
    private final Repository<Customer, String> customerRepository = new Repository<>();
    private final Repository<BankAccount, String> accountRepository = new Repository<>();

    public void registerCustomer(Customer customer) {
        customerRepository.save(customer.getCustomerId(), customer);
    }

    public Customer findCustomer(String customerId) {
        return customerRepository.findById(customerId);
    }

    public boolean customerExists(String customerId) {
        return customerRepository.existsById(customerId);
    }

    public void registerAccount(BankAccount account) {
        accountRepository.save(account.getAccountNumber(), account);
    }

    public BankAccount findAccountByNumber(String accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    public Customer findCustomerByAccountOrId(String identifier) {
        Customer customer = findCustomer(identifier);

        if (customer != null) {
            return customer;
        }

        BankAccount account = findAccountByNumber(identifier);

        if (account != null) {
            return account.getOwner();
        }

        return null;
    }
}