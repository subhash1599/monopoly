package com.monopoly.monopoly.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monopoly.monopoly.entity.Player;
import com.monopoly.monopoly.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    
    @PostMapping("/create")
    public ResponseEntity<Player> createPlayer(@RequestParam String name) {
        Player player = playerService.createPlayer(name);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/{id}/roll-dice")
    public ResponseEntity<Integer> rollDice(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            int diceSum = playerService.rollDice();
            return new ResponseEntity<>(diceSum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/{id}/deduct-cash")
    public ResponseEntity<Player> deductCash(@PathVariable Long id, @RequestParam int amount) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            playerService.deductCash(player, amount);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to add cash to a player
    @PutMapping("/{id}/add-cash")
    public ResponseEntity<Player> addCash(@PathVariable Long id, @RequestParam int amount) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            playerService.addCash(player, amount);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/{id}/is-bankrupt")
    public ResponseEntity<Boolean> isBankrupt(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            boolean isBankrupt = playerService.isBankrupt(player);
            return new ResponseEntity<>(isBankrupt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

}

