package com.chokkoazul.contactosSpringBoot.service;

import com.chokkoazul.contactosSpringBoot.model.ContactModel;

import java.util.List;

public interface ContactService {

    ContactModel addContact(ContactModel contactModel);

    List<ContactModel> listAllContacts();

    void deleteContact(int id);

    ContactModel findContactById(int id);

}
