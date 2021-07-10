package rjr.secretgiftexchange.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class GiftExchange {
	private int gifterId;
	private int recipientId;
	private int year;
}
