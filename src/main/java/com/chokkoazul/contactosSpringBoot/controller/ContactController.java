package com.chokkoazul.contactosSpringBoot.controller;

import com.chokkoazul.contactosSpringBoot.model.ContactModel;
import com.chokkoazul.contactosSpringBoot.repository.ContactRepository;
import com.chokkoazul.contactosSpringBoot.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    @Qualifier("contactServiceImpl")
    private ContactService contactService;

    @GetMapping("/cancel")
    private ModelAndView redirecCancel() {
        return showContacts();
    }

    @GetMapping("/contactform")
    private String redirec(@RequestParam(name="id", required = false) int id, Model model) {
        ContactModel contactModel = new ContactModel();

        if(id != 0){
            contactModel = contactService.findContactById(id);
        }
        model.addAttribute("contactModel", contactModel);
        return "contactform";
    }

    @PostMapping("/addcontact")
    public String addContact(@ModelAttribute(name="contactModel")ContactModel contactModel, Model model){

        if(null != contactService.addContact(contactModel)){
            model.addAttribute("result", 1);
        }
        else{
            model.addAttribute("result", 0);
        }

        return "redirect:/contacts/showContacts";
    }


    @GetMapping("/showContacts")
    public ModelAndView showContacts(){
        ModelAndView modelAndView = new ModelAndView("contacts");
        modelAndView.addObject("contacts", contactService.listAllContacts());
        return modelAndView;
    }

    @GetMapping("/removecontact")
    public ModelAndView removeContact(@RequestParam(name="id", required = true) int id){
        contactService.deleteContact(id);
        return showContacts();
    }

}
