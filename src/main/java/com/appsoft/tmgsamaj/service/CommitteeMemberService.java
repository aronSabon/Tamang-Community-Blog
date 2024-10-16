package com.appsoft.tmgsamaj.service;

import java.util.List;

import com.appsoft.tmgsamaj.model.CommitteeMember;



public interface CommitteeMemberService {
	void addCommitteMember(CommitteeMember committeeMember);
	List<CommitteeMember> getAllCommitteMember();
	void deleteCommitteMemberById(int id);
	CommitteeMember getCommitteMemberById(int id);
	void updateCommitteMember(CommitteeMember committeeMember);

}
