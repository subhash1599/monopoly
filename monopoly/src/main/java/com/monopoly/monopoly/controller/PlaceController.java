package com.monopoly.monopoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.monopoly.monopoly.entity.Place;
import com.monopoly.monopoly.service.PlaceService;
import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

	@GetMapping("/all")
	public ResponseEntity<List<Place>> getAllPlaces() {
		List<Place> places = placeService.getAllPlaces();
		return new ResponseEntity<>(places, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
		Place place = placeService.getPlaceById(id);
		if (place != null) {
			return new ResponseEntity<>(place, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint to get places by their buy price
	@GetMapping("/by-buy-price")
	public ResponseEntity<List<Place>> getPlacesByBuyPrice(@RequestParam double buyPrice) {
		List<Place> places = placeService.getPlacesByBuyPrice(buyPrice);
		return new ResponseEntity<>(places, HttpStatus.OK);
	}

	@GetMapping("/by-rent")
	public ResponseEntity<List<Place>> getPlacesByRent(@RequestParam double rent) {
		List<Place> places = placeService.getPlacesByRent(rent);
		return new ResponseEntity<>(places, HttpStatus.OK);
	}

	@GetMapping("/by-name")
	public ResponseEntity<Place> getPlaceByName(@RequestParam String name) {
		Place place = placeService.getPlaceByName(name);
		if (place != null) {
			return new ResponseEntity<>(place, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
