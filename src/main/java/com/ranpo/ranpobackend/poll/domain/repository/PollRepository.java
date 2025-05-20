package com.ranpo.ranpobackend.poll.domain.repository;

import com.ranpo.ranpobackend.poll.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
