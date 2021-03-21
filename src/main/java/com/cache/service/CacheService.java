package com.cache.service;

import com.cache.dao.ProductDao;
import com.cache.model.Product;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

public class CacheService {

    // Estabelecendo a conexão com o Redis
    Jedis jedis = new Jedis("localhost",6379);

    Gson gson = new Gson();

    ProductDao productDAO = new ProductDao();

    Integer ttl = 60;

    public void searchProduct(String codigo){

        // Testando conexão com o banco Redis
        System.out.println(jedis.ping());

        Product product = gson.fromJson(jedis.get(codigo), Product.class);

        if (product != null){
            System.out.println("Produto encontrado e sendo retornado do Redis!");
            System.out.println(product);
        }
        else {
            product = productDAO.findById(codigo);

            if (product != null){

                System.out.println("Produto encontrado e sendo retornado do PostgreSQl!");
                System.out.println(product);

                System.out.println(jedis.setex(product.getCodigo(),ttl, gson.toJson(product)) +
                        " O Produto foi armazenado no Redis por 60 Segundos !");
            }
            else {
                System.out.println("O produto não foi encontrado nem no Redis nem no PostgreSQl, " +
                        "antes de realizar a pesquisa, certifique-se que o Produto existe!");
            }
        }

    }



}
