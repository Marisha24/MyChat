package com.example.test1.controllers;
import com.example.test1.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private MassageService massageService;

    private DataSource base;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView greetingPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting");
        return modelAndView;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public ModelAndView mainPage1( @RequestParam("user_id") Long userId, @RequestParam("firstBound")Optional<String> firstBound, @RequestParam("secondBound") Optional<String> secondBound) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chat");
        if (firstBound.isPresent() && secondBound.isPresent() && !firstBound.get().isEmpty() && !secondBound.get().isEmpty()){
            LocalDateTime firstDateTime = LocalDateTime.parse(firstBound.get(),  DateTimeFormatter.ISO_LOCAL_DATE_TIME ) ;
            LocalDateTime secondDateTime = LocalDateTime.parse(secondBound.get(),  DateTimeFormatter.ISO_LOCAL_DATE_TIME ) ;
            modelAndView.addObject("messages", massageService.getAllMassagesBetween(firstDateTime, secondDateTime));
        }
        else
            modelAndView.addObject("messages", massageService.getAllMassages());

        modelAndView.addObject("user_id", userId);
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting");
        return modelAndView;
    }
}
