package se.yrgo.spring.services;

import org.springframework.stereotype.Component;

import se.yrgo.spring.data.CustomerCreditExceededException;
import se.yrgo.spring.domain.Book;

@Component("accountsService")
public class AccountsServiceMockImpl implements AccountsService{

    @Override
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException {
       System.out.println("Raised the invoice for " + requiredBook); 
       
    }
    
}
