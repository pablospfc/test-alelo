package br.com.avaliacao.testealelo.repository;

import br.com.avaliacao.testealelo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getBySku(Long sku);
}
