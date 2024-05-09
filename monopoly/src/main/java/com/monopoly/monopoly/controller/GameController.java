package com.monopoly.monopoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.monopoly.monopoly.entity.Game;
import com.monopoly.monopoly.entity.Player;
import com.monopoly.monopoly.service.GameService;

@RestController
@RequestMapping("/games")
public class GameController {

	@Autowired
	private GameService gameService;

	@PostMapping("/create")
	public ResponseEntity<Game> createGame(@RequestBody Player hostPlayer) {
		Game game = gameService.createGame(hostPlayer);
		return new ResponseEntity<>(game, HttpStatus.CREATED);
	}

	@PutMapping("/{id}/join")
	public ResponseEntity<Game> joinGame(@PathVariable Long id, @RequestBody Player player) {
		Game game = gameService.getGameById(id);
		if (game != null && !gameService.isGameFull(game)) {
			gameService.joinGame(game, player);
			return ResponseEntity.ok(game);
		} else if (game != null && gameService.isGameFull(game)) {
			return ResponseEntity.badRequest().body(null); // Or you can return an error message
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint to end a game and declare the winner
	@PutMapping("/{id}/end")
	public ResponseEntity<Player> endGame(@PathVariable Long id) {
		Game game = gameService.getGameById(id);
		if (game != null) {
			Player winner = gameService.endGame(game);
			if (winner != null) {
				return new ResponseEntity<>(winner, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/is-full")
	public ResponseEntity<Boolean> isGameFull(@PathVariable Long id) {
		Game game = gameService.getGameById(id);
		if (game != null) {
			boolean isFull = gameService.isGameFull(game);
			return new ResponseEntity<>(isFull, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
