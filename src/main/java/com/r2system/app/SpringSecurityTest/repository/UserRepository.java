package com.r2system.app.SpringSecurityTest.repository;


import com.r2system.app.SpringSecurityTest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {


    Optional<Users> findByUsername(String username);
}
