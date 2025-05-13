package se.yrgo.spring.services;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.*;

public class PurchasingServiceImpl implements PurchasingService{
    private BookService books;
    private AccountsService accounts;

    public PurchasingServiceImpl(BookService books, AccountsService accounts){
        this.books = books;
        this.accounts = accounts;
    }

    // This is for Setter Injection
    /* public void setBookService(BookService books){
        this.books = books;
    }

    public void setAccountsService(AccountsService accounts){
        this.accounts = accounts;
    } */

    @Override
    public void buyBook(String isbn) throws BookNotFoundException{
        Book book = books.getBookByIsbn(isbn);
        accounts.raiseInvoice(book);
    }
    
}
