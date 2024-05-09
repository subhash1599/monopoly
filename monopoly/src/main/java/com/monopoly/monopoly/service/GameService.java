package com.monopoly.monopoly.service;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monopoly.monopoly.entity.Game;
import com.monopoly.monopoly.entity.Place;
import com.monopoly.monopoly.entity.Player;
import com.monopoly.monopoly.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	public Game createGame(Player hostPlayer) {
		Game game = new Game();
		game.setHostPlayer(hostPlayer);
		return gameRepository.save(game);
	}

	public Game getGameById(Long id) {
		return gameRepository.findById(id).orElse(null);
	}

	public void joinGame(Game game, Player player) {
		if (game.getPlayers().size() < 2) {
			game.getPlayers().add(player);
			gameRepository.save(game);
		}
	}

	public boolean isGameFull(Game game) {
		return game.getPlayers().size() == 2;
	}

	public boolean purchasePlace(Game game, Player player, Place place) {
        if (place.getOwner() == null && player.getCash() >= place.getBuyPrice()) {
            
            player.setCash((int) (player.getCash() - place.getBuyPrice()));
            
            place.setOwner(player);
            
            gameRepository.save(game);
            return true; 
        } else {
            return false; 
        }
    }

 
    public void payRent(Game game, Player tenant, Player owner, Place place) {
        int rentAmount = (int) place.getRent();
        // Deduct rent from tenant's cash
        tenant.setCash((int) (tenant.getCash() - rentAmount));
        // Add rent to owner's cash
        owner.setCash((int) (owner.getCash() + rentAmount));
        // Update the game state
        gameRepository.save(game);
    }
	
	
    public Player endGame(Game game) {
        Player winner = null;
        if (!game.getPlayers().isEmpty()) {
            winner = game.getPlayers().stream()
                    .max(Comparator.comparingInt(player ->(int) player.getCash()))
                    .orElse(null);
        }
        return winner;
    }

}
