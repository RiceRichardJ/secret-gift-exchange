package rjr.secretgiftexchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rjr.secretgiftexchange.service.GiftService;

@RestController
public class GiftController {

	@Autowired
	GiftService giftService;

	@GetMapping("/")
	public String helloWorld() {
		return giftService.helloWorld();
	}
}
