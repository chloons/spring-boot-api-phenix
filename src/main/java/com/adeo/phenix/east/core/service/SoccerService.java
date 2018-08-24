package com.adeo.phenix.east.core.service;

import java.util.List;

public interface SoccerService {
    public List<String> getAllTeamPlayers(long teamId);
    public void addBarcelonaPlayer(String name, String position, int number);
}
