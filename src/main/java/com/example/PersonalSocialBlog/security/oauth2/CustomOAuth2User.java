package com.example.PersonalSocialBlog.security.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final OAuth2User delegate;
    private final String email;

    public CustomOAuth2User(OAuth2User delegate, String email) {
        this.delegate = delegate;
        this.email = email;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return delegate.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return delegate.getAuthorities();
    }

    @Override
    public String getName() {
        return email;
    }
}
