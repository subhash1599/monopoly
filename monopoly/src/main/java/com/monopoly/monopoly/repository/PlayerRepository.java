package com.monopoly.monopoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monopoly.monopoly.entity.Player;

public interface PlayerRepository extends JpaRepository<Player,Long>{

	Player findByName(String name);
	List<Player> findByCashGreaterThan(double amount);
	
}
