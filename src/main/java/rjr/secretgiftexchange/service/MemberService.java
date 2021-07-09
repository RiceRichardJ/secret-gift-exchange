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

}
