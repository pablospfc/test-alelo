package br.com.avaliacao.testealelo.repository;

import br.com.avaliacao.testealelo.model.CepDTO;
import br.com.avaliacao.testealelo.service.CEPService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ApiTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CEPService cepService = new CEPService();

    @Test
    public void testApi() {
        CepDTO cep = new CepDTO("65046-660", "Avenida Santos Dumont", "", "Anil", "São Luís", "MA");
        Mockito.when(restTemplate.getForEntity("viacep.com.br/ws/65046660/json/", CepDTO.class))
                .thenReturn(new ResponseEntity(cep, HttpStatus.OK));

        CepDTO cep2 = cepService.getEnderecoPorCep("65046660");
        Assert.assertEquals(cep, cep2);
    }
}
