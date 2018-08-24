package com.adeo.phenix.east.core.controllers;


import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adeo.phenix.east.core.repository.PlayerNotFoundException;
import com.adeo.phenix.east.core.repository.PlayerRepository;
import com.adeo.phenix.east.core.repository.TeamRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.adeo.phenix.east.core.repository.TeamNotFoundException;

import com.adeo.phenix.east.core.model.Team;
import com.adeo.phenix.east.core.model.Player;

@RestController
@RequestMapping("/")
@Api(value = "user", description = "Rest API for user operations", tags = "User API")
public class SoccerController {
	
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;


	@GetMapping("/players")
	public Iterable<Player> retrieveAllPlayers() {
		 return playerRepository.findAll();	
	}

	//@GetMapping("/players/{id}")

    @RequestMapping(value = "/players/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Display greeting message to non-admin user", response = Player.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    })	
	public Player retrievePlayer(@PathVariable long id) {
		Optional<Player> player = playerRepository.findById(id);

		if (!player.isPresent())
			throw new PlayerNotFoundException("id-" + id);

		return player.get();
	}
	
	@DeleteMapping("/players/{id}")
	public void deletePlayer(@PathVariable long id) {
		playerRepository.deleteById(id);
	}	
	
	
	@GetMapping("/teams")
	public Iterable<Team> retrieveAllTeams() {
		return teamRepository.findAll();
	}

	@GetMapping("/teams/{id}")
	public Team retrieveTeam(@PathVariable long id) {
		Optional<Team> team = teamRepository.findById(id);

		if (!team.isPresent())
			throw new TeamNotFoundException("id-" + id);

		return team.get();
	}

	@DeleteMapping("/teams/{id}")
	public void deleteTeam(@PathVariable long id) {
		teamRepository.deleteById(id);
	}

	@PostMapping("/teams")
	public ResponseEntity<Object> createTeam(@RequestBody Team team) {
		Team savedTeam = teamRepository.save(team);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedTeam.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/teams/{id}")
	public ResponseEntity<Object> updateTeam(@RequestBody Team team, @PathVariable long id) {

		Optional<Team> teamOptional = teamRepository.findById(id);

		if (!teamOptional.isPresent())
			return ResponseEntity.notFound().build();

		team.setId(id);
		
		teamRepository.save(team);

		return ResponseEntity.noContent().build();
	}
}