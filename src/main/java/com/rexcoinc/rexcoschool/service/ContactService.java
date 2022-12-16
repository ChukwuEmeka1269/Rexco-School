package com.rexcoinc.rexcoschool.service;

import com.rexcoinc.rexcoschool.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact){
        log.info("Name: " + contact.getName());
        log.info("Phone: " + contact.getMobileNum());
        log.info("Email: " + contact.getEmail());
        log.info("Subject: " + contact.getSubject());
        log.info("Message: " + contact.getMessage());
        return true;
    }
}
