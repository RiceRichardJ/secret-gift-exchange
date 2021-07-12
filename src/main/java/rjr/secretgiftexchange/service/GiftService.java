package rjr.secretgiftexchange.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.secretgiftexchange.exception.ResourceNotFoundException;
import rjr.secretgiftexchange.model.GiftExchange;
import rjr.secretgiftexchange.model.GiftExchangeResponse;
import rjr.secretgiftexchange.model.Member;
import rjr.secretgiftexchange.repository.GiftRepository;
import rjr.secretgiftexchange.repository.MemberRepository;

@Service
public class GiftService {

	@Autowired
	GiftRepository giftRepository;

	@Autowired
	MemberRepository memberRepository;

	public List<GiftExchangeResponse> getRoster() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return getRoster(year);
	}

	public List<GiftExchangeResponse> getRoster(int year) {
		System.out.println("Get Roster for year " + year);
		
		Set<GiftExchange> exchanges = giftRepository.getListForYear(year);
		if (exchanges == null) {
			throw new ResourceNotFoundException();
		}

		Map<Integer, Member> members = memberRepository.getMapOfMembers();
		List<GiftExchangeResponse> responses = new ArrayList<>();
		
		for (GiftExchange exchange : exchanges) {
			String gifterName = members.get(exchange.getGifterId()).getName();
			String recipientName = members.get(exchange.getRecipientId()).getName();
			GiftExchangeResponse resp = GiftExchangeResponse.builder()
					.year(exchange.getYear())
					.gifterId(exchange.getGifterId())
					.recipientId(exchange.getRecipientId())
					.gifterName(gifterName)
					.recipientName(recipientName)
					.build();
			responses.add(resp);
		}

		return responses;
	}

	public void generateRoster() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		generateRoster(year);
	}

	public int rotate(int amount, int max) {
		return amount > max ? amount - max - 1 : amount;
	}

	public void generateRoster(int year) {
		System.out.println("Generate Roster for year " + year);

		// needs to be in same order, not guaranteed from map/set
		Map<Integer, Member> members = memberRepository.getMapOfMembers();

		if (members.size() < 4) {
			throw new RuntimeException("nope");
		}

		Integer rotAmt1 = giftRepository.getRotationAmtForYear(year - 1);
		rotAmt1 = rotAmt1 == null ? 0 : rotAmt1;
		Integer rotAmt2 = giftRepository.getRotationAmtForYear(year - 2);
		rotAmt2 = rotAmt2 == null ? 0 : rotAmt2;

		Random r = new Random();
		int newRotAmt = r.nextInt(members.size() - 1) + 1;
		while (newRotAmt == rotAmt1 || newRotAmt == rotAmt2) {
			newRotAmt = r.nextInt(members.size() - 1) + 1;
		}

		Set<GiftExchange> exchanges = new HashSet<>();
		for (int i = 0; i < members.size(); i++) {
			int rotatedIndex = (newRotAmt + i) % members.size();

			int gifterId    = members.get(i).getId();
			int recipientId = members.get(rotatedIndex).getId();

			GiftExchange newExchange = GiftExchange.builder()
					.gifterId(gifterId)
					.recipientId(recipientId)
					.year(year).build();
			exchanges.add(newExchange);
		}

		giftRepository.setExchangesForYear(year, exchanges, newRotAmt);
	}

}
