package com.example.booktrackingsystem.service;

import com.example.booktrackingsystem.model.Book;
import com.example.booktrackingsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Yazar ID'ye göre kitapları getir
    public List<Book> getBookByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId); // BookRepository üzerinden çağır
    }

    // Kitap ekle
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Tüm kitapları listele
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ID'ye göre kitap bul
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Kitap silme
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    // Kitap güncelleme
    public Book updateBook(Long id, Book book) {
        Optional<Book> currentbook = bookRepository.findById(id);
        if (currentbook.isPresent()) {
            Book bookToUpdate = currentbook.get();
            bookToUpdate.setName(book.getName());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setPublisher(book.getPublisher());
            bookToUpdate.setPublishingDate(book.getPublishingDate());
            return bookRepository.save(bookToUpdate);
        }
        return null;
    }
}
