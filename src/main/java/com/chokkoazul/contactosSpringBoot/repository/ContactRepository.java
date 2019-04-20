package com.chokkoazul.contactosSpringBoot.repository;

import com.chokkoazul.contactosSpringBoot.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Serializable> {
}
