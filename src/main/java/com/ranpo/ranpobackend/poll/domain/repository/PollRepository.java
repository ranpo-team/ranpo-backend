package com.ranpo.ranpobackend.poll.domain.repository;

import com.ranpo.ranpobackend.poll.domain.Poll;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {

    @EntityGraph(attributePaths = {"pollOptions"})
    Optional<Poll> findWithOptionsById(Long pollId);
}
