package com.ranpo.ranpobackend.member.domain.repository;

import com.ranpo.ranpobackend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
