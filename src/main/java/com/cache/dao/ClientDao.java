package com.cache.dao;

import com.cache.model.Client;
import com.cache.model.Order;
import com.cache.model.Product;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ClientDao {

    ProductDao productDao = new ProductDao();

    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoClient mongoClient = new MongoClient("localhost",
            MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

    MongoDatabase mongoDatabase = mongoClient.getDatabase("Project")
            .withCodecRegistry(pojoCodecRegistry);

    MongoCollection<Client> collection = mongoDatabase.getCollection("Cliente", Client.class);

    public void create(Client client){
        try {
            collection.insertOne(client);
            System.out.println("Cliente Salvo com sucesso no MongoDB!");
        }catch (com.mongodb.MongoWriteException mongoWriteException){
            System.out.println("Falha ao Salvar o Cliente no MongoDB!");
        }
    }

    public void read(){
        try {
            collection.find().forEach((Consumer<? super Client>) System.out::println);
            System.out.println("------------------------------------------------ Clientes ------------------------------------------------");
        }catch(com.mongodb.MongoException exception){
            System.out.println("Falha ao Ler os Clientes no MongoDB!");
        }
    }

    public void update(String cpf, String nome){
        try {
            collection.updateOne(new Document("_id", cpf), set("nome", nome));
            System.out.println("Cliente Atualizado com sucesso no MongoDB!");
        }catch (com.mongodb.MongoException exception){
            System.out.println("Falha ao Atualizar o Cliente no MongoDB!");
        }
    }

    public void delete(String cpf){
        try {
            collection.deleteOne(new Document("_id", cpf));
            System.out.println("Cliente Deletado com sucesso no MongoDB!");
        }catch (com.mongodb.MongoException exception){
            System.out.println("Falha ao Deletar o Clientes do MongoDB!");
        }
    }

    public String setName() {
        Scanner sc = new Scanner(System.in);
        String nameClient;
        System.out.println("Digite o nome que deseja atribuir ao Cliente: ");
        nameClient = sc.nextLine();
        return nameClient;
    }

    public String setCPF() {
        Scanner sc = new Scanner(System.in);
        String cpfClient;
        System.out.println("Digite o CPF que deseja atribuir ao Cliente: ");
        cpfClient = sc.nextLine();
        return cpfClient;
    }

    public List<Order> addProducts(){
        Scanner sc = new Scanner(System.in);
        String productID;
        String orderID;
        List<String> productList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        Product product;
        Order order;
        String productString;
        int amount;
        int x = 1;
        float price, product_total, total = 0;

        while (x == 1){
            System.out.println("Digite o código do Pedido que deseja atribuir ao Cliente: ");
            orderID = sc.nextLine();

            if (orderID != null) {
                do {
                    System.out.println("Digite o código do Produto que deseja adicionar no Pedido: ");
                    productID = sc.nextLine();
                    product = productDao.findById(productID);

                    if (product != null) {
                        System.out.println("Digite a Quantidade do Produto que deseja adicionar no Pedido: ");
                        amount = Integer.parseInt(sc.nextLine());
                        price = product.getPreco();
                        product_total = price * amount;
                        productString = product + ", Quantidade: " + amount + ", Total Produto: " + product_total + " R$ ";
                        total = total + product_total;
                        productList.add(productString);
                    }
                }
                while (!productID.equals("q"));
            }
            System.out.println("Dejesa atribuir outro Pedido ao Cliente: Digite 1 para SIM, 0 para NÃO: ");
            x = Integer.parseInt(sc.nextLine());
//            if (x == 1){
//
//            }
            order = new Order(orderID, productList, total);
            orderList.add(order);
        }
        return orderList;
    }

}
