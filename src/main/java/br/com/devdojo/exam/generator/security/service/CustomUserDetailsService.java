package br.com.devdojo.exam.generator.security.service;


import br.com.devdojo.exam.generator.endpoint.v1.persistence.model.ApplicationUser;
import br.com.devdojo.exam.generator.endpoint.v1.persistence.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public CustomUserDetailsService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails applicationUser = loadUserByUsername(username);
        return new CustomerUserDetails(applicationUser);
    }



    public ApplicationUser loadApplicationUserByUserName(String username) {
        return Optional.ofNullable(applicationUserRepository.findByUserName(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }


    private final static class CustomerUserDetails extends ApplicationUser implements UserDetails {
        private CustomerUserDetails(UserDetails applicationUser){
            super(applicationUser);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorityListProfessor = AuthorityUtils.createAuthorityList("ROLE_PROFESSOR");
            List<GrantedAuthority> authorityListStudent = AuthorityUtils.createAuthorityList("ROLE_STUDENT");
            return this.getProfessor() != null ? authorityListProfessor : authorityListStudent;

        }

        @Override
        public String getUsername() {
            return null;
        }


        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
