package br.edu.infnet.veiculosservice.respository;

import br.edu.infnet.veiculosservice.domain.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
}
