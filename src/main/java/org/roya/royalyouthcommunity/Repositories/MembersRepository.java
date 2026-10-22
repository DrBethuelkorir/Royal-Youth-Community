package org.roya.royalyouthcommunity.Repositories;

import org.roya.royalyouthcommunity.models.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByFirstName(String name);
    boolean existsByFirstName(String name);
    Members findById(long id);
}
