package com.example.booktrackingsystem.service;

import com.example.booktrackingsystem.model.Author;
import com.example.booktrackingsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
