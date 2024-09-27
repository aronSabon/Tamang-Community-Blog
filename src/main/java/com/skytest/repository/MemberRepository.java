package com.skytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytest.model.Member;


public interface MemberRepository extends JpaRepository<Member, Integer> {

}
