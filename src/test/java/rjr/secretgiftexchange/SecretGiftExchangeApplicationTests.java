package rjr.secretgiftexchange;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.web.servlet.MockMvc;

import rjr.secretgiftexchange.model.GiftExchange;
import rjr.secretgiftexchange.model.Member;
import rjr.secretgiftexchange.model.MemberRequest;
import rjr.secretgiftexchange.repository.GiftRepository;
import rjr.secretgiftexchange.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class SecretGiftExchangeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
  
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private GiftRepository giftRepository;

	private void hitAddMemberEndpoint(MemberRequest m) throws Exception {
		mockMvc.perform(post("/members")
				.contentType("application/json")
				.content(objectMapper
				.writeValueAsString(m))
			).andExpect(status().isOk());
	}

	@Test
	@Order(1)
	public void testAddMember() throws Exception {
		System.out.println("testAddMember");

		mockMvc.perform(get("/members"))
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));

		hitAddMemberEndpoint(new MemberRequest("Bob the Builder"));

		mockMvc.perform(get("/members"))
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"id\":0,\"name\":\"Bob the Builder\"}]"));

		Member m = memberRepository.getMemberById(0);
		assertEquals(m.getId(), 0);
		assertEquals(m.getName(), "Bob the Builder");

		memberRepository.deleteMemberById(0);
	}

	@Test
	@Order(2)
	public void testGenerateRoster() throws Exception {
		System.out.println("testGenerateRoster");

		hitAddMemberEndpoint(new MemberRequest("Alice"));
		hitAddMemberEndpoint(new MemberRequest("Bob"));
		hitAddMemberEndpoint(new MemberRequest("Charlie"));
		hitAddMemberEndpoint(new MemberRequest("David"));

		mockMvc.perform(post("/generate_roster/2019")).andExpect(status().isOk());
		mockMvc.perform(post("/generate_roster/2020")).andExpect(status().isOk());
		mockMvc.perform(post("/generate_roster/2021")).andExpect(status().isOk());

		mockMvc.perform(get("/gift_exchange/2019")).andExpect(status().isOk());
		mockMvc.perform(get("/gift_exchange/2020")).andExpect(status().isOk());
		mockMvc.perform(get("/gift_exchange/2021")).andExpect(status().isOk());

		Set<GiftExchange> allExchanges = new HashSet<>();
		allExchanges.addAll(giftRepository.getListForYear(2019));
		allExchanges.addAll(giftRepository.getListForYear(2020));
		allExchanges.addAll(giftRepository.getListForYear(2021));
		for (GiftExchange g : allExchanges) {
			g.setYear(31);
		}

		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(1).recipientId(2).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(2).recipientId(3).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(3).recipientId(4).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(4).recipientId(1).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(1).recipientId(3).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(3).recipientId(1).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(2).recipientId(4).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(4).recipientId(2).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(4).recipientId(3).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(3).recipientId(2).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(2).recipientId(1).year(31).build()));
		assertTrue(allExchanges.contains(GiftExchange.builder().gifterId(1).recipientId(4).year(31).build()));

	}
	
}
