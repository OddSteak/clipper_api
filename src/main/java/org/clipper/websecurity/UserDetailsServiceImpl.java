package org.clipper.websecurity;

import java.util.Optional;

import org.clipper.accessdb.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        final Optional<org.clipper.accessdb.User> user = this.userRepository.findById(username);

        if (!user.isPresent())
            throw new UsernameNotFoundException("username " + username + " doesn't exist");

        return User.withUsername(user.get().getId())
                .password(user.get().getPass())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    };
}
