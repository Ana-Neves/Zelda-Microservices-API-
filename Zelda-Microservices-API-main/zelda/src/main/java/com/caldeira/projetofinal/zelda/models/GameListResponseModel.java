package com.caldeira.projetofinal.zelda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameListResponseModel {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("count")
    private int count;

    @JsonProperty("data")
    private List<GameDetails> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GameDetails {

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("developer")
        private String developer;

        @JsonProperty("publisher")
        private String publisher;

        @JsonProperty("released_date")
        private String releasedDate;

        @JsonProperty("id")
        private String id;
    }
}
