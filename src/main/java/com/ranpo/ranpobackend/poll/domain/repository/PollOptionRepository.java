package com.ranpo.ranpobackend.poll.domain.repository;

import com.ranpo.ranpobackend.poll.domain.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
}
