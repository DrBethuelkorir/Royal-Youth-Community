package org.roya.royalyouthcommunity.Repositories;

import org.roya.royalyouthcommunity.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
 Optional<Roles> findByRole(String role);
 boolean existsByRole(String name);
 Roles findById(long id);
}
