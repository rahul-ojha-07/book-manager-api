package in.rahulojha.bookmanagerapi.auth.service;

import in.rahulojha.bookmanagerapi.entity.AppUser;
import in.rahulojha.bookmanagerapi.auth.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailsRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findAppUserByUsername(username);
        if (user.isPresent()) {
            AppUser user1 = user.get();
            return new User(user1.getUsername(), user1.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(user1.getRole())));
        }
         throw new UsernameNotFoundException("No User with given username");
    }
}
