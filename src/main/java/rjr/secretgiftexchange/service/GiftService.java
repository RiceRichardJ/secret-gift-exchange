package rjr.secretgiftexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.secretgiftexchange.repository.GiftRepository;

@Service
public class GiftService {

	@Autowired
	GiftRepository giftRepository;

	public String helloWorld() {
		return giftRepository.helloWorld();
	}

}
