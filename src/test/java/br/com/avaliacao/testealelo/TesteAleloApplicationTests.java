package br.com.avaliacao.testealelo;

import br.com.avaliacao.testealelo.controller.ProductController;
import br.com.avaliacao.testealelo.exceptions.NotFoundException;
import br.com.avaliacao.testealelo.model.Product;
import br.com.avaliacao.testealelo.repository.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SpringBootTest
class TesteAleloApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void salvarTest() {
        Product product = new Product(1L, "camisa2", "012030123");
        productRepository.save(product);
        assertNotNull(product);
    }

    @Test
    public void deveLancarExcecaoQuandoIdProdutoForNulo() throws NotFoundException {
        ProductController productController = new ProductController(productRepository);
        productController.getProductById(null);
    }

    @Before("")
    public void setup() {
        Product vehicle = new Product(22917L, "Camisa", "0001762718891");

        productRepository.save(vehicle);
    }

}
