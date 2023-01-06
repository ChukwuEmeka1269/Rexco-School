package com.rexcoinc.rexcoschool.service;

import com.rexcoinc.rexcoschool.constants.RexcoSchoolConstants;
import com.rexcoinc.rexcoschool.model.Contact;
import com.rexcoinc.rexcoschool.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(RexcoSchoolConstants.OPEN);
        contact.setCreatedBy(RexcoSchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int rowsAffected = contactRepository.saveContactMsg(contact);
        if(rowsAffected > 0){
            isSaved = true;
        }

        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        return contactRepository.findMsgsByStatus("Open");
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        int rowsAffected =  contactRepository.updateMsgStatus(contactId, RexcoSchoolConstants.CLOSED, updatedBy);
        if(rowsAffected>0){
            isUpdated = true;
        }

        return isUpdated;
    }
}
