package com.musicplatform.services.userDetailsServices;

import com.musicplatform.entities.Band;
import com.musicplatform.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BandDetailsServiceImpl implements UserDetailsService {

    @Autowired
    BandRepository bandRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Band band = bandRepository.findByEmail(email);
        if (band != null) {
            User.UserBuilder builder = User.withUsername(email);
            builder.disabled(false);
            builder.password(band.getPassword());
            String [] authoritiesArr = new String[]{Band.authority};
            builder.authorities(authoritiesArr);
            return builder.build();
        }

        throw new UsernameNotFoundException("User not found.");
    }

}
