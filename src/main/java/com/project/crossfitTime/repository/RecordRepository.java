package com.project.crossfitTime.repository;

import com.project.crossfitTime.domain.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RecordRepository extends CrudRepository<Record, Integer> {

    List<Record> findByGameId(Integer gameId);
}