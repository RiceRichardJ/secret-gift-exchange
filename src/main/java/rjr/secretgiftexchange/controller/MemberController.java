package rjr.secretgiftexchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rjr.secretgiftexchange.model.Member;
import rjr.secretgiftexchange.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
	MemberService memberService;

	@GetMapping()
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
}
