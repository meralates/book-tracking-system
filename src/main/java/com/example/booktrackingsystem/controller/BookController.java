package com.example.booktrackingsystem.controller;

import com.example.booktrackingsystem.model.Book;
import com.example.booktrackingsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Tüm kitapları getir
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // ID'ye göre kitap bul
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findBookById(id);
        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Kitap ekle
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // Kitap güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> bookOptional = bookService.findBookById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(updatedBook.getName());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublisher(updatedBook.getPublisher());
            book.setPublishingDate(updatedBook.getPublishingDate());
            Book savedBook = bookService.addBook(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Kitap sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findBookById(id).isPresent()) {
            bookService.deleteBookById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Yazar ID'ye göre kitapları listele
    @GetMapping("/author/{authorId}")
    public List<Book> getBookByAuthorId(@PathVariable Long authorId) {
        return bookService.getBookByAuthorId(authorId);
    }
}
