package com.musicplatform.services.userDetailsServices;

import com.musicplatform.entities.Musician;
import com.musicplatform.repositories.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MusicianDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MusicianRepository musicianRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Musician musician = musicianRepository.findByEmail(email);
        if (musician != null) {
            User.UserBuilder builder = User.withUsername(email);
            builder.disabled(false);
            builder.password(musician.getPassword());
            String [] authoritiesArr = new String[]{Musician.authority};
            builder.authorities(authoritiesArr);
            return builder.build();
        }

        throw new UsernameNotFoundException("User not found.");
    }
}
