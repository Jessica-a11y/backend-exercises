package se.yrgo.spring.services;

import javax.security.auth.login.CredentialNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.data.CustomerCreditExceededException;
import se.yrgo.spring.domain.*;

@Transactional
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

    @Transactional(rollbackFor = {CustomerCreditExceededException.class, BookNotFoundException.class})
    @Override
    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException{
        Book book = books.getBookByIsbn(isbn);
        books.deleteBookFromStock(book);
        accounts.raiseInvoice(book); 
    }
    
}
