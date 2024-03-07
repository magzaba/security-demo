package mzaba.spring.security.listener;

import mzaba.spring.security.data.repository.UserRepository;
import mzaba.spring.security.service.LoginAttemptService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

public class AuthenticationFailureListener implements
        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private LoginAttemptService loginAttemptService;
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        if(event.getAuthentication().getPrincipal() instanceof String username) {
            var user = userRepository.findUserByUsername(username);
            if (user != null){
                loginAttemptService.loginFailed(username);
            }
        }
    }
}
