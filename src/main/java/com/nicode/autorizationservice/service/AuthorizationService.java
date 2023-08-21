package com.nicode.autorizationservice.service;

import com.nicode.autorizationservice.authorities.Authorities;
import com.nicode.autorizationservice.exception.InvalidCredentials;
import com.nicode.autorizationservice.exception.UnauthorizedUser;
import com.nicode.autorizationservice.repository.UserRepository;
import com.nicode.autorizationservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository repository) {
        this.userRepository = repository;
    }

	public List<Authorities> getAuthorities(User useR) {
        String user = useR.getName();
        String password = useR.getPassword();
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or (and) password is (are) empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
