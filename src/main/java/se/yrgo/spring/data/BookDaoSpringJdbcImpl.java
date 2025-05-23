package se.yrgo.spring.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import se.yrgo.spring.domain.Book;


public class BookDaoSpringJdbcImpl implements BookDao{
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_BOOK_SQL = "INSERT INTO BOOK (ISBN,TITLE, AUTHOR, PRICE) values (?, ?, ?, ?)";
    private static final String ALL_BOOKS_SQL = "SELECT * FROM BOOK";
    private static final String BOOKS_BY_AUTHOR_SQL = "SELECT * FROM BOOK WHERE AUTHOR = ?";
    private static final String FIND_BY_ISBN_SQL = "SELECT * FROM BOOK WHERE ISBN = ?";
    private static final String REMOVE_BOOK_SQL = "DELETE FROM BOOK WHERE ISBN = ?";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE BOOK (ISBN VARCHAR(20), TITLE VARCHAR(60), AUTHOR VARCHAR(100), PRICE DOUBLE)";

    @Autowired
    public BookDaoSpringJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void createTables() {
		try {
			jdbcTemplate.update(CREATE_TABLE_SQL);
		}catch(Exception e) {
			System.err.println("Table already exists");
		}
	}

    @Override
    public List<Book> allBooks() {
        return jdbcTemplate.query(ALL_BOOKS_SQL, new BookMapper());
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException{
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ISBN_SQL, new BookMapper(), isbn);
        } catch(EmptyResultDataAccessException e) {
            throw new BookNotFoundException(); 
        }
       
    }

    @Override
    public void create(Book newBook) {
        jdbcTemplate.update(INSERT_BOOK_SQL, newBook.getIsbn(), 
                                             newBook.getTitle(), 
                                             newBook.getAuthor(), 
                                             newBook.getPrice());
    }

    @Override
    public void delete(Book redundantBook) {
        jdbcTemplate.update(REMOVE_BOOK_SQL, redundantBook.getIsbn());
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return jdbcTemplate.query(BOOKS_BY_AUTHOR_SQL, new BookMapper(), author);
    }
   
}

class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNumber) throws SQLException {
    String isbn = rs.getString("ISBN");
    String title = rs.getString("title");
    String author = rs.getString("author");
    double price = rs.getDouble("price");
    Book book = new Book(isbn, title, author, price);
    return book;
    }
}


