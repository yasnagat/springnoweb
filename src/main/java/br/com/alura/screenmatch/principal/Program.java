package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.EpisodesData;
import br.com.alura.screenmatch.model.SeriesData;
import br.com.alura.screenmatch.service.APIConsumer;
import br.com.alura.screenmatch.service.DataConverter;
import br.com.alura.screenmatch.model.SeasonsData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    private Scanner read = new Scanner(System.in);
    private DataConverter conversor = new DataConverter();
    private APIConsumer APIConsumer = new APIConsumer();

    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void menuExibit() {
        System.out.print("Insira o nome da série que você quer buscar: ");
        var serieName = read.nextLine();

        var json = APIConsumer.dataObtainer(ADDRESS + serieName.replace(" ", "+") + API_KEY);
        SeriesData data = conversor.dataObtainer(json, SeriesData.class);
        System.out.println(data);

        List<SeasonsData> seasons = new ArrayList<>();
        for(int i = 1; i<=data.totalSeasons(); i++) {
            json = APIConsumer.dataObtainer(ADDRESS +
                    serieName.replace(" ", "+") +
                    "&season=" +
                    i +
                    API_KEY);
            SeasonsData seasonsData = conversor.dataObtainer(json, SeasonsData.class);
            seasons.add(seasonsData);
        }
        // seasons.forEach(System.out::println);
        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

        // unificar as listas com os dados de cada temporada em uma só lista usando o stream
        List<EpisodesData> episodesData = seasons.stream()
                // metodo para concatenar cada elemento da lista em uma lista unica
                .flatMap(e -> e.episodes().stream())
                // collect é responsavel por adicionar ou remover itens da lista
                // se usassemos o toList direto, a List se tornaria imutavel
                .collect(Collectors.toList());

        // para ordenar e limitar o top 5, implementando o parametro da busca
        episodesData.stream()
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                        .sorted(Comparator.comparing(EpisodesData::rating).reversed())
                                .limit(5)
                                        .forEach(System.out::println);

        // para lidar com cada episodio isoladamente, como elemento separado da lista total da temporada
        List<Episode> episodes = seasons.stream()
                .flatMap(e -> e.episodes().stream())
                .map(d -> new Episode(d.number(), d)
                ).collect(Collectors.toList());
        episodes.forEach(System.out::println);
    }
}