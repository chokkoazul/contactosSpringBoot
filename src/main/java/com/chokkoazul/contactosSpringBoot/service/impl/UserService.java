package com.chokkoazul.contactosSpringBoot.service.impl;

import com.chokkoazul.contactosSpringBoot.entity.UserRole;
import com.chokkoazul.contactosSpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.chokkoazul.contactosSpringBoot.entity.User user = userRepository.findByUsername(s);
        List<GrantedAuthority> grantedAuthorities = buildAuthorities(user.getUserRole());
        return buildUser(user, grantedAuthorities);
    }

    private User buildUser(com.chokkoazul.contactosSpringBoot.entity.User user, List<GrantedAuthority> authorities){
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(UserRole userRole : userRoles){
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<GrantedAuthority>(authorities);
    }
}
