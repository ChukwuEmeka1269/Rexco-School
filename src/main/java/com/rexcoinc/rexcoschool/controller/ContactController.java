package com.rexcoinc.rexcoschool.controller;

import com.rexcoinc.rexcoschool.model.Contact;
import com.rexcoinc.rexcoschool.service.ContactService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String viewContactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact,
                                    Errors errors){
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to field error: "+ errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        return ("redirect:/contact");
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsg", contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg", method = RequestMethod.GET)
    public String closeMsg(@RequestParam int id, Authentication authentication){
        contactService.updateMsgStatus(id, authentication.getName());
        return "redirect:/displayMessages";
    }
}
