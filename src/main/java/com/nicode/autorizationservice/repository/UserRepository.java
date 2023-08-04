package com.nicode.autorizationservice.repository;

import com.nicode.autorizationservice.authorities.Authorities;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private class User {

        private List<Authorities> userAuthorities;
        private String password;

        User(List<Authorities> userAuthorities, String password) {
            this.userAuthorities = userAuthorities;
            this.password = password;
        }

        List<Authorities> getUserAuthorities() {

            return this.userAuthorities;
        }

        public String getPassword() {
            return password;
        }
    }

    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    {
        users.put("user1", new User(List.of(Authorities.READ), "user1password"));
        users.put("user2", new User(List.of(Authorities.READ, Authorities.WRITE), "user2password"));
        users.put("user3", new User(List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE), "user3password"));
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
