package USERMANAGEMENT.service.auth;



import USERMANAGEMENT.model.auth.Account;
import USERMANAGEMENT.repository.auth.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository ;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user with " + email));

        return UserDetailsImpl.build(account);
    }
}
