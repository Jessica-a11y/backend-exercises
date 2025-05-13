package se.yrgo.spring.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.spring.services.BookService;

public class Client {
	public static void main(String[] args){
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		BookService service = container.getBean(BookService.class);
		System.out.println(service.getEntireCatalogue());

		container.close();

	}
}
