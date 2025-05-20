package com.ranpo.ranpobackend.reward.domain;

import com.ranpo.ranpobackend.reward.domain.enums.RewardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "poll_rewards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PollReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private RewardType type;

    private String value;

    private String description;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
