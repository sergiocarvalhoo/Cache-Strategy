package com.cache.view;

import com.cache.dao.ClientDao;
import com.cache.dao.ProductDao;
import com.cache.model.Product;
import com.cache.service.CacheService;
import com.cache.service.ClientService;

public class App {

    public static void main(String[] args) throws Exception {

        ProductDao productDAO = new ProductDao();

        ClientDao clientDao = new ClientDao();

        CacheService cacheService = new CacheService();

        ClientService clientService = new ClientService();

        cacheService.testRedis();

        Product product1 = new Product("1111-1","Arroz","Comun", (float) 5.3);
        Product product2 = new Product("1111-2","Carne","Gado", (float) 45.7);
        Product product3 = new Product("1111-3","Feijão","Carioca", (float) 4.6);
        Product product4 = new Product("1111-4","Macarrão","Parafuso", (float) 3.7);
        Product product5 = new Product("1111-5","Batata","Doce", (float) 3.5);

        productDAO.save(product1);
        productDAO.save(product2);
        productDAO.save(product3);
        productDAO.save(product4);
        productDAO.save(product5);

        System.out.println("\n");

        cacheService.searchProduct("1111-1");
        System.out.println("\n");

        cacheService.searchProduct("1111-2");
        System.out.println("\n");

        cacheService.searchProduct("1111-2");
        System.out.println("\n");

        cacheService.searchProduct("1111-4");
        System.out.println("\n");

        cacheService.searchProduct("1111-5");
        System.out.println("\n");

        clientService.createClient();

        clientDao.read();

        clientDao.update("111.111.111-11", "Paulo");

        clientDao.read();

//        clientDao.delete("111.111.111-11");

//        clientDao.read();

    }

}