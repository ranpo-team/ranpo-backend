package com.ranpo.ranpobackend.vote.domain.repository;

import com.ranpo.ranpobackend.vote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
