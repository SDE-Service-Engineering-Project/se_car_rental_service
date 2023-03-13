package at.ac.fhcampuswien.car_rental.service.user;

import at.ac.fhcampuswien.car_rental.repository.auth.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository repository;

    /**
     * Custom implementation of UserDetailsService from Spring Boot Security
     * <p>
     * In this project we only check if the account is locked based on failed attempts.
     *
     * @param username the username identifying the user whose data is required.
     * @return Spring Security User
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username).map(userEntity -> new User(userEntity.getUserName(),
                        userEntity.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
