package com.example.application.backend;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@BrowserCallable
@Service
@AnonymousAllowed
public class ProductService {
    private List<Product> products = new ArrayList<>(List.of(
            new Product(1L, "Laptop", 999.99, 10),
            new Product(2L, "Ratón", 29.99, 50),
            new Product(3L, "Teclado", 59.99, 30),
            new Product(4L, "Monitor", 349.99, 15),
            new Product(5L, "Auriculares", 79.99, 25)
    ));

    private Long nextId = 6L;

    public List<Product> findAll() {
        return products;
    }

    public Product save(Product product) {
        if (product.id() == null) {
            Product nuevo = new Product(nextId++, product.name(), product.price(), product.stock());
            products.add(nuevo);
            return nuevo;
        } else {
            products.removeIf(p -> p.id().equals(product.id()));
            products.add(product);
            return product;
        }
    }

    public void delete(Long id) {
        products.removeIf(p -> p.id().equals(id));
    }

}
