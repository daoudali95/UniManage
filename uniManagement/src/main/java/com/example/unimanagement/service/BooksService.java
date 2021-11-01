package com.example.unimanagement.service;

import com.example.unimanagement.dao.Books;
import com.example.unimanagement.repo.BooksRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepo booksRepo;

    public BooksService(BooksRepo booksRepo) {
        this.booksRepo = booksRepo;
    }

    public Books createBook(Books book){return booksRepo.save(book);}


    public Optional<Books> findBook(Long id){return booksRepo.findById(id);}

    public List<Books> getAllBooks(){return booksRepo.findAll();}

    public void deleteBook(Long id){
        booksRepo.deleteById(id);
    }
}
