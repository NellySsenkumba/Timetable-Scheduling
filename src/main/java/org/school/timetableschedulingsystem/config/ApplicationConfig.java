//package org.school.timetableschedulingsystem.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.sql.Timestamp;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig {
//
//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//
//    private final System.Logger logger = System.getLogger("App");
//
//
//    private static final List<UserDetails> SYSTEM_USERS = Arrays.asList(
//            new User(
//                    "ssenkumbanelson.sn@gmail.com",
//                    passwordEncoder.encode("123456789"),
//                    Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
//            ),
//            new User(
//                    "user@gmail.com",
//                    passwordEncoder.encode("password"),
//                    Collections.singleton(new SimpleGrantedAuthority("GUEST"))
//            )
//    );
//
//    //def getUsername():
////    return 1,2
//    @Bean
//    public UserDetailsService userDetailsService() {
////        return username ->
////                usersRepository.findByEmail(username)
////                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return username -> null;
//    }
//
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return username ->
////                SYSTEM_USERS
////                        .stream()
////                        .filter(user -> user.getUsername().equals(username))
////                        .findFirst()
////                        .orElseThrow(() -> new UsernameNotFoundException("No user was Found"));
////    }
//
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());//get userdatial
//        authProvider.setPasswordEncoder(passwordEncoder());//encode coming passewro
//        return authProvider;
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//
//}
