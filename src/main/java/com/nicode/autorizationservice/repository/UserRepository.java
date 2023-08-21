package com.nicode.autorizationservice.repository;

import com.nicode.autorizationservice.authorities.Authorities;
import com.nicode.autorizationservice.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final ConcurrentHashMap<String, User> users;

    {
        users = new ConcurrentHashMap<>();
        users.put("user10", new User("user10", "user1password1111111111", List.of(Authorities.READ)));
        users.put("user2", new User("user2", "user2password", List.of(Authorities.READ, Authorities.WRITE)));
        users.put("user3", new User("user3", "user3password", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
    }

    public List<Authorities> getUserAuthorities(@RequestParam String user, @RequestParam String password) {
        if (users.containsKey(user)) {
            User useR = users.get(user);
            if (Objects.equals(useR.getPassword(), password)) {
                return useR.getUserAuthorities();
            }
        }
        return null;
    }
}
