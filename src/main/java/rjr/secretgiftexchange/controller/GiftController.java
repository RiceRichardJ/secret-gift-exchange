package rjr.secretgiftexchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rjr.secretgiftexchange.service.GiftService;

@RestController
public class GiftController {

	@Autowired
	GiftService giftService;

	@GetMapping("gift_exchange")
	public void getRoster() {
		giftService.getRoster();
	}

	@GetMapping("gift_exchange/{year}")
	public void getRoster(@PathVariable int year) {
		giftService.getRoster(year);
	}

	@PostMapping("generate_roster/{year}")
	public void generateRoster(@PathVariable int year) {
		giftService.generateRoster(year);
	}
}
