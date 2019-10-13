package com.project.crossfitTime;

import com.project.crossfitTime.repository.GameRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

    private GameRepository gameRepository;

    @Autowired
    public void gameDataFetcher(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public DataFetcher getGame() {
        return dataFetchingEnvironment -> {
            String isn = dataFetchingEnvironment.getArgument("id");
            Integer id = Integer.valueOf(isn);
            System.out.println(gameRepository.findById(id));
            return gameRepository.findById(id).orElse(null);
        };
    }
}