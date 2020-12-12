package com.example.demo.controller;

import com.example.demo.service.FileService;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String homeView(Model model){
        model.addAttribute("fileslist",fileService.getAllFiles());
        model.addAttribute("noteslist",noteService.getAllNotes());

        return "home";
    }
}
