package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.models.GameResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

@Service
public class ZeldaGatewayService {

    private final RestTemplate restTemplate;

    @Autowired
    public ZeldaGatewayService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<GameModel> getAll(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 5;
        }

        String url = "https://zelda.fanapis.com/api/games?limit=" + size + "&page=" + page;

        GameListResponseModel response = restTemplate.getForObject(url, GameListResponseModel.class);

        if (response != null && response.isSuccess() && response.getData() != null) {
            return response.getData().stream().map(
                    gameDetails -> new GameModel(
                            gameDetails.getName(),
                            gameDetails.getDescription(),
                            gameDetails.getDeveloper(),
                            gameDetails.getPublisher(),
                            gameDetails.getReleasedDate(),
                            gameDetails.getId())
            ).toList();
        }

        return Collections.emptyList();
    }

    public GameModel getById(String id) {

        String url = "https://zelda.fanapis.com/api/games/" + id;

        try {
            ResponseEntity<GameResponseModel> response = restTemplate.exchange(url, HttpMethod.GET, null, GameResponseModel.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                if (response.getBody() != null){
                    return response.getBody().getGameModel();
                } else {
                    return null;
                }
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return null;
        }

        return null;
    }

    public List<GameModel> getAllByName (String name){

        String url = "https://zelda.fanapis.com/api/games?name=" + name;

        try {
            GameListResponseModel response = restTemplate.getForObject(url, GameListResponseModel.class);

            if (response != null && response.isSuccess() && response.getData() != null) {

                return response.getData().stream().map(
                        gameDetails -> new GameModel(
                                gameDetails.getName(),
                                gameDetails.getDescription(),
                                gameDetails.getDeveloper(),
                                gameDetails.getPublisher(),
                                gameDetails.getReleasedDate(),
                                gameDetails.getId())
                ).toList();

            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }
}