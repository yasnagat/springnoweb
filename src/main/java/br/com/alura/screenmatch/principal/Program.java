package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.EpisodesData;
import br.com.alura.screenmatch.model.SeriesData;
import br.com.alura.screenmatch.service.APIConsumer;
import br.com.alura.screenmatch.service.DataConverter;
import br.com.alura.screenmatch.model.SeasonsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// classe que vai ser responsável por implementar o menu de interação com o usuário
public class Program {
    private Scanner read = new Scanner(System.in);
    // instancia o objeto da classe de conversão dos dados JSON para POJO
    private DataConverter conversor = new DataConverter();

    // instancia o objeto da classe de consultas à API
    private APIConsumer APIConsumer = new APIConsumer();

    // classe do tipo final porque vão ser fixas
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    // metodo que implementa o menu de interacao
    public void menuExibit() {
        System.out.print("Insira o nome da série que você quer buscar: ");
        var serieName = read.nextLine();

        // pega os dados JSON a partir da URL criada com o uso das variaveis finals
        var json = APIConsumer.dataObtainer(ADDRESS + serieName.replace(" ", "+") + API_KEY);

        // desacomplando as classes e trazendo operacoes do metodo run para o program
        // usa a classe do tipo record, que contem a correspondencia dos dados sobre a serie
        SeriesData data = conversor.dataObtainer(json, SeriesData.class);
        System.out.println(data);

        List<SeasonsData> seasons = new ArrayList<>();

        // loop para mostrar os dados de todas as temporadas da serie
        for(int i = 1; i<=data.totalSeasons(); i++) {
            json = APIConsumer.dataObtainer(ADDRESS + serieName.replace(" ", "+") +"&season=" + i + API_KEY);
            SeasonsData seasonsData = conversor.dataObtainer(json, SeasonsData.class);
            seasons.add(seasonsData);
        }
        // substitui um loop para exibir todos os elementos da ArrayList que preenchemos com os dados POJO (ex-JSON) depois de consumir a API
        seasons.forEach(System.out::println);

        // loop para exibir os dados de cada episodio por temporada. antes só tinhamos os dados por temporada condensados
        // agora, vamos acrescentar um loop, como se fosse uma matriz, para percorrer episodio por episodio de cada temporada
        // colecao (episodios) de colecao (temporada)
        for (int i = 0; i < data.totalSeasons(); i++) {
            List<EpisodesData> episodesData = seasons.get(i).episodes();
            // para coletar os titulos dos episodios
            for (int j = 0; j < episodesData.size(); j++) {
                System.out.println(episodesData.get(j).title());
            }
        }
    }
}
