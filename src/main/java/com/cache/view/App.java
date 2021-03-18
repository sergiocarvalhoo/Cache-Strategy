package com.cache.view;

import com.cache.dao.ProductDao;
import com.cache.model.Product;
import redis.clients.jedis.Jedis;

// TODO: Refatorar criando classe para realizar o CRUD do objeto

public class App {

    public static void main(String[] args) throws Exception {

        // Estabelecendo a conexão com o Redis (atualizar para dados locais)
        Jedis jedis = new Jedis("localhost",6379);

        // Testando conexão com o banco
        System.out.println(jedis.ping());

        Product product1 = new Product("188HGFS","Teste","Comun", (float) 19.3);
        Product product2 = new Product("18GHHGFS","Teste2222","Dois", (float) 53.3);
        Product product3 = new Product("1767HHS","Tesdffdte2222","Três", (float) 26);

        ProductDao productDAO = new ProductDao();

        productDAO.save(product1);
        productDAO.save(product2);
        productDAO.save(product3);

        System.out.println("Produto encontrado: " + productDAO.findById("188HGFS").toString());

        productDAO.delete("188HGFS");


//        Biblioteca que será utilizada para converter Json <-> Object
//        Gson gson = new Gson();
//
//        Salvando uma pessoa
//        Pessoa pessoa = new Pessoa("111.111.111-01", "Paulo",
//                LocalDate.of(1990,2,3));
//        System.out.println(jedis.set(pessoa.getCpf(), gson.toJson(pessoa)));
//
//        Recuperando dados a partir de uma chave
//        Pessoa pessoa1 = gson.fromJson(
//                jedis.get("111.111.111-01"),
//                Pessoa.class
//        );
//        System.out.println(pessoa1);

    }

}
