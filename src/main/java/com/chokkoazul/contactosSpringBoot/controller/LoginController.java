package com.chokkoazul.contactosSpringBoot.controller;

import com.chokkoazul.contactosSpringBoot.model.UserCredencial;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("/")
    public String redirectToLogin() {
        LOG.info("METHOD: redirectToLogin");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name="error" , required = false) String error, @RequestParam(name="logout" , required = false) String logout) {
        LOG.info("METHOD: showLoginForm() -- params: error="+error+" logout="+logout);
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        model.addAttribute("userCredencials", new UserCredencial());
        LOG.info("returning to login view");
        return "login";
    }

    @PostMapping("/logincheck")
    public String loginCheck(@ModelAttribute(name="userCredencials")UserCredencial userCredencial){
        LOG.info("METHOD: loginCheck() -- params: "+userCredencial.toString());
        if(userCredencial.getUserName().equals("user") && userCredencial.getPassword().equals("user")){
            LOG.info("returning to contact view");
            return "redirect:/contacts/showContacts";
        }
        LOG.info("redirect to login?error");
        return "redirect:/login?error";
    }

}
