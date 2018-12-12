package eu.telecomnancy.championnat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.security.PublicKey;
import java.util.Optional;

interface CompetitionRepository extends  JpaRepository<Competition, Long> {
    // creating new instances
    // updating existing ones
    // deleting
    // finding (one, all, by simple or complex properties

    @Override
    Optional<Competition> findById(Long aLong);
}
