package rjr.secretgiftexchange.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GiftExchange {
	private int gifterId;
	private int recipientId;
	private int year;
}
