package se.yrgo.spring.data;

public class BookNotFoundException extends Exception{
    public BookNotFoundException() {
        super("Book not found");
    }
    
}
