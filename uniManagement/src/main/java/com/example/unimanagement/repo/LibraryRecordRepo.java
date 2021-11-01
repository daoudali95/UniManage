package com.example.unimanagement.repo;

import com.example.unimanagement.dao.LibraryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRecordRepo extends JpaRepository<LibraryRecord, Long> {
}
