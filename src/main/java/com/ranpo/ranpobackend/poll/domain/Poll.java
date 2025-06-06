package com.ranpo.ranpobackend.poll.domain;

import com.ranpo.ranpobackend.member.domain.Member;
import com.ranpo.ranpobackend.poll.domain.enums.AuthType;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerScope;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerSelectType;
import com.ranpo.ranpobackend.reward.domain.PollReward;
import com.ranpo.ranpobackend.reward.domain.enums.RewardMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "poll", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PollOption> options = new ArrayList<>();

    private Boolean rewardEnabled;

    @Enumerated(EnumType.STRING)
    private RewardMethod rewardMethod;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    @Builder
    public Poll(
            Member member,
            String title,
            String description,
            LocalDateTime startAt,
            LocalDateTime endAt,
            AuthType authType,
            WinnerSelectType winnerSelectType,
            WinnerScope winnerScope,
            int totalWinnerCount,
            Boolean rewardEnabled
    ) {
        this.member = member;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.authType = authType;
        this.winnerSelectType = winnerSelectType;
        this.winnerScope = winnerScope;
        this.totalWinnerCount = totalWinnerCount;
        this.rewardEnabled = rewardEnabled;
    }
}
