package se.yrgo.spring.services;

import se.yrgo.spring.data.CustomerCreditExceededException;
import se.yrgo.spring.domain.Book;

public class AccountsServiceMockImpl implements AccountsService{

    @Override
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException {
       /*  System.out.println("Raised the invoice for " + requiredBook); */
       throw new CustomerCreditExceededException();
    }
    
}
