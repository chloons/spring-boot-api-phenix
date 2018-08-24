package com.adeo.phenix.east.core;

import java.util.Arrays;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adeo.phenix.east.core.service.SoccerService;

@SpringBootApplication
//public class PhenixSpringApiApplication implements CommandLineRunner{
public class PhenixSpringApiApplication {
			

	@Autowired
    SoccerService soccerService;
	
    public static void main(String[] args) {
        SpringApplication.run(PhenixSpringApiApplication.class, args);
    }
    // @Override
    public void run2(String... arg0) throws Exception {
        soccerService.addBarcelonaPlayer("Xavi Hernandez", "Midfielder", 6);
        List<String> players = soccerService.getAllTeamPlayers(1);
        for(String player : players)
        {
            System.out.println("Introducing Barca player => " + player);
        }
    }
}    