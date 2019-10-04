package com.project.crossfitTime.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "t_game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String gameName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private Date endDate;

    @CreatedDate
    private Date createDate;

    @Column
    private String description;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Team> teams = new ArrayList<>();

    public void addEvent(Event event) {
//        events.add(event);
        event.setGame(this);
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void bindGameToEvent(Event event) {
        event.setGame(this);
    }
    public List<Integer> getEventsId() {
        List<Integer> ids = new ArrayList<>();
        for (Event event : events) {
            ids.add(event.getId());
        }
        return ids;
    }

    public void addTeam(Team team) {
//        teams.add(team);
        team.setGame(this);
    }

    public void bindGameToTeam(Team team) {
        team.setGame(this);
    }
    public List<Integer> getTeamsId() {
        List<Integer> ids = new ArrayList<>();
        for (Team team : teams) {
            ids.add(team.getId());
        }
        return ids;
    }
}

