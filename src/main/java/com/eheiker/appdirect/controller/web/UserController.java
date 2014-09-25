package com.eheiker.appdirect.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eheiker.appdirect.service.myapp.ProfileService;

@Controller
public class UserController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showCurrentUser(Model model, OpenIDAuthenticationToken authentication) {
        model.addAttribute("authentication", authentication);
        return "user/show";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", profileService.getAll());
        return "users/show";
    }

}
