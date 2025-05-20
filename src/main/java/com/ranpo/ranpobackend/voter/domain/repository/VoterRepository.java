package com.ranpo.ranpobackend.voter.domain.repository;

import com.ranpo.ranpobackend.voter.domain.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
