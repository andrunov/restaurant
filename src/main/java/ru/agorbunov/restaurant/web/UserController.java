package ru.agorbunov.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by Admin on 27.02.2017.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        service.delete(getId(request));
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("user", service.get(getId(request)));
        return "user";
    }

    @PostMapping(value = "save")
    public String updateOrCreate(HttpServletRequest request) {
        String id = request.getParameter("id");
        User user = new User(Integer.valueOf(request.getParameter("id")),
                             request.getParameter("name"),
                             request.getParameter("email"),
                             request.getParameter("password"),
                             null,
                             Role.USER);
        service.save(user);
        return "redirect:/users";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
