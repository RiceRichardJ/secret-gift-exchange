package rjr.secretgiftexchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rjr.secretgiftexchange.model.MemberRequest;
import rjr.secretgiftexchange.model.MemberResponse;
import rjr.secretgiftexchange.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
	MemberService memberService;

	@GetMapping()
	public List<MemberResponse> getMembers() {
		return memberService.getMembers();
	}

	@GetMapping("{memberId}")
	public MemberResponse getMemberById(@PathVariable int memberId) {
		return memberService.getMemberById(memberId);
	}

	@PostMapping()
	public void addNewMember(@RequestBody MemberRequest newMember) {
		memberService.addNewMember(newMember);
	}

	@PutMapping("{memberId}")
	public void updateMember(@PathVariable int memberId, @RequestBody MemberRequest newMember) {
		memberService.updateMember(memberId, newMember);
	}

	@DeleteMapping("{memberId}")
	public void deleteMemberById(@PathVariable int memberId) {
		memberService.deleteMemberById(memberId);
	}

}
