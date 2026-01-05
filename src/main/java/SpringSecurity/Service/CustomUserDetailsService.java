package SpringSecurity.Service;



import SpringSecurity.Model.User;
import SpringSecurity.Repository.UserRepository;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;



    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

//        --------use when u dont have customUserDetail--------
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword()) // BCrypt hash
//                .roles("USER")
//                .build();

        return new CustomUserDetails(user);
    }
}
