package com.project.crossfitTime.repository;

import com.project.crossfitTime.domain.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findByGameId(Integer gameId);
}
