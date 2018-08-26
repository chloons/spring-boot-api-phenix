package com.adeo.phenix.east.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adeo.phenix.east.core.service.SoccerService;

@SpringBootApplication
public class PhenixSpringApiApplicationTests implements CommandLineRunner{

	@Autowired
    SoccerService soccerService;
	
    public static void main(String[] args) {
        SpringApplication.run(PhenixSpringApiApplicationTests.class, args);
    }
    // @Override
    public void run(String... arg0) throws Exception {
        
    	
    	findTeamByPlayer();
    }

	 @Test
	 public void findTeamByPlayer() {

	    	List<String> players = soccerService.getAllTeamPlayers(2L);
	        for(String player : players)
	        {
	            System.out.println("Introducing Losc player => " + player);
	        }
	        if (players != null && players.size()>1)  
	        	System.out.println("Team with player=> ");
	        else
	    		System.out.println("No team=> ");
	 }

    
} 