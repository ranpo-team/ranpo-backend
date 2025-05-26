package com.ranpo.ranpobackend.vote.domain;

import com.ranpo.ranpobackend.poll.domain.Poll;
import com.ranpo.ranpobackend.poll.domain.PollOption;
import com.ranpo.ranpobackend.voter.domain.Voter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "votes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voter_id")
    private Voter voter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_option_id")
    private PollOption pollOption;

    private String anonymousToken;

    private Boolean isWinner;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
