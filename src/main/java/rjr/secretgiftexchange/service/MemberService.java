package rjr.secretgiftexchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.secretgiftexchange.model.Member;
import rjr.secretgiftexchange.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	public Member getMemberById(int memberId) {
		return memberRepository.getMemberById(memberId);
	}

	public void addNewMember(Member newMember) {
		memberRepository.addNewMember(newMember);
	}

	public void updateMember(Member updatedMember) {
		memberRepository.updateMember(updatedMember);
	}

	public void deleteMemberById(int memberId) {
		memberRepository.deleteMemberById(memberId);
	}
}
