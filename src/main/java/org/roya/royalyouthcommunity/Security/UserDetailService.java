package org.roya.royalyouthcommunity.Security;


import lombok.RequiredArgsConstructor;
import org.roya.royalyouthcommunity.Repositories.UserRepository;
import org.roya.royalyouthcommunity.models.Users;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UsersDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UsersDetails.buildUserDetails(user);
    }
}
