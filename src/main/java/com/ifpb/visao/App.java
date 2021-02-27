package com.ifpb.visao;

import com.google.gson.Gson;
import com.ifpb.modelo.Pessoa;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;

// TODO: Refatorar criando classe para realizar o CRUD do objeto

public class App {

    public static void main(String[] args) {

        // Estabelecendo a conexão com o Redis (atualizar para dados locais)
        Jedis jedis = new Jedis("172.18.0.2",6379);

        //Testando conexão com o banco
//        System.out.println(jedis.ping());

        // Biblioteca que será utilizada para converter Json <-> Object
        Gson gson = new Gson();

        // Salvando uma pessoa
//        Pessoa pessoa = new Pessoa("111.111.111-01", "Paulo",
//                LocalDate.of(1990,2,3));
//        System.out.println(jedis.set(pessoa.getCpf(), gson.toJson(pessoa)));

        // Recuperando dados a partir de uma chave
        Pessoa pessoa1 = gson.fromJson(
                jedis.get("111.111.111-01"),
                Pessoa.class
        );
        System.out.println(pessoa1);

    }

}
