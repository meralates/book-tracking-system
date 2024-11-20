package com.example.booktrackingsystem.repository;

import com.example.booktrackingsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {//veritabanı işlemleri için miras aldık.
}
