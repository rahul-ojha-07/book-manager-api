package in.rahulojha.bookmanagerapi.auth.service;

import in.rahulojha.bookmanagerapi.auth.model.AuthRequest;
import in.rahulojha.bookmanagerapi.auth.model.AuthResponse;
import in.rahulojha.bookmanagerapi.entity.AppUser;
import in.rahulojha.bookmanagerapi.auth.repository.UserDetailsRepository;
import in.rahulojha.bookmanagerapi.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class AuthService {

    private final UserDetailsRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(AuthRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.isAdmin())
            user.setRole("ROLE_ADMIN");
        else
            user.setRole("ROLE_USER");

        userRepository.save(user);
        return AuthResponse.builder()
                .message("User Created!")
                .build();
    }

    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtils.generateToken(request.getUsername());
        return AuthResponse.builder()
                .message("Login Success!")
                .token(token)
                .build();
    }

}
