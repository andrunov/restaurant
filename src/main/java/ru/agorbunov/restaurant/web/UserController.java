package ru.agorbunov.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
//        AuthorizedUser.setId(userId);
        return "redirect:users";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
