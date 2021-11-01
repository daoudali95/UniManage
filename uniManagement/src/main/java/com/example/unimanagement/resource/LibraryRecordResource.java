package com.example.unimanagement.resource;

import com.example.unimanagement.dao.LibraryRecord;
import com.example.unimanagement.dto.LibraryRecordDto;
import com.example.unimanagement.service.LibraryRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uni")
public class LibraryRecordResource {
    private final LibraryRecordService libraryRecordService;

    public LibraryRecordResource(LibraryRecordService libraryRecordService) {
        this.libraryRecordService = libraryRecordService;
    }

    @PostMapping("recordCreate")
    public ResponseEntity<LibraryRecord> recordCreate(@RequestBody LibraryRecordDto libraryRecordDto){
        LibraryRecord library_RecordRes = libraryRecordService.createRecord(libraryRecordDto);
        return ResponseEntity.ok().body(library_RecordRes);
    }

    @GetMapping("/record/{id}")
    public Optional<LibraryRecord> retrieveRecord(@PathVariable Long id){
        return libraryRecordService.findRecord(id);
    }

    @GetMapping("AllRecords")
    public ResponseEntity<List<LibraryRecord>>AllRecords(){
        List<LibraryRecord> library_Records = libraryRecordService.getAllRecords();
        return ResponseEntity.ok().body(library_Records);
    }

    @DeleteMapping("/delRecord/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id){
        libraryRecordService.deleteRecord(id);
        return ResponseEntity.ok().body("Record deleted id : "+ id);
    }
}