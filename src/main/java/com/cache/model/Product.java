package com.cache.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @Basic(optional = false)
    private String codigo;
    private String nome;
    private String tipo;
    private Float preco;

    public Product(String codigo, String nome, String tipo, Float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

    public Product() {

    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nome, product.nome) &&
                Objects.equals(tipo, product.tipo) &&
                Objects.equals(codigo, product.codigo) &&
                Objects.equals(preco, product.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, tipo, codigo, preco);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", preco=" + preco +
                '}';
    }
}