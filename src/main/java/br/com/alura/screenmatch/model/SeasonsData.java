package br.com.alura.screenmatch.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
// criacao de lista para receber os episodios
@JsonIgnoreProperties(ignoreUnknown=true)
public record SeasonsData(@JsonAlias("Season")Integer number,
                          @JsonAlias("Episodes")List<EpisodesData> episodes) {
}
