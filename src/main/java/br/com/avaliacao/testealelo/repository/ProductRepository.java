package br.com.avaliacao.testealelo.repository;

import br.com.avaliacao.testealelo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
