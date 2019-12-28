package com.example.forex.controller;

import com.example.forex.exception.ResourceNotFoundException;
import com.example.forex.model.Forex;
import com.example.forex.repository.ForexRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ForexEngineController {

    @Autowired
    ForexRepository forexRepository;

    @GetMapping("/forexs")
    public List<Forex> getAllForexs() {
        return forexRepository.findAll();
    }

    @PostMapping("/forexs")
    public Forex createForex(@Valid @RequestBody Forex forex) {
        return forexRepository.save(forex);
    }

    @GetMapping("/forexs/{id}")
    public Forex getForexById(@PathVariable(value = "id") Long forexId) {
        return forexRepository.findById(forexId)
                .orElseThrow(() -> new ResourceNotFoundException("Forex", "id", forexId));
    }

    @PutMapping("/forexs/{id}")
    public Forex updateForex(@PathVariable(value = "id") Long forexId,
                                           @Valid @RequestBody Forex forexDetails) {

        Forex forex = forexRepository.findById(forexId)
                .orElseThrow(() -> new ResourceNotFoundException("Forex", "id", forexId));

        forex.setCurrency(forexDetails.getCurrency());
        forex.setRate(forexDetails.getRate());

        Forex updatedForex = forexRepository.save(forex);
        return updatedForex;
    }

    @DeleteMapping("/forexs/{id}")
    public ResponseEntity<?> deleteForex(@PathVariable(value = "id") Long forexId) {
        Forex forex = forexRepository.findById(forexId)
                .orElseThrow(() -> new ResourceNotFoundException("Forex", "id", forexId));
        forexRepository.delete(forex);

        return ResponseEntity.ok().build();
    }
}
