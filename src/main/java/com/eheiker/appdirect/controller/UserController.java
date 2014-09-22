package com.eheiker.appdirect.controller;

import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String show(Model model, OpenIDAuthenticationToken authentication) {
        model.addAttribute("authentication", authentication);
        return "user/show";
    }

}
