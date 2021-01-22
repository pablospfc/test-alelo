package br.com.avaliacao.testealelo.service;

import br.com.avaliacao.testealelo.model.CepDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CEPService {

    private RestTemplate restTemplate;

    public CepDTO getEnderecoPorCep(String cep) {
        ResponseEntity resp = restTemplate.getForEntity(String.format("viacep.com.br/ws/%s/json/",cep),CepDTO.class);
        return resp.getStatusCode() == HttpStatus.OK ? (CepDTO) resp.getBody() : null;
    }
}
