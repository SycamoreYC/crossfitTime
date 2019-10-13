package com.project.crossfitTime.service;

import com.project.crossfitTime.domain.Event;
import com.project.crossfitTime.domain.Game;
import com.project.crossfitTime.domain.Record;
import com.project.crossfitTime.domain.Team;
import com.project.crossfitTime.repository.GameRepository;
import com.project.crossfitTime.repository.RecordRepository;
import com.project.crossfitTime.response.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    RecordRepository recordRepository;
    @Autowired
    GameRepository gameRepository;

    public Game getRealGame(Game game) {
        List<Event> events = game.getEvents().stream().filter(t -> t.getDeleted() == null || !t.getDeleted()).collect(Collectors.toList());
        game.setEvents(events);
        List<Team> teams = game.getTeams().stream().filter(t -> t.getDeleted() == null || !t.getDeleted()).collect(Collectors.toList());
        game.setTeams(teams);
        return game;
    }

    public List<GameResponse> getAllGames() {

        List<Record> records = new ArrayList<>();
        recordRepository.findAll().forEach(records::add);
        Map<Integer,List<Record>> gameMap  = records.stream().collect(Collectors.groupingBy(Record::getGameId));

        List<GameResponse> games = new ArrayList<>();
        gameRepository.findAll().forEach(game -> {
            GameResponse gameResponse = GameResponse.from(this.getRealGame(game));

            boolean canEdit = gameMap.get(game.getId()) == null;
            gameResponse.setCanEdit(canEdit);

            games.add(gameResponse);
        });

        return games;
    }
}
