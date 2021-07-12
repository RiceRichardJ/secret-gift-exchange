package rjr.secretgiftexchange.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Member {
	private int id;
	private String name;
}
