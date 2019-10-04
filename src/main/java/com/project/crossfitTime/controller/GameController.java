package com.project.crossfitTime.controller;

import com.project.crossfitTime.domain.*;
import com.project.crossfitTime.repository.EventRepository;
import com.project.crossfitTime.repository.GameRepository;
import com.project.crossfitTime.repository.RecordRepository;
import com.project.crossfitTime.repository.TeamRepository;
import com.project.crossfitTime.response.GameResponse;
import com.project.crossfitTime.response.GameResult;
import com.project.crossfitTime.response.TeamResponse;
import com.project.crossfitTime.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private GameService gameService;

    @PostMapping
    public @ResponseBody
    GameResponse addNewGame(
            @RequestBody Game game
    ) {
        game.setCreateDate(new Date());

        List<Event> events = game.getEvents();
        for (Event event : events) {
            event.setDeleted(false);
            game.addEvent(event);
        }

        List<Team> teams = game.getTeams();
        for (Team team : teams) {
            team.setDeleted(false);
            game.addTeam(team);
        }

        return GameResponse.from(gameRepository.save(game));
    }

    @PostMapping(path = "/{gameId}")
    public @ResponseBody
    GameResult editAGame(
            @PathVariable(name = "gameId") Integer gameId,
            @RequestBody Game newGame
    ) {

        List<Record> records = recordRepository.findByGameId(gameId);

        if (records.size() > 0) {
            return new GameResult(false,"The game is over",null);
        }


        Game game = gameRepository.findById(gameId).get();

        game.setGameName(newGame.getGameName());
        game.setStartDate(newGame.getStartDate());
        game.setEndDate(newGame.getEndDate());
        game.setDescription(newGame.getDescription());

        //todo other attributes

        List<Event> newGameEvents = newGame.getEvents();
        List<Team> newGameTeams = newGame.getTeams();
        List<Event> gameEvents = game.getEvents();
        List<Team> gameTeams = game.getTeams();


        List<Integer> delEventIds = new ArrayList<>();
        List<Integer> gameIds = newGameEvents.stream().filter(t->t.getId() != null).map(Event::getId).collect(Collectors.toList());
        List<Event> delEvents = gameEvents.stream().filter(t->!gameIds.contains(t.getId())).map(t-> {
            t.setDeleted(true);
            t.setGame(game);
            delEventIds.add(t.getId());
            return t;
        }).collect(Collectors.toList());
//        eventRepository.saveAll(delEvents);

        List<Event> saveEvents =  newGameEvents.stream().filter(t-> t.getId() == null || !delEventIds.contains(t.getId())).map(t->{
            t.setDeleted(false);
            t.setGame(game);
            return t;
        }).collect(Collectors.toList());
        newGame.setEvents(saveEvents);


        List<Integer> delTeamIds = new ArrayList<>();
        List<Integer> teamIds = newGameTeams.stream().filter(t->t.getId() != null).map(Team::getId).collect(Collectors.toList());
        List<Team> delTeams = gameTeams.stream().filter(t->!teamIds.contains(t.getId())).map(t-> {
            t.setDeleted(true);
            t.setGame(game);
            delTeamIds.add(t.getId());
            return t;
        }).collect(Collectors.toList());
//        teamRepository.saveAll(delTeams);

        List<Team> saveTeams =  newGameTeams.stream().filter(t-> t.getId() == null || !delTeamIds.contains(t.getId())).map(t->{
            t.setDeleted(false);
            t.setGame(game);
            return t;
        }).collect(Collectors.toList());
        newGame.setTeams(saveTeams);

        newGame.getEvents().addAll(delEvents);
        newGame.getTeams().addAll(delTeams);
        return new GameResult(true,"update success." ,GameResponse.from(gameRepository.save(newGame)));
    }

    @GetMapping(path = "/{gameId}")
    public @ResponseBody
    GameResponse getAGame(
            @PathVariable(name = "gameId") Integer gameId
    ) {
        Game game = gameRepository.findById(gameId).get();
        return GameResponse.from(gameService.getRealGame(game));
    }

    @GetMapping
    public @ResponseBody
    List<GameResponse> getAllGames() {

        List<Record> records = new ArrayList<>();
        recordRepository.findAll().forEach(records::add);
        Map<Integer,List<Record>> gameMap  = records.stream().collect(Collectors.groupingBy(Record::getGameId));

        List<GameResponse> games = new ArrayList<>();
        gameRepository.findAll().forEach(game -> {
            GameResponse gameResponse = GameResponse.from(gameService.getRealGame(game));

            boolean canEdit = gameMap.get(game.getId()) == null;
            gameResponse.setCanEdit(canEdit);

            games.add(gameResponse);
        });

        return games;
    }

    @PostMapping(path = "/saveScore")
    public @ResponseBody
    List<Record> saveRecords(
            @RequestBody RecordRequest recordRequest) {

        Integer gameId = recordRequest.getGameId();
        Integer evenId = recordRequest.getEventId();
        List<TeamScore> teamScores = recordRequest.getTeamScores();

        List<Record> records = new ArrayList<>();
        teamScores.forEach(teamScore -> {
            Record record = new Record();
            record.setGameId(gameId);
            record.setEventId(evenId);
            record.setTeamId(teamScore.getTeamId());
            record.setScore(teamScore.getScore());
            records.add(record);
        });
        recordRepository.saveAll(records);
        return records;
    }

    @PostMapping(path = "/updateScore/{recordId}")
    public @ResponseBody
    Record updateRecord(
            @PathVariable(name = "recordId") Integer recordId,
            @RequestBody Record newRecord
    ) {
        Record record = recordRepository.findById(recordId).get();
        record.setScore(newRecord.getScore());
        recordRepository.save(record);
        return record;
    }
}
