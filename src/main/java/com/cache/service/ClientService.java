package com.cache.service;

import com.cache.dao.ClientDao;
import com.cache.dao.ProductDao;
import com.cache.model.Client;
import com.cache.model.Order;
import com.cache.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientService {

    CacheService cacheService = new CacheService();
    ClientDao clientDao = new ClientDao();

    public void createClient(){

        // Declarando Scanner
        Scanner sc = new Scanner(System.in);

        // Lista de Pedidos
        List<Order> orderList = new ArrayList<>();

        // Lista de Produtos
        List<String> productList = null;

        // Controla a escolha de Adicionar mais Pedidos
        int x = 1;

        // Declarando o Cliente
        Client client = new Client();

        // Preenchendo Nome do Cliente
        System.out.println("Digite o nome que deseja atribuir ao Cliente: ");
        client.setNome(sc.nextLine());

        // Preenchendo CPF do Cliente
        System.out.println("Digite o CPF que deseja atribuir ao Cliente: ");
        client.setId(sc.nextLine());

        do {
            // Total do Pedido
            float total = 0;

            // Armazenará o Preço Unitário do Produto
            float price = 0;

            // Quantidade de Unidades do Produto
            int amount = 0;

            // Armazenará o Total daquele Produto: Quantidade x Preço Unitário
            float product_total = 0;

            // Instancia um novo Pedido
            Order orderOne = new Order();

            // Instancia um novo Array List de Produtos
            productList = new ArrayList<>();


            // Preenchendo o Código do Pedido que será atribuido ao Cliente
            System.out.println("Digite o código do Pedido que deseja atribuir ao Cliente: ");
            String orderID = sc.nextLine();

            if (!orderID.equals("q")) {

                // Armazenará o ID do Produto
                String productID;

                do {

                    // Define o Produto para Adicionar ao Pedido
                    System.out.println("Digite o código do Produto que deseja adicionar no Pedido: ");
                    productID = sc.nextLine();

                    // Procura o Produto escolhido, usando o serviço de Cache nos Redis
                    Product product = cacheService.searchProduct(productID);

                    if (product != null) {

                        try {

                            // Define a Quantidade do Produto Escolhido
                            System.out.println("Digite a Quantidade do Produto que deseja adicionar no Pedido: ");
                            amount = Integer.parseInt(sc.nextLine());

                        }catch (NumberFormatException exception){
                            System.out.println("Tente novamente digitando a quantidade de Produtos em valor inteiro!");
                        }

                        // Define o Preço Unitário do Produto
                        price = product.getPreco();

                        // Multiplica a Quantidade do Produto pelo Preço Unitário dele, definindo o Total daquele Produto no Pedido
                        product_total = price * amount;

                        // Cria uma String com o Product.toString, Quantidade do Produto, e Total do Produto
                        String productString = product + ", Quantidade: " + amount + ", Total Produto: " + product_total + " R$";

                        // Adiciona o String de Produto na Lista de String com Produtos
                        productList.add(productString);

                        // Acrescenta os valores dos Produtos ao Total do Pedido
                        total = total + product_total;

                    }// Fim do IF

                }while (!productID.equals("q")); // Fim do Do While

            }// Fim do IF

                orderOne.setId(orderID);
                orderOne.setProdutos(productList);
                orderOne.setTotal(total);

                orderList.add(orderOne);

            try {
                // Pergunta se deseja Atribuir mais um Pedido ao Cliente
                System.out.println("Dejesa atribuir outro Pedido ao Cliente: Digite 1 para SIM, 0 para NÃO: ");
                x = Integer.parseInt(sc.nextLine());

            }catch (NumberFormatException exception){
                System.out.println("Tente novamente digitando 1 para Adicionar um novo Pedido ou 0 para Salvar !");
            }


        }while (x != 0); // Fim do Do While

        // Define os Pedidos do Cliente usando a Lista de Pedidos
        client.setPedidos(orderList);

        // Salva o Cliente no MongoDB usando o ClientDao
        clientDao.create(client);
    }

}
