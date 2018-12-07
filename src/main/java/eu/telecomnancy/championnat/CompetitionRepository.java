package eu.telecomnancy.championnat;

import org.springframework.data.jpa.repository.JpaRepository;

interface CompetitionRepository extends  JpaRepository<Competition, Long> {
    // creating new instances
    // updating existing ones
    // deleting
    // finding (one, all, by simple or complex properties
}
