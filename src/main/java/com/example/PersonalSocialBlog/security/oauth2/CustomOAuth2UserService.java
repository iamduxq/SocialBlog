package com.example.PersonalSocialBlog.security.oauth2;

import com.example.PersonalSocialBlog.entity.Enum.AuthProvider;
import com.example.PersonalSocialBlog.entity.RoleEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import com.example.PersonalSocialBlog.repository.RoleRepository;
import com.example.PersonalSocialBlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${app.user.default-avatar}")
    private String defaultAvatar;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(request);
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String avatar = oauth2User.getAttribute("picture");

        userRepository.findByEmail(email).orElseGet(() -> {
            RoleEntity role = roleRepository.findByCode("USER").orElseThrow(() -> new RuntimeException("Không tìm thấy role"));
            assert email != null;
            String baseUsername = email.split("@")[0];
            String username = baseUsername;
            int count = 1;
            while (userRepository.existsByUsername(username)) {
                username = username + count;
                count++;
            }

            UserEntity newUser = new UserEntity();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setFullName(name);
            newUser.setProvider(AuthProvider.GOOGLE);
            newUser.setPassword(null);
            newUser.setRoles(role);
            newUser.setAvatar(avatar != null ? avatar : defaultAvatar);
           return userRepository.save(newUser);
        });
        return new CustomOAuth2User(oauth2User, email);
    }
}
