package rjr.secretgiftexchange.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		Map<Integer, Member> members = memberRepository.getMapOfMembers();
		Set<GiftExchange> exchanges = giftRepository.getListForYear(year);
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

		System.out.println(exchanges);
		System.out.println(responses);
		return responses;
	}

	public void generateRoster() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		generateRoster(year);
	}

	public void generateRoster(int year) {
		System.out.println("Generate Roster for year " + year);

		List<Member> members = memberRepository.getMembers();
		List<Member> shuffled = memberRepository.getMembers();
		Collections.shuffle(shuffled);

		Set<GiftExchange> exchanges = new HashSet<>();
		for (int i = 0; i < members.size(); i++) {
			int gifterId = members.get(i).getId();
			int recipientId = shuffled.get(i).getId();
			GiftExchange newExchange = GiftExchange.builder()
					.gifterId(gifterId)
					.recipientId(recipientId)
					.year(year).build();
			exchanges.add(newExchange);
		}

		giftRepository.setExchangesForYear(year, exchanges);
	}

}
