package br.com.alura.screenmatch.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episode {
    private String title;
    private Integer seasonNumber;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate launchDate;

    public Episode(Integer seasonNumber, EpisodesData episodesData) {
        this.seasonNumber = seasonNumber;
        this.title = episodesData.title();
        this.episodeNumber = episodesData.number();
        // inicialmente, gera um erro, porque os tipos das variaveis são diferentes, lembrando que o atributo rating é importado da classe EpisodesData
        // e la ele é declarado como String
        // por isso precisamos forçar que
        // a transformacao para um tipo que presumimos ser o padrao
        // o valueOf le a string e converte-a para double
        // depois surge mais um erro, porque nem todos os episodios receberam avaliações, entao o tipo double não é o unico tipo para esse atributo
        // por isso, precisamos tratar a excecao:
        try {
            this.rating = Double.valueOf(episodesData.rating());
        // caso a avaliacao nao esteja no tipo double, ou seja com uma excecao de formato numerico
            // vamos assumir que o valor dela é 0
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try {
            // enfrentamos um erro semelhante e tratamentos com o parse presumindo o padrao de data ISO_LOCAL_DATE: YYYY-MM-DD
            this.launchDate = LocalDate.parse(episodesData.launchDate());
        } catch (DateTimeException e) {
            this.launchDate = null;
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }
    @Override
    public String toString() {
        return                 "Título = '" + title + '\'' +
                ", Número Episódio = " + episodeNumber +
                ", Avaliação = " + rating +
                ", Data Lançamento = " + launchDate;
    }


}
