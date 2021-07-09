package rjr.secretgiftexchange.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import rjr.secretgiftexchange.model.Member;

@Repository
public class MemberRepository {

	Map<Integer, Member> members = new HashMap<>();

	public List<Member> getMembers() {
		List<Member> members = new ArrayList<>();
		Member m = new Member(1, "Bob the Builder");
		members.add(m);
		return members;
	}

}
