package com.appsoft.tmgsamaj.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.tmgsamaj.model.Member;
import com.appsoft.tmgsamaj.repository.MemberRepository;
import com.appsoft.tmgsamaj.service.MemberService;



@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberRepository memberRepository;
		@Override
		public void addMember(Member member) {
			// TODO Auto-generated method stub
			memberRepository.save(member);
		}

		@Override
		public List<Member> getAllMember() {
			// TODO Auto-generated method stub
			return memberRepository.findAll();
		}

		@Override
		public void deleteMemberById(int id) {
			// TODO Auto-generated method stub
			memberRepository.deleteById(id);
		}

		@Override
		public Member getMemberById(int id) {
			// TODO Auto-generated method stub
			return memberRepository.findById(id).get();
		}

		@Override
		public void updateMember(Member member) {
			// TODO Auto-generated method stub
			memberRepository.save(member);
		}

		@Override
		public Member getMemberByEmail(String email) {
			// TODO Auto-generated method stub
			return memberRepository.findByContactEmail(email);
		}

}
