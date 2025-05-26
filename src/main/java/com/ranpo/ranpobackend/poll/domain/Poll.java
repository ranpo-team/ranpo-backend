package com.ranpo.ranpobackend.poll.domain;

import com.ranpo.ranpobackend.member.domain.Member;
import com.ranpo.ranpobackend.poll.domain.enums.AuthType;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerScope;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerSelectType;
import com.ranpo.ranpobackend.reward.domain.PollReward;
import com.ranpo.ranpobackend.reward.domain.enums.RewardMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "polls")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Enumerated(EnumType.STRING)
    private WinnerSelectType winnerSelectType;

    @Enumerated(EnumType.STRING)
    private WinnerScope winnerScope;

    private int totalWinnerCount;

    private Boolean rewardEnabled;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "poll_reward_id")
    @Enumerated(EnumType.STRING)
    private PollReward pollReward;

    @Enumerated(EnumType.STRING)
    private RewardMethod rewardMethod;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;
}
