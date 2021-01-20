package br.com.avaliacao.testealelo;

import br.com.avaliacao.testealelo.controller.ProductController;
import br.com.avaliacao.testealelo.exceptions.NotFoundException;
import br.com.avaliacao.testealelo.repository.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TesteAleloApplicationTests {

    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void deveLancarExcecaoQuandoIdProdutoForNulo() throws NotFoundException {
        ProductController productController = new ProductController(productRepository);
        productController.getProductById(null);
    }

    @Before("")
    public void setup() {
        this.productRepository = Mockito.mock(ProductRepository.class);
    }

}
