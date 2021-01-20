package br.com.avaliacao.testealelo.controller;

import br.com.avaliacao.testealelo.exceptions.NotFoundException;
import br.com.avaliacao.testealelo.model.Product;
import br.com.avaliacao.testealelo.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ApiOperation(value = "Retorna todos os produtos")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna todos os produtos cadastrados"),
            @ApiResponse(code = 404, message = "Não há registros de produtos cadastrados"),
            @ApiResponse(code = 500, message = "Foi gerada alguma exceção"),})
    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProducts() {
     return new ResponseEntity<>(
             productRepository.findAll(),
             HttpStatus.OK
     );
    }

    @ApiOperation(value = "Retorna um produto específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o produto"),
            @ApiResponse(code = 404, message = "Não há registro do produto com id informado"),
            @ApiResponse(code = 500, message = "Foi gerada alguma exceção")
    })
    @GetMapping( value = "/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) throws NotFoundException{
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        return new ResponseEntity<Product>(
                product,
                HttpStatus.OK
        );

    }

    @ApiOperation(value = "Salva um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o novo produto cadastrado"),
            @ApiResponse(code = 500, message = "Foi gerada alguma exceção")
    })
    @PostMapping(value = "/products")
    public Product postProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @ApiOperation(value = "Atualiza um produto específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o novo produto com dados atualizados"),
            @ApiResponse(code = 404, message = "Não há registro do produto com id informado"),
            @ApiResponse(code = 500, message = "Foi gerada alguma exceção")
    })
    @PutMapping(value = "/products/{id}")
    public ResponseEntity putProduct(@RequestBody Product product, @PathVariable(value = "id") Long id) throws NotFoundException {
        return productRepository.findById(id)
                .map(pro -> {
                    pro.setSku(product.getSku());
                    pro.setBarCode(product.getBarCode());
                    pro.setDescription(product.getDescription());
                    Product updatedProduct = productRepository.save(pro);
                    return new ResponseEntity(
                            updatedProduct,
                            HttpStatus.OK
                    );
                }).orElse(new ResponseEntity(new NotFoundException("Product not found"), HttpStatus.NOT_FOUND));

    }

    @ApiOperation(value = "Exclui um produto específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O produto é removido"),
            @ApiResponse(code = 404, message = "Não há registro do produto com id informado"),
            @ApiResponse(code = 500, message = "Foi gerada alguma exceção")
    })
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") Long id) {

        return productRepository.findById(id)
                .map(pro -> {
                    productRepository.deleteById(id);
                    return new ResponseEntity(
                         "The product was removed successfully",
                         HttpStatus.OK
                    );
                }).orElse(new ResponseEntity(new NotFoundException("Product not found"), HttpStatus.NOT_FOUND));

    }
}
