package com.monopoly.monopoly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monopoly.monopoly.entity.Player;
import com.monopoly.monopoly.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    
    public Player createPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        player.setCash(1000); // Starting cash of $1000
        return playerRepository.save(player);
    }

    
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    
    public int rollDice() {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
    }

    
    public void deductCash(Player player, int amount) {
        player.setCash( (player.getCash() - amount));
        playerRepository.save(player);
    }

    
    public void addCash(Player player, int amount) {
        player.setCash(player.getCash() + amount);
        playerRepository.save(player);
    }

   
    public boolean isBankrupt(Player player) {
        return player.getCash() <= 0;
    }

    
}
