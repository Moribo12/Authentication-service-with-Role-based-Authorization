package com.geeks4learning.Authenticationservice.Repository;

import com.geeks4learning.Authenticationservice.Enum.Role;
import com.geeks4learning.Authenticationservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
