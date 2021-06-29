package com.github.yeeun_yun97.LottoPredictAppBackEnd.security;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.LoginResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.User;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.LoginDataWrongException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.SimpleException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.UserNotFoundException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.TOKEN_SUFFIX_BEARER;

@AllArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("해당하는 유저가 존재하지 않습니다."));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    public LoginResponse authenticateByEmailAndPassword(String email, String password) throws SimpleException {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginDataWrongException();
        }
        final String token= this.jwtTokenUtil.generateToken(user.getEmail());
        return LoginResponse.builder().token(TOKEN_SUFFIX_BEARER+token).user_id(user.getId()).build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
