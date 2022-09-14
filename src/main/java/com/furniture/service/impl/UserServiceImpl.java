package com.furniture.service.impl;

import java.util.*;

import com.furniture.Repository.CartRepository;
import com.furniture.Repository.UserRepository;
import com.furniture.exception.UserNameException;
import com.furniture.exception.UserNotFoundException;
import com.furniture.model.*;
import com.furniture.service.RoleService;
import com.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    //MY methord for authentication
    public UserInfo getUserByName(String name) throws UsernameNotFoundException{

        User user = userRepository.findByUsername(name);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        long id = user.getId();
        String s=String.valueOf(id);

        Set <Role> p=user.getRoles();
        List<String>roles = new ArrayList<>();

        for (Role x : p) {
            roles.add(x.getName());
        }
        UserInfo userInfo=new UserInfo(s,user.getUsername(),user.getEmail(),roles,user.getCart());

        return userInfo;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto user) {
       Optional<User>  u= Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if(!u.isPresent()) {
            User nUser = user.getUserFromDto();
            nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

            Role role = roleService.findByName("CUSTOMER");
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            Cart cart= new Cart();
            cartRepository.save(cart);

            if (nUser.getEmail().split("@")[1].equals("admin.com")) {
                role = roleService.findByName("ADMIN");
                roleSet.add(role);
            }
            if (nUser.getEmail().split("@")[1].equals("seller.com")) {
                role = roleService.findByName("SELLER");
                roleSet.add(role);
            }

            nUser.setRoles(roleSet);
            nUser.setCart(cart);
            return userRepository.save(nUser);
        }
        else {
            throw new UserNameException("UserName is Taken");
        }
    }


    // my services
    public List<User> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }
    public User getUserById(long id){
        Optional<User> users=userRepository.findById(id);
        if(users.isPresent()){

            return userRepository.findById(id).get();
        }
        else{
            throw new UserNotFoundException("Get Operation failed \n No User Found with id : "+id);
        }
    }
    public void deleteUser(long id)
    {
        Optional<User> users=userRepository.findById(id);
        if(users.isPresent()){
            userRepository.deleteById(id);
        }
        else{
            throw new UserNotFoundException(" Delete  Operation failed  \n No User Found with id : "+id);
        }
    }
    public void createUser(User user){
        userRepository.save(user);
    }
}
