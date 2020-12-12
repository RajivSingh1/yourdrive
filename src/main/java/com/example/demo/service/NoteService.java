package com.example.demo.service;

import com.example.demo.model.Notes;

import java.util.List;

public interface NoteService {

    public Notes addNote(Notes note);
    public void deleteNote(int id);
    public void updateNote(Notes note);
    public List<Notes> allNotes();
    public Notes findNote(int id);
}
