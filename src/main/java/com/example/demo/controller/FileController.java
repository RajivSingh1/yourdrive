package com.example.demo.controller;


import com.example.demo.exception.FileStorageException;
import com.example.demo.model.User;
import com.example.demo.service.FileService;
import com.example.demo.service.UserService;
import com.example.demo.service.implimentation.FileServiceImp;
import com.example.demo.utils.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    private Logger logger =  LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;


    @PostMapping
    public String addFile(@RequestParam(value = AppConstant.FILE_PARAM)MultipartFile file,
                                     Model model) throws IOException {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userService.getUser(currentPrincipalName);
       ;
            if(!file.isEmpty() &&  user != null){
//            if(!fileService.findFile(fileName).getFileName().isEmpty()){
//                throw new FileStorageException(AppConstant.FILE_EXIST);
//            }

                String savedFile=fileService.storeFile(file,user);
                logger.info("Saved filename: "+savedFile);
                model.addAttribute("successMessage","The file"+savedFile+" saved successful.");
                return "redirect:/home";



        }
        model.addAttribute("errorMessage","The were an error during saving the file. Please try again");
        return "redirect:/home";

    }
}
