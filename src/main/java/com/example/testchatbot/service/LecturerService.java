package com.example.testchatbot.service;

import com.example.testchatbot.domen.Lecturer;
import com.example.testchatbot.repository.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LecturerService {
    private final LecturerRepository lecturerRepository;

    public List<String> globalSearch(String regex){
        return lecturerRepository.getAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(regex, regex)
                .stream()
                .map(Lecturer::toString)
                .toList();
    }
}
