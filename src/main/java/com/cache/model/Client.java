package com.cache.model;

import java.util.List;

public class Client {

    private String nome;
    private String id;
    private List<Order> pedidos;

    public Client(){

    }

    public Client(String nome, String id, List<Order> pedidos) {
        this.nome = nome;
        this.id = id;
        this.pedidos = pedidos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Order> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Order> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "Nome= " + nome +
                ", CPF= " + id +
                ", Pedido= " + pedidos;
    }

}