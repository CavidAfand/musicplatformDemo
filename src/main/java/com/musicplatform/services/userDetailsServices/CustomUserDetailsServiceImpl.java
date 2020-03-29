package com.musicplatform.services.userDetailsServices;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Musician;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.ListenerRepository;
import com.musicplatform.repositories.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ListenerRepository listenerRepository;

    @Autowired
    BandRepository bandRepository;

    @Autowired
    MusicianRepository musicianRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Listener listener = listenerRepository.findByUsername(username);
        if (listener != null) {
            UserBuilder builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(listener.getPassword());
            String [] authoritiesArr = new String[] {Listener.authority};
            builder.authorities(authoritiesArr);
            return builder.build();
        }

        Musician musician = musicianRepository.findByUsername(username);
        if (musician != null) {
            UserBuilder builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(musician.getPassword());
            String [] authoritiesArr = new String[]{Musician.authority};
            builder.authorities(authoritiesArr);
            return builder.build();
        }

        Band band = bandRepository.findByUsername(username);
        if (band != null) {
            UserBuilder builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(band.getPassword());
            String [] authoritiesArr = new String[]{Band.authority};
            builder.authorities(authoritiesArr);
            return builder.build();
        }

        throw new UsernameNotFoundException("User not found.");
    }

}
