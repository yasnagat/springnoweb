package br.com.alura.screenmatch.model;

// essa classe foi criada como Record porque nao serao criados metodos
// também nao vamos mexer com ela diretamente
// ela serve apenas para obter os dados e representa-los

// é preciso especificar a correlacao para a transformacao entre os itens que serao trazidos do json
// para isso usamos o JsonAlias, que serve apenas para ler os dados JSON, que pode ser lido como
// "traga do json esse dado que tem como apelido tal
// para que seja transformado/associado a esse atributo do classe"
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// como padrao
@JsonIgnoreProperties(ignoreUnknown = true)

// MAPEAMENTO de como ficara a desserializacao
public record SeriesData(@JsonAlias("Title") String title,
                         @JsonAlias("totalSeasons") Integer seasonsTotal,
                         @JsonAlias("imdbRating") String rate) {
    // alem do @JsonAlias, a biblioteca Jackson tem a notacao @JsonProperties
    // a diferenca é que o Properties pode ser usado na serializacao e na desserializacao
    // ou seja, ele faz a leitura do JSON mas também escreve o dado

    // é possivel buscar dados em outras APIs, que poderiam definir os nomes de outras formas alem de Title
    // nesse caso, teriamos que buscar uma lista de APIs
}
