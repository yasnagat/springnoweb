package br.com.alura.screenmatch.service;

public interface IDataConverter {
    // collection generics porque vamos aceitar qualquer tipo de variavel que vier a ser atribuida a essa variavel
    // indicamos o tipo generico por meio de uma letra, por boa pratica T
    // como parametro, a interface vai pegar a variavel json do tipo string
    // a classe generica do tipo classe
    // com isso, vamos ter um conversor generico capaz de absorver qualquer tipo de dado que for
    // inserido
    <T> T dataObtainer(String json, Class<T> classe);
    //Ele receberá um json, que é uma string, uma classe e no conversor de dados
    // tentaremos transformá-lo na classe que foi indicada.
}
