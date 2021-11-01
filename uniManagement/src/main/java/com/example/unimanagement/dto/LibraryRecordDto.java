package com.example.unimanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryRecordDto {
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime issueDate = LocalDateTime.now();

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date returnDate;

//    String bookName;
    Set<String> books;

    String user;
}
