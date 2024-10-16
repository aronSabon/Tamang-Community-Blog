package com.appsoft.tmgsamaj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.tmgsamaj.model.Member;

import java.util.List;



public interface MemberRepository extends JpaRepository<Member, Integer> {
Member  findByContactEmail(String email);
}
