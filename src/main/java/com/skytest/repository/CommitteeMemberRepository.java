package com.skytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytest.model.CommitteeMember;


public interface CommitteeMemberRepository extends JpaRepository<CommitteeMember, Integer> {

}
