package com.example.unimanagement.resource;

import com.example.unimanagement.dao.Books;
import com.example.unimanagement.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uni")
public class BooksResource {

    private final BooksService booksService;

    public BooksResource(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping("book")
    public ResponseEntity<Books> creatBook(@RequestBody Books books){
        Books bookRes = booksService.createBook(books);
        return ResponseEntity.ok().body(bookRes);
    }

    @GetMapping("Allbooks")
    public ResponseEntity<List<Books>>AllBooks(){
        List<Books> books = booksService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/book/{id}")
    public Optional<Books> retrieveBook(@PathVariable Long id){
        return booksService.findBook(id);
    }

    @DeleteMapping("/delBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        booksService.deleteBook(id);
        return ResponseEntity.ok().body("User deleted id : "+ id);
    }
}