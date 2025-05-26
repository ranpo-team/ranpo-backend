package com.ranpo.ranpobackend.reward.domain;

import com.ranpo.ranpobackend.reward.domain.enums.RewardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "poll_rewards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PollReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RewardType type;

    private String value;

    private String description;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
