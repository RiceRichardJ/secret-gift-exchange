package rjr.secretgiftexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponse {
	private int id;
	private String name;

	public MemberResponse(Member m) {
		id = m.getId();
		name = m.getName();
	}
}
