package com.eheiker.appdirect.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eheiker.appdirect.service.ProfileService;

@Controller
public class UserController {

    @Autowired
    ProfileService profileService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showCurrentUser(Model model, OpenIDAuthenticationToken authentication) {
        model.addAttribute("authentication", authentication);
        model.addAttribute("authenticated", authentication != null ? OpenIDAuthenticationStatus.SUCCESS.equals(authentication.getStatus()) : Boolean.FALSE);
        return "user/show";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model, OpenIDAuthenticationToken authentication) {
        model.addAttribute("users", profileService.getAll());
        model.addAttribute("authenticated", authentication != null ? OpenIDAuthenticationStatus.SUCCESS.equals(authentication.getStatus()) : Boolean.FALSE);
        return "users/show";
    }

}
