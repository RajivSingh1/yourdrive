package com.example.demo.controller;

import com.example.demo.model.Notes;
import com.example.demo.model.User;
import com.example.demo.service.NoteService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Notes> getNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping
    public String addNote(Authentication authentication, Notes note, RedirectAttributes redirectAttributes){
        String username= authentication.getName();
        User user = userService.getUser(username);
        note.setNoteId(null);
        note.setUserId(user.getUserId());
        noteService.addNote(note);
        redirectAttributes.addFlashAttribute("successMessage","Note added");
        return "redirect:/home";
    }
}
