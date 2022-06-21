package com.example.springcloud.gateway.apiGateway.filter;

//import com.example.demo.dao.AuthorityListRepository;
//import com.example.demo.dao.UserRepository;
//import com.example.demo.dto.AuthorityDTO;
//import com.example.demo.dto.AuthorityListDTO;
//import com.example.demo.dto.UserDTO;
//import com.example.demo.entity.AuthorityEnum;
//import com.example.demo.entity.User;
import com.example.springcloud.gateway.apiGateway.entity.User;
import com.example.springcloud.gateway.apiGateway.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private AuthorityListService authorityListService;
//
//    @Autowired
//    private AuthorityListRepository authorityListRepository;
//
//    @Autowired
//    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

//    public int save(UserDTO userDTO) {
//
//        User newUser = new User(userDTO.getFirstName(),
//                userDTO.getLastName(), userDTO.getEmail(),
//                userDTO.getBirthDate(), bcryptEncoder.encode(userDTO.getPassword()));
//        newUser = userRepository.save(newUser);
//
//        AuthorityDTO authorityDTO = new AuthorityDTO(AuthorityEnum.ROLE_CLIENT);
//        userDTO.setId(newUser.getId());
//
//        AuthorityListDTO authorityListDTO = new AuthorityListDTO(authorityDTO, userDTO);
//
//        authorityListService.save(authorityListDTO);
//
////        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO);
////
////        restTemplate.exchange
////                ("http://localhost:8080/account/addAccount/users/" + newUser.getId(),
////                        HttpMethod.POST, request, Object.class);
//
//        return newUser.getId();
//
//    }
}