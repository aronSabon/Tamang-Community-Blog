package com.skytest.service;

import java.util.List;

import com.skytest.model.Member;



public interface MemberService {
	void addMember(Member member);
	List<Member> getAllMember();
	void deleteMemberById(int id);
	Member getMemberById(int id);
	void updateMember(Member member);

}
