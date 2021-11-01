package com.example.unimanagement.service;

import com.example.unimanagement.dao.LibraryRecord;
import com.example.unimanagement.dto.LibraryRecordDto;

import java.util.List;
import java.util.Optional;

public interface libRecord {
    LibraryRecord createRecord(LibraryRecordDto libraryrecordDto);
    Optional<LibraryRecord> findRecord(Long id);
    List<LibraryRecord> getAllRecords();
    void deleteRecord(Long id);
}
