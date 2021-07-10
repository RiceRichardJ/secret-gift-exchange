package rjr.secretgiftexchange;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import rjr.secretgiftexchange.model.Member;
import rjr.secretgiftexchange.model.MemberRequest;
import rjr.secretgiftexchange.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@Test
	void testAddMember() throws Exception {
		mockMvc.perform(get("/members"))
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));

		MemberRequest firstMember = new MemberRequest("Bob the Builder");
		mockMvc.perform(post("/members")
				.contentType("application/json")
				.content(objectMapper
				.writeValueAsString(firstMember))
			).andExpect(status().isOk());

		mockMvc.perform(get("/members"))
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"id\":0,\"name\":\"Bob the Builder\"}]"));

		Member m = memberRepository.getMemberById(0);
		assertEquals(m.getId(), 0);
		assertEquals(m.getName(), "Bob the Builder");
	}

}
