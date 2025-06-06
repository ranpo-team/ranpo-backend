package com.ranpo.ranpobackend.member.domain;

import com.ranpo.ranpobackend.global.auth.dto.AuthenticatedUser;
import com.ranpo.ranpobackend.member.domain.enums.MemberRole;
import com.ranpo.ranpobackend.member.domain.enums.ProviderType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String uid;

    @Column(nullable = false, length = 20)
    private String nickname;

    private String phone;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Member(String email, String nickname, String uid, MemberRole role, ProviderType providerType) {
        this.email = email;
        this.nickname = nickname;
        this.uid = uid;
        this.role = role;
        this.providerType = providerType;
    }

    public Member(AuthenticatedUser user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getMemberRole();
    }
}
