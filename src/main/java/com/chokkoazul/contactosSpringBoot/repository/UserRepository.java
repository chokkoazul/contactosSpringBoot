package com.chokkoazul.contactosSpringBoot.repository;

import com.chokkoazul.contactosSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {

    User findByUsername(String username);
}
