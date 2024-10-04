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
	CommitteeMemberRepository committeMemberRepository;
		@Override
		public void addCommitteMember(CommitteeMember committeeMember) {
			// TODO Auto-generated method stub
			committeMemberRepository.save(committeeMember);
		}

		@Override
		public List<CommitteeMember> getAllCommitteMember() {
			// TODO Auto-generated method stub
			return committeMemberRepository.findAll();
		}

		@Override
		public void deleteCommitteMemberById(int id) {
			// TODO Auto-generated method stub
			committeMemberRepository.deleteById(id);
		}

		@Override
		public CommitteeMember getCommitteMemberById(int id) {
			// TODO Auto-generated method stub
			return committeMemberRepository.findById(id).get();
		}

		@Override
		public void updateCommitteMember(CommitteeMember committeeMember) {
			// TODO Auto-generated method stub
			committeMemberRepository.save(committeeMember);
		}

}
