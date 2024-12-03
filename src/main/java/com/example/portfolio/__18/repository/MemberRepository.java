package com.example.portfolio.__18.repository;

import com.example.portfolio.__18.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
    Member findByEmail(String email);
    Member findByName(String name);
}
