package mzaba.spring.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mzaba.spring.security.data.repository.UserRepository;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username);
        if(user == null){
            log.error("User " + username + " not found.");
            throw new UsernameNotFoundException("Error - user not found.");
        } else {
            if (loginAttemptService.isBlocked(user.getUsername())){
                throw new LockedException("User is blocked!");
            }
        }
        log.info("The required user was found: " + username);
        return user;
    }
}
