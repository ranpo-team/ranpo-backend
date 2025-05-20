package com.ranpo.ranpobackend.winner.domain.repository;

import com.ranpo.ranpobackend.winner.domain.PollWinner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollWinnerRepository extends JpaRepository<PollWinner, Long> {
}
