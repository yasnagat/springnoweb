// CLASSE DE CONSUMIR API
// boa pratica de separacao de responsabilidades

package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// da mesma forma que digitamos o endereco no navegador para consultar dados, essa classe vai fazer um serviço
// uma requisicao e vamos receber uma resposta
public class APIConsumer {

    public String dataObtainer(String adress) {
        // cliente para gerar a requisicao de consulta para a api
        HttpClient client = HttpClient.newHttpClient();

        // especifica para qual endereço sera feita a requisicao
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(adress))
                .build();

        // metodo para receber a resposta da requisicao feita
        HttpResponse<String> response = null;
        // tratamento das excecoes que podem acontecer durante a execucao
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // corpo da resposta
        String json = response.body();
        return json;
    }
}