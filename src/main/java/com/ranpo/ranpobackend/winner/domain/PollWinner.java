package com.ranpo.ranpobackend.winner.domain;

import com.ranpo.ranpobackend.poll.domain.Poll;
import com.ranpo.ranpobackend.poll.domain.PollOption;
import com.ranpo.ranpobackend.vote.domain.Vote;
import com.ranpo.ranpobackend.voter.domain.Voter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "poll_winners")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PollWinner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_option_id")
    private PollOption pollOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voter_id")
    private Voter voter;

    private Boolean isRewardSent;

    private LocalDateTime rewardSentAt;

    private LocalDateTime createdAt;
}
