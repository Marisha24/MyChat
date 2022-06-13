package com.example.test1.controllers;

import com.example.test1.entity.User;
import com.example.test1.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.net.Inet4Address;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class loginController {
    @Autowired
    private UserRepository userRepository;
    @SneakyThrows
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPage(@RequestParam Optional<String> username, @RequestParam Optional<String> password) {
        ModelAndView modelAndView = new ModelAndView();
        if (username.isPresent() && password.isPresent()){
            User userFromDb = userRepository.findByUsernameAndPassword(username.get(), password.get());
            if (userFromDb != null) {
                userFromDb.setDataLastVisit(LocalDateTime.now());
                userFromDb.setIP(Inet4Address.getLocalHost().getHostAddress());
                userRepository.save(userFromDb);
                modelAndView.setViewName("chat");
                modelAndView.addObject("infoLog","Проверка-проверка");
                modelAndView.addObject("user_id",userFromDb.getId());
                return modelAndView;
            }
        }
        modelAndView.setViewName("newLoginPage");
        return modelAndView;
    }


}
