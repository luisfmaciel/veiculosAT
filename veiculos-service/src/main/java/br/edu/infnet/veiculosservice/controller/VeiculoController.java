package br.edu.infnet.veiculosservice.controller;

import br.edu.infnet.veiculosservice.domain.Veiculo;
import br.edu.infnet.veiculosservice.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> listAll() {
        List<Veiculo> veiculos = veiculoService.listAll();
        return ResponseEntity.ok(veiculos);
    }

    @PostMapping
    public ResponseEntity<Veiculo> saveVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo veiculosSalvo = veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculosSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable String id, @RequestBody Veiculo veiculo) {
        Veiculo updatedVeiculo = veiculoService.updateVeiculo(id, veiculo);
        return ResponseEntity.ok(updatedVeiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVeiculo(@PathVariable String id) {
        Map<String, String> response = new HashMap<>();
        veiculoService.deleteVeiculo(id);
        response.put("message", "Veiculo excluído com sucesso");
        return ResponseEntity.ok(response);
    }
}
