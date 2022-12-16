package com.rexcoinc.rexcoschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping("/contact")
    public String viewContactPage(){
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject,
                                    @RequestParam String message){

        log.info("Name: " + name);
        log.info("Phone: " + mobileNum);
        log.info("Email: " + email);
        log.info("Subject: " + subject);
        log.info("Message: " + message);
        return new ModelAndView("redirect:/contact");
    }
}