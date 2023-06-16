//package ru.zulvit.databasecoursework.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.zulvit.databasecoursework.model.Role;
//import ru.zulvit.databasecoursework.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//    private final UserService userService;
//
//    @Autowired
//    public UserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userService.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
//    }
//
//    private static List<GrantedAuthority> getAuthorities(User user) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
//}
