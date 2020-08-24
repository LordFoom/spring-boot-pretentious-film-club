package lordfoom.sideprojects.pretentiousfilmclub.auth;

import lordfoom.sideprojects.pretentiousfilmclub.critic.Critic;
import lordfoom.sideprojects.pretentiousfilmclub.critic.CriticRegistrationDto;
import lordfoom.sideprojects.pretentiousfilmclub.critic.CriticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CriticUserDetailsService implements UserDetailsService {
    @Autowired
    CriticRepository criticRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Critic> critic = criticRepository.findByUsername(username);
        critic.orElseThrow(()->new UsernameNotFoundException(username));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        return critic.map(Critic::new).get();
        return new User(critic.get().getUsername(), critic.get().getPassword(), grantedAuthorities);
    }

    public Optional<Critic> findByUsername(String username){
        return criticRepository.findByUsername(username);
    }

    public Critic save(CriticRegistrationDto dto){
        Critic critic = new Critic();
        critic.setUsername(dto.getUsername());
        critic.setPassword(passwordEncoder.encode(dto.getPassword()));
        return criticRepository.save(critic);
    }


}
