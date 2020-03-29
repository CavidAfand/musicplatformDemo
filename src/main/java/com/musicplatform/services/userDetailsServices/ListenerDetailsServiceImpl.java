package com.musicplatform.services.userDetailsServices;

import com.musicplatform.entities.Listener;
import com.musicplatform.repositories.ListenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ListenerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ListenerRepository listenerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Listener listener = listenerRepository.findByEmail(email);
        if (listener != null) {
            User.UserBuilder builder = User.withUsername(email);
            builder.disabled(false);
            builder.password(listener.getPassword());
            String [] authoritiesArr = new String[] {Listener.authority};
            builder.authorities(authoritiesArr);
            return builder.build();
        }

        throw new UsernameNotFoundException("User not found");
    }


}
