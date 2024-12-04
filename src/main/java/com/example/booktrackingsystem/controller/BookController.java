package com.example.booktrackingsystem.controller;

import com.example.booktrackingsystem.model.Book;
import com.example.booktrackingsystem.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")//localhost:8080/books
public class BookController {

    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();//veritabanından kitapları alır
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            return ResponseEntity.ok(bookOptional.get());
        }else {
            return ResponseEntity.notFound().build();//bulunmazsa 404
        }
    }
    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        Optional<Book> bookOptional = bookRepository.findById(id);//güncellenecek kitabı bulur
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setName(updatedBook.getName());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublisher(updatedBook.getPublisher());
            book.setPublishingDate(updatedBook.getPublishingDate());
            Book savedBook = bookRepository.save(book);
            return ResponseEntity.ok(savedBook);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
