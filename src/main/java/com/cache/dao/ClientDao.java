package com.cache.dao;

import com.cache.model.Client;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import java.util.function.Consumer;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ClientDao {

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
            System.out.println("------------------------------------------------ Clientes ------------------------------------------------");
            collection.find().forEach((Consumer<? super Client>) System.out::println);
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

}
