package dto;

import entities.BankCustomer;

/**
 *
 * @author Nikolaj Larsen
 */
public class CustomerDTO {

    public CustomerDTO() {
    }
    
    int customerID;
    String fullName; 
    String accountNumber;
    double balance;

public CustomerDTO(BankCustomer bc) {
        this.customerID = bc.getId();
        this.fullName = bc.getFirstName() + " " + bc.getLastName();
        this.accountNumber = bc.getAccountNumber();
        this.balance = bc.getBalance();
    }



}
