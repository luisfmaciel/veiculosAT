package br.edu.infnet.veiculosservice.service;

import br.edu.infnet.veiculosservice.domain.Veiculo;
import br.edu.infnet.veiculosservice.respository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listAll() {
        return veiculoRepository.findAll();
    }

    public Veiculo saveVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo updateVeiculo(String id, Veiculo veiculo) {
        veiculo.setId(id);
        return veiculoRepository.save(veiculo);
    }

    public void deleteVeiculo(String id) {
        veiculoRepository.deleteById(id);
    }
}



