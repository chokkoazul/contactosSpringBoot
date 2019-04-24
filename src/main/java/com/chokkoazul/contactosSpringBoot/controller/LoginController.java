package com.chokkoazul.contactosSpringBoot.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name="error" , required = false) String error, @RequestParam(name="logout" , required = false) String logout) {
        LOG.info("METHOD: showLoginForm() -- params: error="+error+" logout="+logout);
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        LOG.info("returning to login view");
        return "login";
    }

    @GetMapping({"/loginsuccess", "/"})
    public String loginCheck(){
        LOG.info("METHOD: loginCheck() ");
        LOG.info("returning to contact view");
        return "redirect:/contacts/showContacts";
    }

}
