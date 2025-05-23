package se.yrgo.spring.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import se.yrgo.spring.domain.Book;

@Repository
public class BookDaoHibernate implements BookDao {
    @Autowired
    private HibernateTemplate template;

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> allBooks() {
        return template.execute(session -> session.createQuery("from Book").list());
    }

 @Override
	public Book findByIsbn(String isbn) throws BookNotFoundException  {
        
		String hql = "FROM Book WHERE isbn = :isbn";
        List<Book> books = (List<Book>) template.execute(session -> session.createQuery(hql, Book.class).setParameter("isbn", isbn).list());

        if (books == null || books.isEmpty()) {
            throw new BookNotFoundException();
        }

        return books.get(0); // Assuming ISBN is unique
    }

    @Override
    public void create(Book newBook) {
        template.save(newBook);
    }

    @Override
    public void delete(Book redundantBook) {
        Book foundBook = template.get(Book.class, redundantBook.getId());
        template.delete(foundBook);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return template.execute(session -> session.createQuery("from Book where author = :author", Book.class)
                .setParameter("author", author).list());
    }

}
