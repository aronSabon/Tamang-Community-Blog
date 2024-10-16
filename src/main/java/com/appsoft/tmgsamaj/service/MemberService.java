package com.appsoft.tmgsamaj.service;

import java.util.List;

import com.appsoft.tmgsamaj.model.Member;



public interface MemberService {
	void addMember(Member member);
	List<Member> getAllMember();
	void deleteMemberById(int id);
	Member getMemberById(int id);
	void updateMember(Member member);
	Member getMemberByEmail(String email);

}
