package br.edu.infnet.veiculosservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "veiculos")
public class Veiculo {
    @Id
    private String id;
    private String modelo;
    private String marca;
    private int ano;
}