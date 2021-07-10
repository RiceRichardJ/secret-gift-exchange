package rjr.secretgiftexchange.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import rjr.secretgiftexchange.model.GiftExchange;

@Repository
public class GiftRepository {

	Map<Integer, Set<GiftExchange>> exchanges = new HashMap<>();

	

}
