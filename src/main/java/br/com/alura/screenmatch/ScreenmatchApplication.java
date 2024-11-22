package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.SeriesData;
import br.com.alura.screenmatch.service.APIConsumer;

import br.com.alura.screenmatch.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// classe de aplicacao de linha de comando
public class ScreenmatchApplication implements CommandLineRunner {

	// commandlinerunner especifica que essa classe vai ser uma interface de linha de comando
	// ou seja, quando o programa for iniciado, esse metodo da classe vai ser iniciado junto
	// para executar o que for mandado
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// instanciar a classe de consumo da api
		// APIConsumer consumer = new APIConsumer();
		var consumer = new APIConsumer();

		// variavel com inferencia de tipo, importada da classe APIConsumer
		var json = consumer.dataObtainer("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		System.out.println(json);

		DataConverter conversor = new DataConverter();
		SeriesData data = conversor.dataObtainer(json, SeriesData.class);
		System.out.println(data);
	}

	// DESSERIALIZACAO DE DADOS: transformar dados JSON em dados em objetos de classes e componentes do programa
	// vamos usar o jackson
	// isso é feito mais facilmente a partir da ferramenta do MAVEN - que é um gerenciador de dependencias do spring

}
