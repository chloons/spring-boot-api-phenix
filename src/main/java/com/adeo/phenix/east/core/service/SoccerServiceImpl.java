package com.adeo.phenix.east.core.service;
	
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.adeo.phenix.east.core.model.Player;
import com.adeo.phenix.east.core.model.Team;
import com.adeo.phenix.east.core.repository.PlayerRepository;
	import com.adeo.phenix.east.core.repository.TeamRepository;

	@Service
	public class SoccerServiceImpl implements SoccerService {
	    @Autowired
	    private PlayerRepository playerRepository;
	    @Autowired
	    private TeamRepository teamRepository;
	    public List<String> getAllTeamPlayers(long teamId) {
	        List<String> result = new ArrayList<String>();
	        List<Player> players = playerRepository.findByTeamId(teamId);
	        for (Player player : players) {
	            result.add(player.getName());
	        }
	        return result;
	    }
	    public void addBarcelonaPlayer(String name, String position, int number) {
	        
	        Optional<Team> barcelona = teamRepository.findById(1L);
	        
	        Player newPlayer = new Player();
	        newPlayer.setName(name);
	        newPlayer.setPosition(position);
	        newPlayer.setNum(number);
	        
	        if (barcelona.isPresent()) newPlayer.setTeam(barcelona.get());
	        playerRepository.save(newPlayer);
	    }
	}	

