package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

// Ignorar propriedades que não foram especificadas no parametro de busca dessa classe
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// como padrao o ignoreUnknown é false, por isso precisamos estabelecer expressamente que ele é verdadeiro
@JsonIgnoreProperties(ignoreUnknown = true)

// MAPEAMENTO de como ficara os dados depois da desserializacao
public record SeriesData(@JsonAlias("Title") String title,
                         @JsonAlias("totalSeasons") Integer totalSeasons,
                         @JsonAlias("imdbRating") String rate) {
}
