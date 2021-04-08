package com.cache.model;

import java.util.List;

public class Order {

    private String id;
    private List<String> produtos;
    private float total;

    public Order() {
    }

    public Order(String id, List<String> produtos, float total) {
        this.id = id;
        this.produtos = produtos;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido: " +
                "Código= " + id +
                ", Produtos= " + produtos +
                ", Total= " + total;
    }
}