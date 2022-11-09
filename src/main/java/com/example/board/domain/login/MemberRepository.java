package com.example.board.domain.login;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByMemberId(String memberId);
    Member findByMemberId(String memberId);
}
