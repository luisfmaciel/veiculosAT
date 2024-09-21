package br.edu.infnet.veiculosservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/api/status")
    public String getStatus() {
        return "Serviço de Veículo rodando com sucesso!";
    }
}
