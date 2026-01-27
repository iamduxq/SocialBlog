package com.example.PersonalSocialBlog.security.oauth2;

import com.example.PersonalSocialBlog.security.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandle extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwt;

    public OAuth2SuccessHandle(JwtTokenProvider jwt) {
        this.jwt = jwt;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String token = jwt.generateToken(email);
        String redirectUrl = "http://localhost:5173/oauth2/redirect?token=" + token;
        response.sendRedirect(redirectUrl);
    }
}
