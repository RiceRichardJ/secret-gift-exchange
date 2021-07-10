package rjr.secretgiftexchange.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.secretgiftexchange.model.Member;
import rjr.secretgiftexchange.model.MemberRequest;
import rjr.secretgiftexchange.model.MemberResponse;
import rjr.secretgiftexchange.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public List<MemberResponse> getMembers() {
		return memberRepository.getMembers().stream().map(
			member -> new MemberResponse(member)
		).collect(Collectors.toList());
	}

	public MemberResponse getMemberById(int memberId) {
		Member m = memberRepository.getMemberById(memberId);
		return new MemberResponse(m);
	}

	public void addNewMember(MemberRequest newMember) {
		memberRepository.addNewMember(newMember.toMember());
	}

	public void updateMember(int memberId, MemberRequest updatedMember) {
		Member m = updatedMember.toMember();
		m.setId(memberId);
		memberRepository.updateMember(m);
	}

	public void deleteMemberById(int memberId) {
		memberRepository.deleteMemberById(memberId);
	}
}
