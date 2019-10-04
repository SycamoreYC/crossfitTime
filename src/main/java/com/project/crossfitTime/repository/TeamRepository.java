package com.project.crossfitTime.repository;

import com.project.crossfitTime.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    List<Team> findByGameId(Integer gameId);
}
