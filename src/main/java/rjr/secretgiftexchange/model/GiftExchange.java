package rjr.secretgiftexchange.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class GiftExchange {
	private int gifterId;
	private int recipientId;
	private int year;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (year ^ (year >>> 32));
		result = prime * result + (int) (gifterId ^ (gifterId >>> 32));
		result = prime * result + (int) (recipientId ^ (recipientId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof GiftExchange)) {
			return false;
		}
		GiftExchange other = (GiftExchange)o;
		if (other.getGifterId() != this.gifterId) {
			return false;
		}
		if (other.getRecipientId() != this.recipientId) {
			return false;
		}
		if (other.getYear() != this.year) {
			return false;
		}
		return true;
	}
}
