package rjr.secretgiftexchange.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiftExchangeResponse {
	private int year;
	private int gifterId;
	private int recipientId;
	private String gifterName;
	private String recipientName;
}
