package com.skytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytest.model.Member;
import java.util.List;



public interface MemberRepository extends JpaRepository<Member, Integer> {
Member  findByContactEmail(String email);
}
