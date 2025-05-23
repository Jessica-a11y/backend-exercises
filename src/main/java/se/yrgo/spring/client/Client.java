package se.yrgo.spring.client;



import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.data.CustomerCreditExceededException;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;
import se.yrgo.spring.services.PurchasingService;

public class Client {
	public static void main(String[] args) {
		
	ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
    
		try {	
			PurchasingService purchasing = container.getBean(PurchasingService.class);
			BookService bookService = container.getBean(BookService.class);
			
			List<Book> results = bookService.getAllBooksByAuthor("JavaAuthor");
			for(Book b : results) {
				System.out.println(b);
			}
		}finally {
		  container.close();
		}
	}

}
