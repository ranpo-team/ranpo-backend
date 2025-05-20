package com.ranpo.ranpobackend.voter.domain;

import com.ranpo.ranpobackend.member.domain.Member;
import com.ranpo.ranpobackend.poll.domain.Poll;
import com.ranpo.ranpobackend.poll.domain.enums.AuthType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "voters")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    private AuthType authType;

    private String email;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime verifiedAt;
}
