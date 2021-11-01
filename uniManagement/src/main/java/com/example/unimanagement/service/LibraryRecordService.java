package com.example.unimanagement.service;

import com.example.unimanagement.dao.Books;
import com.example.unimanagement.dao.LibraryRecord;
import com.example.unimanagement.dao.Users;
import com.example.unimanagement.dto.LibraryRecordDto;
import com.example.unimanagement.repo.BooksRepo;
import com.example.unimanagement.repo.LibraryRecordRepo;
import com.example.unimanagement.repo.UsersRepo;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class LibraryRecordService implements libRecord{

    private final LibraryRecordRepo libraryRecordRepo;
    private final BooksRepo booksRepo;
    private final UsersRepo usersRepo;

    public LibraryRecordService(LibraryRecordRepo libraryRecordRepo, BooksRepo booksRepo, UsersRepo usersRepo) {
        this.libraryRecordRepo = libraryRecordRepo;
        this.booksRepo = booksRepo;
        this.usersRepo = usersRepo;
    }


//    libRecord createRecord = (LibraryRecordDto libraryrecordDto) -> {
//         LibraryRecord libraryRecord = new LibraryRecord();
//         Set<Books> bookSet = new HashSet<>();
//     };

    @Override
    public LibraryRecord createRecord(LibraryRecordDto libraryrecordDto){
        LibraryRecord libraryRecord = new LibraryRecord();
        Set<Books> bookSet = new HashSet<>();

        libraryRecord.setIssueDate(libraryrecordDto.getIssueDate());
        libraryRecord.setReturnDate(libraryrecordDto.getReturnDate());

//        for (String book : libraryrecordDto.getBooks()) {
//            Optional<Books> bookRes = booksRepo.findByName(book);
//            if (bookRes.isPresent())
//                bookSet.add(bookRes.get());
//        }

        libraryrecordDto.getBooks().forEach((String book) ->{
            Optional<Books> bookRes = booksRepo.findByName(book);
            if (bookRes.isPresent())
                bookSet.add(bookRes.get());
        });
        libraryRecord.setBooks(bookSet);

        Optional<Users> user = usersRepo.findByName(libraryrecordDto.getUser());
        if (user.isPresent())
            libraryRecord.setUser(user.get());

        return libraryRecordRepo.save(libraryRecord);
    }


    @Override
    public Optional<LibraryRecord> findRecord(Long id){return libraryRecordRepo.findById(id);}

    @Override
    public List<LibraryRecord> getAllRecords(){return libraryRecordRepo.findAll();}

    @Override
    public void deleteRecord(Long id){
        libraryRecordRepo.deleteById(id);
    }

}