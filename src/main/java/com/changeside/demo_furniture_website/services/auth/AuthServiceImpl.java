package com.changeside.demo_furniture_website.services.auth;

import com.changeside.demo_furniture_website.config.JwtGenerator;
import com.changeside.demo_furniture_website.models.dto.request.LoginRequest;
import com.changeside.demo_furniture_website.models.dto.request.RefreshTokenRequest;
import com.changeside.demo_furniture_website.models.dto.request.RegisterRequest;
import com.changeside.demo_furniture_website.models.dto.response.LoginResponse;
import com.changeside.demo_furniture_website.models.dto.response.RegisterResponse;
import com.changeside.demo_furniture_website.models.entity.Role;
import com.changeside.demo_furniture_website.models.entity.User;
import com.changeside.demo_furniture_website.repository.RoleRepository;
import com.changeside.demo_furniture_website.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new RegisterResponse("Username is taken");
        }

        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Role role = roleRepository.findByName("USER").orElseThrow();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);

        return new RegisterResponse("User registered successfully");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtGenerator.generateToken(authentication);
        String refreshToken = jwtGenerator.generateRefreshToken(authentication.getName());
        return new LoginResponse(accessToken, refreshToken);
    }

    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if (jwtGenerator.validateRefreshToken(refreshToken)) {
            String username = jwtGenerator.getUsernameFromJWT(refreshToken);
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            String newAccessToken = jwtGenerator.generateToken(authentication);
            return new LoginResponse(newAccessToken, refreshToken);
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid refresh token");
        }
    }
}