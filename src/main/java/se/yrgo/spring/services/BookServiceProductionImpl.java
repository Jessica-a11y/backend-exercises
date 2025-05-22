package se.yrgo.spring.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.BookDao;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

@Component("bookService")
@Transactional
public class BookServiceProductionImpl implements BookService{
    private BookDao dao;

    public BookServiceProductionImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return dao.findBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllRecommendedBooks(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRecommendedBooks'");
    }

    @Override
    public Book getBookByIsbn(String isbn) throws BookNotFoundException{
        return dao.findByIsbn(isbn);
    }

    @Override
    public List<Book> getEntireCatalogue() {
        return dao.allBooks(); 
    }

    @Override
    public void registerNewBook(Book newBook) {
        dao.create(newBook);
    }

    @Override
    public void deleteBookFromStock(Book removeBook) {
        dao.delete(removeBook);
    }
    
}
