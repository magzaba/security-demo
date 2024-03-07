package mzaba.spring.security.handler;

import lombok.AllArgsConstructor;
import mzaba.spring.security.data.model.User;
import mzaba.spring.security.service.LoginAttemptService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var user = (User) authentication.getPrincipal();
        loginAttemptService.loginSuccess(user.getUsername());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
