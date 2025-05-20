package com.ranpo.ranpobackend.reward.domain.repository;

import com.ranpo.ranpobackend.reward.domain.PollReward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRewardRepository extends JpaRepository<PollReward, Long> {
}
