package com.areznikov.examples.hibernate.onetomany;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Runner {

	public static void main(String[] args) {
		HibernateConfiguration config = new HibernateConfiguration();
		SessionFactory sessionFactory = config.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Author author = new Author();
		author.setName("Steven");
		author.setSurname("King");
		session.save(author);

		Book book = new Book();
		book.setTitle("It");
		book.setAuthor(author);
		session.save(book);

		Book anotherBook = new Book();
		anotherBook.setTitle("Mist");
		anotherBook.setAuthor(author);
		session.save(anotherBook);

		author.getBooks().add(book);
		author.getBooks().add(anotherBook);
		session.getTransaction().commit();

		List<Book> books = session.createCriteria(Book.class).list();
		for (Book singleBook : books) {
			System.out.println(singleBook);
		}

		Author selectedAuthor = (Author) session.get(Author.class, 1L);
		System.out.println(selectedAuthor.getName() + " "
				+ selectedAuthor.getSurname() + " Кол-во книг: "
				+ selectedAuthor.getBooks().size());
	}

}
