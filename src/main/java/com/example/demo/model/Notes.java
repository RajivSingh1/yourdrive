package com.example.demo.model;

import lombok.Data;

@Data
public class Notes {

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

}
