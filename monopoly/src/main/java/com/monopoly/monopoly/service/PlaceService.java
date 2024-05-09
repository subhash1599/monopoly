package com.monopoly.monopoly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monopoly.monopoly.entity.Place;
import com.monopoly.monopoly.repository.PlaceRepository;


@Service
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;

	
	public List<Place> getAllPlaces() {
		return placeRepository.findAll();
	}

	
	public Place getPlaceById(Long id) {
		return placeRepository.findById(id).orElse(null);
	}

	
	public List<Place> getPlacesByBuyPrice(double buyPrice) {
		return placeRepository.findByBuyPrice(buyPrice);
	}

	
	public List<Place> getPlacesByRent(double rent) {
		return placeRepository.findByRent(rent);
	}

	
	public Place getPlaceByName(String name) {
		return placeRepository.findByName(name);
	}
}