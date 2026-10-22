package org.roya.royalyouthcommunity.Repositories;

import org.roya.royalyouthcommunity.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
 Optional<Users> findByEmail(String username);
 boolean existsByEmail(String email);
 Users findById(long id);
}
