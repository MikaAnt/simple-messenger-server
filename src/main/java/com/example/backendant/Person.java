package com.example.backendant;

import java.time.LocalDateTime;

public record Person(
        Long id,
        String personName,
        String personPassword,
        PersonStatus personStatus,
        LocalDateTime entryTime
){
}
