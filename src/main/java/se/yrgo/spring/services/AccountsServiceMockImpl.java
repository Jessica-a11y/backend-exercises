package se.yrgo.spring.services;

import se.yrgo.spring.domain.Book;

public class AccountsServiceMockImpl implements AccountsService{

    @Override
    public void raiseInvoice(Book requiredBook) {
        System.out.println("Raised the invoice for " + requiredBook);
    }
    
}
