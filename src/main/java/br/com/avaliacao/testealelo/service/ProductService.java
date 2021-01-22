package br.com.avaliacao.testealelo.service;

import br.com.avaliacao.testealelo.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getBySku(Long sku);
    List<Product> getAll();
    Optional<Product> getById(Long id);
    Product save(Product product);
    void delete(Long id);
}
