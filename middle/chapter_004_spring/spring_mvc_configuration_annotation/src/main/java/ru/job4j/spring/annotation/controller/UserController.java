package ru.job4j.spring.annotation.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.spring.annotation.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class.getName());

    final List<User> users = new CopyOnWriteArrayList<>();

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        model.addAttribute("users", this.users);
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addItem(@ModelAttribute User user) {
        this.users.add(user);
        return "redirect:users.do";
    }
}
