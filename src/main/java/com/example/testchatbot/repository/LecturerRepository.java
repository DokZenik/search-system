package com.example.testchatbot.repository;

import com.example.testchatbot.domen.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    @Query("select l from Lecturer l where l.firstName like %?1% or l.lastName like %?1%")
    List<Lecturer> getAllByRegex(String regex);
}
