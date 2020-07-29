package com.apistarwars.services;

import com.apistarwars.entity.Planeta;
import com.apistarwars.repository.PlanetaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service @EnableScheduling
public class SwapiService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Value("https://swapi.dev/api")
    private String BASE_URL;


    public int quantidadeFilmesPorPlaneta(String nome) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(BASE_URL + "/planets?search=" + nome,
                HttpMethod.GET, entity, String.class);

        JsonNode root = new ObjectMapper().readTree(response.getBody());

        if (root != null && root.get("count").asLong() > 0) {
            JsonNode films = root.get("results").get(0).get("films");
            return films.size();
        }

        return 0;
    }

    @Scheduled(cron = "0 0 0 1 1/1 ?", zone = "America/Sao_Paulo")
    public void atualizarQuantidadeFilmesPlaneta() throws IOException{

        List<Planeta> planetas = planetaRepository.findAll();

        for(Planeta planeta : planetas){
            int quantidadeDeFilmes = quantidadeFilmesPorPlaneta(planeta.getNome());
            if (quantidadeDeFilmes != planeta.getQuantidadeFilmes()){
                planeta.setQuantidadeFilmes(quantidadeDeFilmes);
                planetaRepository.save(planeta);
            }
        }

    }
}
