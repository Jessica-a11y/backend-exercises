package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

public class BookServiceTimingProxy implements BookService {
    private BookService originalBookService;

    public void setOriginalBookService(BookService original) {
        this.originalBookService = original;
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> getAllRecommendedBooks(String userId) {
        return null;
    }

    @Override
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        long start = System.currentTimeMillis();
        try {
            Book book = originalBookService.getBookByIsbn(isbn);
            return book;
        } finally {
            long end = System.currentTimeMillis();
            long timeTaken = end - start;
            System.out.println("Took this long: " + timeTaken);
        }
    }

    @Override
    public List<Book> getEntireCatalogue() {
        long start = System.currentTimeMillis();

        List<Book> books = originalBookService.getEntireCatalogue();

        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        System.out.println("Took this long: " + timeTaken);
        return books;
    }

    @Override
    public void registerNewBook(Book newBook) {
        long start = System.currentTimeMillis();

        originalBookService.registerNewBook(newBook);

        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        System.out.println("Took this long: " + timeTaken);
    }

    @Override
    public void deleteBookFromStock(Book removeBook) {
       long start = System.currentTimeMillis();

        originalBookService.registerNewBook(removeBook);

        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        System.out.println("Took this long: " + timeTaken);
    }
}
