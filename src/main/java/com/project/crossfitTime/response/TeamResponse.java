package com.project.crossfitTime.response;

import com.project.crossfitTime.domain.Team;

public class TeamResponse {

    private Integer id;
    private String teamName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public static TeamResponse from(Team team) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setId(team.getId());
        teamResponse.setTeamName(team.getTeamName());
        return teamResponse;
    }
}
