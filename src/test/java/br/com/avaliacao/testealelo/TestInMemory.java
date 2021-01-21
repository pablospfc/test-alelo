package br.com.avaliacao.testealelo;

import br.com.avaliacao.testealelo.repository.ProductRepository;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@SpringBootTest

@Transactional
public class TestInMemory {

    @Resource
    private ProductRepository productRepository;
}
