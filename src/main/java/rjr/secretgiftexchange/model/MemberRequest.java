package rjr.secretgiftexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRequest {
	private String name;

	public MemberRequest() {}

	public Member toMember() {
		return Member.builder().name(name).build();
	}
}
