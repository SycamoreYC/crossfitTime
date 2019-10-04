package com.project.crossfitTime.service;

import com.project.crossfitTime.domain.Event;
import com.project.crossfitTime.domain.Game;
import com.project.crossfitTime.domain.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    public Game getRealGame(Game game) {
        List<Event> events = game.getEvents().stream().filter(t -> t.getDeleted() == null || !t.getDeleted()).collect(Collectors.toList());
        game.setEvents(events);
        List<Team> teams = game.getTeams().stream().filter(t -> t.getDeleted() == null || !t.getDeleted()).collect(Collectors.toList());
        game.setTeams(teams);
        return game;
    }
}
