package com.project.crossfitTime.response;

import com.project.crossfitTime.domain.Game;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {

    private Integer id;
    private String gameName;
    private Date createDate;
    private Date startDate;
    private Date endDate;

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    private Boolean canEdit;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
    private List<EventResponse> events;
    private List<TeamResponse> teams;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<EventResponse> getEvents() {
        return events;
    }

    public void setEvents(List<EventResponse> events) {
        this.events = events;
    }

    public List<TeamResponse> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamResponse> teams) {
        this.teams = teams;
    }

    public static GameResponse from(Game game) {
        GameResponse gameResponse = new GameResponse();

        gameResponse.setId(game.getId());
        gameResponse.setCreateDate(game.getCreateDate());
        gameResponse.setStartDate(game.getStartDate());
        gameResponse.setEndDate(game.getEndDate());
        gameResponse.setGameName(game.getGameName());
        gameResponse.setDescription(game.getDescription());


//        BeanUtils.copyProperties(game,gameResponse);

        List<EventResponse> events = game.getEvents()
                .stream()
                .map(EventResponse::from)
                .collect(Collectors.toList());
        gameResponse.setEvents(events);

        List<TeamResponse> teams = game.getTeams()
                .stream()
                .map(TeamResponse::from)
                .collect(Collectors.toList());
        gameResponse.setTeams(teams);

        return gameResponse;
    }
}
