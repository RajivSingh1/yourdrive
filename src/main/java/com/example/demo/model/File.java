package com.example.demo.model;

public class File {
    private Integer id;
    private String fileName;
    private String owner;

    public File(Integer id, String fileName, String owner) {
        this.id = id;
        this.fileName = fileName;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
