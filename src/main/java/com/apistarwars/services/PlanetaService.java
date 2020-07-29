package com.apistarwars.services;

import com.apistarwars.entity.Planeta;
import com.apistarwars.repository.PlanetaRepository;
import com.apistarwars.exceptions.ObjectAlreadyExistsException;
import com.apistarwars.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository repository;

    @Autowired
    private SwapiService swapiService;

    public List<Planeta> findAll(){
        return repository.findAll();
    }

    public Optional<Planeta> findById(String id){
        Optional<Planeta> planeta = repository.findById(id);
        if (planeta == null){
            throw new ObjectNotFoundException("Planeta com o id informado não encontrado.");
        }
        return planeta;
    }

    public Planeta findByNome(String nome){
        Planeta planeta = repository.findByNomeIgnoreCase(nome);
        if (planeta == null){
            throw new ObjectNotFoundException("Planeta o com nome informado não encontrado.");
        }
        return planeta;
    }

    public Planeta gravar(Planeta planeta) throws IOException {
       if(repository.findByNomeIgnoreCase(planeta.getNome()) != null){
           throw new ObjectAlreadyExistsException("Já existe um planeta cadastrado com o nome informado.");
       }
       planeta.setQuantidadeFilmes(swapiService.quantidadeFilmesPorPlaneta(planeta.getNome()));
       return repository.insert(planeta);
    }

    public void deletarPorId(String id){
        repository.deleteById(id);
    }

    public void deletarPorNome(String nome){
       Planeta planeta = findByNome(nome);
       repository.delete(planeta);
    }
}
