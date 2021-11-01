package com.example.unimanagement.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime issueDate = LocalDateTime.now();

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date returnDate;

    @ManyToMany (fetch = FetchType.EAGER)
    private Collection<Books> books = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
}
