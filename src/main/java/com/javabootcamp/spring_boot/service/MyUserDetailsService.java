package com.javabootcamp.spring_boot.service;

import com.javabootcamp.spring_boot.model.User;
import com.javabootcamp.spring_boot.repository.UserRepository;
import com.javabootcamp.spring_boot.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null)
        {
            throw new UsernameNotFoundException(username);
        }

        System.out.println("Found User: " + user.getUsername());

        String role = userRoleRepository.findRoleByUsername(user.getUsername());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        grantList.add(new SimpleGrantedAuthority(role));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantList);

        return userDetails;
    }
}
