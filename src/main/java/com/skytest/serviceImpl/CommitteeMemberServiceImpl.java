package com.skytest.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skytest.model.CommitteeMember;
import com.skytest.repository.CommitteeMemberRepository;
import com.skytest.service.CommitteeMemberService;



@Service
public class CommitteeMemberServiceImpl implements CommitteeMemberService {
	@Autowired
	CommitteeMemberRepository committeCommitteMemberRepository;
		@Override
		public void addCommitteMember(CommitteeMember committeCommitteMember) {
			// TODO Auto-generated method stub
			committeCommitteMemberRepository.save(committeCommitteMember);
		}

		@Override
		public List<CommitteeMember> getAllCommitteMember() {
			// TODO Auto-generated method stub
			return committeCommitteMemberRepository.findAll();
		}

		@Override
		public void deleteCommitteMemberById(int id) {
			// TODO Auto-generated method stub
			committeCommitteMemberRepository.deleteById(id);
		}

		@Override
		public CommitteeMember getCommitteMemberById(int id) {
			// TODO Auto-generated method stub
			return committeCommitteMemberRepository.findById(id).get();
		}

		@Override
		public void updateCommitteMember(CommitteeMember committeCommitteMember) {
			// TODO Auto-generated method stub
			committeCommitteMemberRepository.save(committeCommitteMember);
		}

}
