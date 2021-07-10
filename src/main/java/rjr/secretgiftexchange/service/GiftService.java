package rjr.secretgiftexchange.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.secretgiftexchange.repository.GiftRepository;

@Service
public class GiftService {

	@Autowired
	GiftRepository giftRepository;

	public void getRoster() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		getRoster(year);
	}

	public void getRoster(int year) {
		System.out.println("Get Roster for year " + year);
	}

	public void generateRoster() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		generateRoster(year);
	}

	public void generateRoster(int year) {
		System.out.println("Generate Roster for year " + year);
	}

}
