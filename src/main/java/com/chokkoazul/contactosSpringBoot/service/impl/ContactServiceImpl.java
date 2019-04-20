package com.chokkoazul.contactosSpringBoot.service.impl;

import com.chokkoazul.contactosSpringBoot.converter.ContactConverter;
import com.chokkoazul.contactosSpringBoot.entity.Contact;
import com.chokkoazul.contactosSpringBoot.model.ContactModel;
import com.chokkoazul.contactosSpringBoot.repository.ContactRepository;
import com.chokkoazul.contactosSpringBoot.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

    @Autowired
    @Qualifier("contactRepository")
    private ContactRepository contactRepository;

    @Autowired
    @Qualifier("contactConverter")
    private ContactConverter contactConverter;


    @Override
    public ContactModel addContact(ContactModel contactModel) {
        Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
        return contactConverter.convertContact2ContactModel(contact);
    }

    @Override
    public List<ContactModel> listAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(contact -> contactConverter.convertContact2ContactModel(contact)).collect(Collectors.toList());
    }

    @Override
    public void deleteContact(int id) {
        contactRepository.deleteById(id);
    }

    @Override
    public ContactModel findContactById(int id) {
        return contactConverter.convertContact2ContactModel(contactRepository.findById(id).get());
    }
}
