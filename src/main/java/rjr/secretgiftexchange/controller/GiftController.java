package rjr.secretgiftexchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rjr.secretgiftexchange.model.GiftExchangeResponse;
import rjr.secretgiftexchange.service.GiftService;

@RestController
public class GiftController {

	@Autowired
	GiftService giftService;

	@GetMapping("gift_exchange")
	public List<GiftExchangeResponse> getRoster() {
		return giftService.getRoster();
	}

	@GetMapping("gift_exchange/{year}")
	public List<GiftExchangeResponse> getRoster(@PathVariable int year) {
		return giftService.getRoster(year);
	}

	@PostMapping("generate_roster")
	public void generateRoster() {
		giftService.generateRoster();
	}

	@PostMapping("generate_roster/{year}")
	public void generateRoster(@PathVariable int year) {
		giftService.generateRoster(year);
	}
}
