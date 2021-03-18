package com.cache.dao;

import com.cache.jpautil.JpaUtil;
import com.cache.model.Product;
import javax.persistence.EntityManager;

public class ProductDao {

    public EntityManager getEntityManager(){
        return JpaUtil.createEntityManager();
    }

    public void save(Product product) throws Exception {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            System.out.println("Salvando o Produto: " + product.toString());
            if(product.getCodigo() == null) {
                entityManager.persist(product);
            } else {
                product = entityManager.merge(product);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void delete(String codigo) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, codigo);
            System.out.println("Excluindo os dados do Produto: " + product.toString());
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        }
        finally {
            entityManager.close();
        }
    }


    public Product findById(String codigo) {
        EntityManager entityManager = getEntityManager();
        Product product = null;
        try {
            product = entityManager.find(Product.class, codigo);
        } finally {
            entityManager.close();
        }
        return product;
    }
}
