package com.BitirmeOdevi.HastaneRandevu.Service;
import com.BitirmeOdevi.HastaneRandevu.config.CustomUserDetails;
import com.BitirmeOdevi.HastaneRandevu.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.BitirmeOdevi.HastaneRandevu.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=userRepo.getUserByEmail(email);
        if (user!=null){
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("Kullanıcı bulunamadı" + email);

    }
}
