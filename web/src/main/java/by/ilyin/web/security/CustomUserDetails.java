package by.ilyin.web.security;

import by.ilyin.web.entity.CustomUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final CustomUser customUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customUser.getUserRoles()
                .stream()
                .map(o -> new SimpleGrantedAuthority("ROLE_" + o.getRoleType().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.customUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.customUser.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE;
    }

    public Long getId() {
        return this.customUser.getId();
    }

}
