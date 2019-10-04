package com.project.crossfitTime.repository;

import com.project.crossfitTime.domain.Event;
import com.project.crossfitTime.domain.Game;
import com.project.crossfitTime.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query(value="u", nativeQuery = true)
    Game updateGame();

}
