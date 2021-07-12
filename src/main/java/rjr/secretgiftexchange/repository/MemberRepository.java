package rjr.secretgiftexchange.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import rjr.secretgiftexchange.exception.ResourceNotFoundException;
import rjr.secretgiftexchange.model.Member;

@Repository
public class MemberRepository {

	private static int id = 0;

	private Map<Integer, Member> members = new HashMap<>();

	public List<Member> getMembers() {
		return new ArrayList<>(members.values());
	}

	public Map<Integer, Member> getMapOfMembers() {
		return members;
	}

	public Member getMemberById(int memberId) {
		Member m = members.get(memberId);
		if (m == null) {
			throw new ResourceNotFoundException();
		}
		return m;
	}

	public void addNewMember(Member newMember) {
		newMember.setId(id++);
		members.put(newMember.getId(), newMember);
	}

	public void updateMember(Member updatedMember) {
		if (members.get(updatedMember.getId()) == null) {
			throw new ResourceNotFoundException();
		}
		members.put(updatedMember.getId(), updatedMember);
	}

	public void deleteMemberById(int memberId) {
		if (members.get(memberId) == null) {
			throw new ResourceNotFoundException();
		}
		members.remove(memberId);
	}
}
