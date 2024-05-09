package com.monopoly.monopoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monopoly.monopoly.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

	Place findByName(String name);

	List<Place> findByBuyPrice(double buyPrice);

	List<Place> findByRent(double rent);
}
