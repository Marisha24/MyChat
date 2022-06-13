package com.example.test1.controllers;

import com.example.test1.entity.Role;
import com.example.test1.entity.User;
import com.example.test1.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.Inet4Address;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationControllers {
   @Autowired
   private UserRepository userRepository;
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Map<String, Object> model) {
        return "registration";
    }
    @SneakyThrows
    @PostMapping("/registration")
    public ModelAndView addUser(@RequestParam("regusername") String username, @RequestParam("regpassword") String password, @RequestParam("reregpassword") String rePassword) {
        ModelAndView modelAndView = new ModelAndView();
        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || !rePassword.equals(password)){
            modelAndView.setViewName("newLoginPage");
            return modelAndView;
        }

        User userFromDb = userRepository.findByUsername(username);

        if (userFromDb != null) {
            modelAndView.setViewName("newLoginPage");
            modelAndView.addObject("message", "User exists!");
            return modelAndView;
        }
        modelAndView.setViewName("chat");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setDataLastVisit(LocalDateTime.now());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.User));
        user.setIP(Inet4Address.getLocalHost().getHostAddress());
        userRepository.save(user);
        userFromDb = userRepository.findByUsername(user.getUsername());
        modelAndView.addObject("user_id", userFromDb.getId());

        return modelAndView;
    }


//
//    @SneakyThrows
//    @PostMapping("/registration")
//    public String addUser(User user, Map<String, Object> model) {
//        user.setDataLastVisit(LocalDateTime.now());
//        User userFromDb = userRepository.findByUsername(user.getUsername());
//
//        if (userFromDb != null) {
//            model.put("message", "User exists!");
//            return "registration";
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.User));
//        user.setIP(Inet4Address.getLocalHost().getHostAddress());
//        userRepository.save(user);
//        userFromDb = userRepository.findByUsername(user.getUsername());
//
//        model.put("user_id", user.getId());
//        return "redirect:/main";
//    }

}
