package com.example.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private String password;
    private Long hit;
    private LocalDateTime created_time;
}
