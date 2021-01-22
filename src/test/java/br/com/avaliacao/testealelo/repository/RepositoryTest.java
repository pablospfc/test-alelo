package br.com.avaliacao.testealelo.repository;

import br.com.avaliacao.testealelo.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class RepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    public void setup() {
        Product product = new Product(102910L, "Biscoito", "0001882617");
        productRepository.save(product);
    }

    @Test
    public void saveTest() {
        Product product = new Product(102910L, "Ração", "00144261788");
        productRepository.save(product);
        Assert.assertNotNull(product);
    }

    @Test
    public void getBySkuTest() {
        Optional<Product> product = productRepository.getBySku(102910L);
        Assert.assertTrue(product.isPresent());
    }
}
