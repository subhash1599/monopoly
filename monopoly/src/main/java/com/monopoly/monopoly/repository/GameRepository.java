package com.monopoly.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monopoly.monopoly.entity.Game;

public interface GameRepository extends JpaRepository<Game,Long>{

}
