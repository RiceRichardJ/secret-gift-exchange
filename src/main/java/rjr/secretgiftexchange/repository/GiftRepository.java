package rjr.secretgiftexchange.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import rjr.secretgiftexchange.model.GiftExchange;

@Repository
public class GiftRepository {

	private Map<Integer, Set<GiftExchange>> exchangeHistory = new HashMap<>();

	private Map<Integer, Integer> yearRotationAmt = new HashMap<>();

	public Set<GiftExchange> getListForYear(int year) {
		return exchangeHistory.get(year);
	}

	public void setExchangesForYear(int year, Set<GiftExchange> exchanges, int rotationAmt) {
		exchangeHistory.put(year, exchanges);
		yearRotationAmt.put(year, rotationAmt);
	}

	public Integer getRotationAmtForYear(int year) {
		return yearRotationAmt.get(year);
	}

}
