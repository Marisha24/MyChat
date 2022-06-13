package com.example.test1.controllers;

import com.example.test1.FileService;
import com.example.test1.MassageService;
import com.example.test1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller

public class MessageController {
    @Value("${web-path}")
    private String uploadDir;
    @Autowired
    FileService fileService;
    @Autowired
    private MassageService massageService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/send", method = RequestMethod.POST)

    public ModelAndView sendMessage(
            @RequestParam("file") Optional<MultipartFile> file,
            @RequestParam("user_id") Long userId,
            @RequestParam("text") Optional<String> text) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chat");
        if(!file.isEmpty() && !file.get().isEmpty() && text.isPresent() && !text.get().isEmpty())
        {
            massageService.addfile(userRepository.getById(userId),uploadDir + StringUtils.cleanPath(file.get().getOriginalFilename()), text.get());
        }
        else if (!file.isEmpty() && !file.get().isEmpty() ) {
            fileService.uploadFile(file.get());
            massageService.addfile( userRepository.getById(userId),uploadDir  + StringUtils.cleanPath(file.get().getOriginalFilename()));
        } else if (text.isPresent() && !text.get().isEmpty())
            massageService.work(userRepository.getById(userId), text.get());

        modelAndView.addObject("messages", massageService.getAllMassages());
        modelAndView.addObject("user_id", userId);
        return modelAndView;
    }

}
