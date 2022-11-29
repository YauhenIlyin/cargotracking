package by.ilyin.web.service.auth;

import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.feign.UsersCoreFeignClient;
import by.ilyin.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersCoreFeignClient usersCoreFeignClient;

    //todo name not id exception
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        CustomUser customUser = usersCoreFeignClient.getUserById(id);
        if (customUser == null) {
            throw new UsernameNotFoundException("User with id: " + id + " not found"); //todo catch this exception ?
        }
        return new CustomUserDetails(customUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
