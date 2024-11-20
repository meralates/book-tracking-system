package com.example.booktrackingsystem.service;

import com.example.booktrackingsystem.model.Book;
import com.example.booktrackingsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    //ekleme
    public Book addBook(Book book) {
        return bookRepository.save(book);//veritabanına kaydet
    }
    //listele
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    //ıd ile bul
    public Optional<Book>findBookById(Long id) {
        return bookRepository.findById(id);
    }
    //silme
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    //güncelleme
    public Book updateBook(Long id,Book book) {
        Optional<Book> currentbook = bookRepository.findById(id);
        if (currentbook.isPresent()) {
            Book bookToUpdate = currentbook.get();
            bookToUpdate.setName(book.getName());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setPublisher(book.getPublisher());
            bookToUpdate.setPublishingDate(book.getPublishingDate());
            return bookRepository.save(bookToUpdate);//güncellemeyi kaydet
        }
        return null;//yoksa null dönsün
    }
}
